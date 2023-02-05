> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [docs.docker.com](https://docs.docker.com/engine/security/#docker-daemon-attack-surface)

> Docker Daemon 攻击面回顾

在审查 Docker 安全性时，需要考虑四个主要方面：

*   内核的内在安全性及其对名称空间和 cgroup 的支持；
*   Docker 守护进程本身的攻击面；
*   容器配置文件中的漏洞，默认情况下或用户自定义时。
*   内核的“强化”安全特性以及它们如何与容器交互。

内核命名空间[🔗](#kernel-namespaces)
------------------------------

Docker 容器与 LXC 容器非常相似，它们具有相似的安全特性。当您使用 启动容器时 `docker run`，Docker 在后台为容器创建一组命名空间和控制组。

**命名空间提供了第一种也是最直接的隔离形式**：在一个容器中运行的进程无法看到，甚至更不会影响在另一个容器或主机系统中运行的进程。

**每个容器也有自己的网络堆栈**，这意味着一个容器没有特权访问另一个容器的套接字或接口。当然，如果相应地设置了主机系统，容器可以通过各自的网络接口相互交互——就像它们可以与外部主机交互一样。当您为容器指定公共端口或使用 [_链接_](https://docs.docker.com/network/links/)时 ，容器之间将允许 IP 流量。它们可以相互 ping 通、发送/接收 UDP 数据包以及建立 TCP 连接，但如有必要，可以对其进行限制。从网络架构的角度来看，给定 Docker 主机上的所有容器都位于桥接口上。这意味着它们就像通过普通以太网交换机连接的物理机器；不多也不少。

提供内核命名空间和私有网络的代码有多成熟？内核名称空间是[在内核版本 2.6.15 和 2.6.26 之间](https://man7.org/linux/man-pages/man7/namespaces.7.html)引入的。这意味着自 2008 年 7 月（2.6.26 发布之日）起，名称空间代码已在大量生产系统上得到执行和审查。还有更多：命名空间代码的设计和灵感甚至更古老。命名空间实际上是为了重新实现[OpenVZ](https://en.wikipedia.org/wiki/OpenVZ)的特性，以便它们可以合并到主流内核中。而 OpenVZ 最初发布于 2005 年，因此无论是设计还是实现都相当成熟。

对照组[🔗](#control-groups)
------------------------

控制组是 Linux 容器的另一个关键组件。他们实施资源核算和限制。它们提供了许多有用的指标，但它们也有助于确保每个容器都能公平地分配内存、CPU、磁盘 I/O；而且，更重要的是，单个容器无法通过耗尽这些资源之一来使系统崩溃。

因此，虽然它们在阻止一个容器访问或影响另一个容器的数据和进程方面没有发挥作用，但它们对于抵御某些拒绝服务攻击至关重要。它们在多租户平台（如公共和私有 PaaS）上尤为重要，即使在某些应用程序开始出现异常时也能保证一致的正常运行时间（和性能）。

Control Groups 也已经存在了一段时间：代码开始于 2006 年，最初合并到内核 2.6.24 中。

Docker 守护进程攻击面[🔗](#docker-daemon-attack-surface)
-------------------------------------------------

使用 Docker 运行容器（和应用程序）意味着运行 Docker 守护进程。[除非您选择加入无根模式](https://docs.docker.com/engine/security/rootless/)，否则此守护程序需要`root`特权，因此您应该了解一些重要细节。[](https://docs.docker.com/engine/security/rootless/)

首先，**应该只允许受信任的用户控制您的 Docker 守护进程**。这是一些强大的 Docker 功能的直接结果。具体来说，Docker 允许您在 Docker 主机和来宾容器之间共享一个目录；它允许您在不限制容器访问权限的情况下这样做。这意味着您可以启动一个容器，其中`/host`目录是`/`您主机上的目录；并且容器可以不受任何限制地更改您的主机文件系统。这类似于虚拟化系统如何允许文件系统资源共享。没有什么能阻止您与虚拟机共享您的根文件系统（甚至您的根块设备）。

这具有很强的安全隐患：例如，如果您从 Web 服务器检测 Docker 以通过 API 提供容器，您应该比平时更加​​小心地检查参数，以确保恶意用户无法传递精心设计的参数，从而导致 Docker创建任意容器。

出于这个原因，REST API 端点（Docker CLI 用来与 Docker 守护进程通信）在 Docker 0.5.2 中发生了变化，现在使用 UNIX 套接字而不是绑定在 127.0.0.1 上的 TCP 套接字（后者容易跨站点请求伪造攻击，如果你碰巧直接在你的本地机器上运行 Docker，在 VM 之外）。然后，您可以使用传统的 UNIX 权限检查来限制对控制套接字的访问。

如果您明确决定这样做，也可以通过 HTTP 公开 REST API。但是，如果您这样做，请注意上述安全隐患。请注意，即使您有防火墙来限制网络中其他主机对 REST API 端点的访问，端点仍然可以从容器访问，并且很容易导致权限提升。因此，_必须使用_[HTTPS 和证书](https://docs.docker.com/engine/security/protect-access/)来保护 API 端点 。还建议确保只能从受信任的网络或 VPN 访问它。

如果您更喜欢 SSH 而不是 TLS，也可以使用`DOCKER_HOST=ssh://USER@HOST`or代替。`ssh -L /path/to/docker.sock:/var/run/docker.sock`

该守护进程也可能容易受到其他输入的影响，例如从磁盘加载图像`docker load`，或从网络 加载图像`docker pull`。从 Docker 1.3.2 开始，图像现在在 Linux/Unix 平台上的 chrooted 子进程中提取，这是更广泛的特权分离努力的第一步。从 Docker 1.10.0 开始，所有图像都通过其内容的加密校验和进行存储和访问，从而限制了攻击者与现有图像发生冲突的可能性。

最后，如果您在服务器上运行 Docker，建议在服务器上专门运行 Docker，并将所有其他服务移动到 Docker 控制的容器中。当然，保留您最喜欢的管理工具（可能至少是一个 SSH 服务器）以及现有的监控/监督流程（例如 NRPE 和 collectd）是很好的。

Linux 内核功能[🔗](#linux-kernel-capabilities)
------------------------------------------

By default, Docker starts containers with a restricted set of capabilities. What does that mean?

Capabilities turn the binary “root/non-root” dichotomy into a fine-grained access control system. Processes (like web servers) that just need to bind on a port below 1024 do not need to run as root: they can just be granted the `net_bind_service` capability instead. And there are many other capabilities, for almost all the specific areas where root privileges are usually needed.

This means a lot for container security; let’s see why!

Typical servers run several processes as `root`, including the SSH daemon, `cron` daemon, logging daemons, kernel modules, network configuration tools, and more. A container is different, because almost all of those tasks are handled by the infrastructure around the container:

*   SSH access are typically managed by a single server running on the Docker host;
*   `cron`, when necessary, should run as a user process, dedicated and tailored for the app that needs its scheduling service, rather than as a platform-wide facility;
*   log management is also typically handed to Docker, or to third-party services like Loggly or Splunk;
*   hardware management is irrelevant, meaning that you never need to run `udevd` or equivalent daemons within containers;
*   network management happens outside of the containers, enforcing separation of concerns as much as possible, meaning that a container should never need to perform `ifconfig`, `route`, or ip commands (except when a container is specifically engineered to behave like a router or firewall, of course).

This means that in most cases, containers do not need “real” root privileges _at all_. And therefore, containers can run with a reduced capability set; meaning that “root” within a container has much less privileges than the real “root”. For instance, it is possible to:

*   deny all “mount” operations;
*   deny access to raw sockets (to prevent packet spoofing);
*   deny access to some filesystem operations, like creating new device nodes, changing the owner of files, or altering attributes (including the immutable flag);
*   deny module loading;
*   and many others.

This means that even if an intruder manages to escalate to root within a container, it is much harder to do serious damage, or to escalate to the host.

This doesn’t affect regular web apps, but reduces the vectors of attack by malicious users considerably. By default Docker drops all capabilities except [those needed](https://github.com/moby/moby/blob/master/oci/caps/defaults.go#L6-L19), an allowlist instead of a denylist approach. You can see a full list of available capabilities in [Linux manpages](https://man7.org/linux/man-pages/man7/capabilities.7.html).

One primary risk with running Docker containers is that the default set of capabilities and mounts given to a container may provide incomplete isolation, either independently, or when used in combination with kernel vulnerabilities.

Docker supports the addition and removal of capabilities, allowing use of a non-default profile. This may make Docker more secure through capability removal, or less secure through the addition of capabilities. The best practice for users would be to remove all capabilities except those explicitly required for their processes.

Docker Content Trust Signature Verification[🔗](#docker-content-trust-signature-verification)
---------------------------------------------------------------------------------------------

The Docker Engine can be configured to only run signed images. The Docker Content Trust signature verification feature is built directly into the `dockerd` binary.  
This is configured in the Dockerd configuration file.

To enable this feature, trustpinning can be configured in `daemon.json`, whereby only repositories signed with a user-specified root key can be pulled and run.

This feature provides more insight to administrators than previously available with the CLI for enforcing and performing image signature verification.

For more information on configuring Docker Content Trust Signature Verificiation, go to [Content trust in Docker](https://docs.docker.com/engine/security/trust/).

Other kernel security features[🔗](#other-kernel-security-features)
-------------------------------------------------------------------

Capabilities are just one of the many security features provided by modern Linux kernels. It is also possible to leverage existing, well-known systems like TOMOYO, AppArmor, SELinux, GRSEC, etc. with Docker.

While Docker currently only enables capabilities, it doesn’t interfere with the other systems. This means that there are many different ways to harden a Docker host. Here are a few examples.

*   You can run a kernel with GRSEC and PAX. This adds many safety checks, both at compile-time and run-time; it also defeats many exploits, thanks to techniques like address randomization. It doesn’t require Docker-specific configuration, since those security features apply system-wide, independent of containers.
*   If your distribution comes with security model templates for Docker containers, you can use them out of the box. For instance, we ship a template that works with AppArmor and Red Hat comes with SELinux policies for Docker. These templates provide an extra safety net (even though it overlaps greatly with capabilities).
*   You can define your own policies using your favorite access control mechanism.

Just as you can use third-party tools to augment Docker containers, including special network topologies or shared filesystems, tools exist to harden Docker containers without the need to modify Docker itself.

As of Docker 1.10 User Namespaces are supported directly by the docker daemon. This feature allows for the root user in a container to be mapped to a non uid-0 user outside the container, which can help to mitigate the risks of container breakout. This facility is available but not enabled by default.

Refer to the [daemon command](https://docs.docker.com/engine/reference/commandline/dockerd/#daemon-user-namespace-options) in the command line reference for more information on this feature. Additional information on the implementation of User Namespaces in Docker can be found in [this blog post](https://integratedcode.us/2015/10/13/user-namespaces-have-arrived-in-docker/).

Conclusions[🔗](#conclusions)
-----------------------------

Docker containers are, by default, quite secure; especially if you run your processes as non-privileged users inside the container.

You can add an extra layer of safety by enabling AppArmor, SELinux, GRSEC, or another appropriate hardening system.

If you think of ways to make docker more secure, we welcome feature requests, pull requests, or comments on the Docker community forums.

*   [Use trusted images](https://docs.docker.com/engine/security/trust/)
*   [Seccomp security profiles for Docker](https://docs.docker.com/engine/security/seccomp/)
*   [AppArmor security profiles for Docker](https://docs.docker.com/engine/security/apparmor/)
*   [On the Security of Containers (2014)](https://medium.com/@ewindisch/on-the-security-of-containers-2c60ffe25a9e)
*   [Docker swarm mode overlay network security model](https://docs.docker.com/network/overlay/)