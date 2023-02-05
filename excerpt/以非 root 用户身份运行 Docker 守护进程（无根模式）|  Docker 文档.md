> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [docs.docker.com](https://docs.docker.com/engine/security/rootless/)

> 以非 root 用户身份运行 Docker 守护进程（无根模式）

Rootless 模式允许以非 root 用户身份运行 Docker 守护进程和容器，以减轻守护进程和容器运行时中的潜在漏洞。

只要满足[先决条件](#prerequisites)，即使在安装 Docker 守护程序期间，无根模式也不需要 root 权限。

Rootless 模式作为一项实验性功能在 Docker Engine v19.03 中引入。Rootless 模式从 Docker Engine v20.10 的实验中毕业。

它是如何工作[的🔗](#how-it-works)
--------------------------

无根模式在用户命名空间内执行 Docker 守护进程和容器。[`userns-remap`这与mode](https://docs.docker.com/engine/security/userns-remap/)非常相似，除了在`userns-remap`mode 模式下，守护进程本身以 root 权限运行，而在 rootless 模式下，守护进程和容器都在没有 root 权限的情况下运行。

无根模式不使用具有`SETUID`位或文件功能的二进制文件，除了`newuidmap`和`newgidmap`，这是允许在用户命名空间中使用多个 UID/GID 所必需的。

先决条件[🔗](#prerequisites)
------------------------

*   您必须在主机上安装`newuidmap`和。`newgidmap`这些命令由`uidmap`大多数发行版的软件包提供。
    
*   `/etc/subuid`并且`/etc/subgid`应该包含至少 65,536 个用户的从属 UID/GID。在以下示例中，用户`testuser`有 65,536 个从属 UID/GID (231072-296607)。
    

```
$ id -u
1001
$ whoami
测试用户
$ grep ^$(whoami): /etc/subuid
测试用户：231072:65536
$ grep ^$(whoami): /etc/subgid
测试用户：231072:65536


```

### 特定于发行版的提示[🔗](#distribution-specific-hint)

> 注意：我们建议您使用 Ubuntu 内核。

*   Ubuntu
*   德比安 GNU/Linux
*   架构Linux
*   openSUSE 和 SLES
*   CentOS 8、RHEL 8 和 Fedora
*   CentOS 7 和 RHEL 7

*   如果未安装，请安装`dbus-user-session`软件包。运行`sudo apt-get install -y dbus-user-session`并重新登录。
    
*   `overlay2`默认情况下启用存储驱动程序（[特定于 Ubuntu 的内核补丁](https://kernel.ubuntu.com/git/ubuntu/ubuntu-bionic.git/commit/fs/overlayfs?id=3b7da90f28fe1ed4b79ef2d994c81efbc58f1144)）。
    
*   已知可在 Ubuntu 18.04、20.04 和 22.04 上运行。
    

*   如果未安装，请安装`dbus-user-session`软件包。运行`sudo apt-get install -y dbus-user-session`并重新登录。
    
*   对于 Debian 10，添加`kernel.unprivileged_userns_clone=1`到`/etc/sysctl.conf`（或 `/etc/sysctl.d`）并运行`sudo sysctl --system`. Debian 11 不需要此步骤。
    
*   建议安装`fuse-overlayfs`。运行`sudo apt-get install -y fuse-overlayfs`。也可以使用`overlay2`带有 Debian 特定 modprobe 选项`sudo modprobe overlay permit_mounts_in_userns=1`的存储驱动程序，但是，由于[不稳定性](https://github.com/moby/moby/issues/42302)，强烈建议不要使用。
    
*   Rootless docker 需要`slirp4netns`大于的版本`v0.4.0`（`vpnkit`未安装时）。检查你有这个
    
    如果您没有此版本，请下载并安装`sudo apt-get install -y slirp4netns`或下载最新[版本](https://github.com/rootless-containers/slirp4netns/releases)。
    

*   建议安装`fuse-overlayfs`。运行`sudo pacman -S fuse-overlayfs`。
    
*   添加`kernel.unprivileged_userns_clone=1`到`/etc/sysctl.conf`（或 `/etc/sysctl.d`）并运行`sudo sysctl --system`
    

*   建议安装`fuse-overlayfs`。运行`sudo zypper install -y fuse-overlayfs`。
    
*   `sudo modprobe ip_tables iptable_mangle iptable_nat iptable_filter`是必须的。根据配置，其他发行版也可能需要这样做。
    
*   已知可在 openSUSE 15 和 SLES 15 上工作。
    

*   建议安装`fuse-overlayfs`。运行`sudo dnf install -y fuse-overlayfs`。
    
*   你可能需要`sudo dnf install -y iptables`。
    
*   已知可在 CentOS 8、RHEL 8 和 Fedora 34 上运行。
    

*   添加`user.max_user_namespaces=28633`到`/etc/sysctl.conf`（或 `/etc/sysctl.d`）并运行`sudo sysctl --system`。
    
*   `systemctl --user`默认情况下不起作用。`dockerd-rootless.sh`不用systemd直接运行。
    

已知限制[🔗](#known-limitations)
----------------------------

*   仅支持以下存储驱动程序：
    *   `overlay2`（仅当运行内核 5.11 或更高版本，或 Ubuntu 风格的内核时）
    *   `fuse-overlayfs`（仅当使用内核 4.18 或更高版本运行并且`fuse-overlayfs`已安装时）
    *   `btrfs` (only if running with kernel 4.18 or later, or `~/.local/share/docker` is mounted with `user_subvol_rm_allowed` mount option)
    *   `vfs`
*   Cgroup is supported only when running with cgroup v2 and systemd. See [Limiting resources](#limiting-resources).
*   Following features are not supported:
    *   AppArmor
    *   Checkpoint
    *   Overlay network
    *   Exposing SCTP ports
*   To use the `ping` command, see [Routing ping packets](#routing-ping-packets).
*   To expose privileged TCP/UDP ports (<1024), see [Exposing privileged ports](#exposing-privileged-ports).
*   `IPAddress` shown in `docker inspect` is namespaced inside RootlessKit’s network namespace. This means the IP address is not reachable from the host without `nsenter`-ing into the network namespace.
*   Host network (`docker run --net=host`) is also namespaced inside RootlessKit.
*   NFS mounts as the docker “data-root” is not supported. This limitation is not specific to rootless mode.

Install[🔗](#install)
---------------------

> **Note**
> 
> If the system-wide Docker daemon is already running, consider disabling it: `$ sudo systemctl disable --now docker.service docker.socket`

*   With packages (RPM/DEB)
*   Without packages

If you installed Docker 20.10 or later with [RPM/DEB packages](https://docs.docker.com/engine/install), you should have `dockerd-rootless-setuptool.sh` in `/usr/bin`.

Run `dockerd-rootless-setuptool.sh install` as a non-root user to set up the daemon:

```
$ dockerd-rootless-setuptool.sh install
[INFO] Creating /home/testuser/.config/systemd/user/docker.service
...
[INFO] Installed docker.service successfully.
[INFO] To control docker.service, run: `systemctl --user (start|stop|restart) docker.service`
[INFO] To run docker.service on system startup, run: `sudo loginctl enable-linger testuser`

[INFO] Make sure the following environment variables are set (or add them to ~/.bashrc):

export PATH=/usr/bin:$PATH
export DOCKER_HOST=unix:///run/user/1000/docker.sock


```

If `dockerd-rootless-setuptool.sh` is not present, you may need to install the `docker-ce-rootless-extras` package manually, e.g.,

```
$ sudo apt-get install -y docker-ce-rootless-extras


```

If you do not have permission to run package managers like `apt-get` and `dnf`, consider using the installation script available at [https://get.docker.com/rootless](https://get.docker.com/rootless). Since static packages are not available for `s390x`, hence it is not supported for `s390x`.

```
$ curl -fsSL https://get.docker.com/rootless | sh
...
[INFO] Creating /home/testuser/.config/systemd/user/docker.service
...
[INFO] Installed docker.service successfully.
[INFO] To control docker.service, run: `systemctl --user (start|stop|restart) docker.service`
[INFO] To run docker.service on system startup, run: `sudo loginctl enable-linger testuser`

[INFO] Make sure the following environment variables are set (or add them to ~/.bashrc):

export PATH=/home/testuser/bin:$PATH
export DOCKER_HOST=unix:///run/user/1000/docker.sock


```

The binaries will be installed at `~/bin`.

See [Troubleshooting](#troubleshooting) if you faced an error.

Uninstall[🔗](#uninstall)
-------------------------

To remove the systemd service of the Docker daemon, run `dockerd-rootless-setuptool.sh uninstall`:

```
$ dockerd-rootless-setuptool.sh uninstall
+ systemctl --user stop docker.service
+ systemctl --user disable docker.service
Removed /home/testuser/.config/systemd/user/default.target.wants/docker.service.
[INFO] Uninstalled docker.service
[INFO] This uninstallation tool does NOT remove Docker binaries and data.
[INFO] To remove data, run: `/usr/bin/rootlesskit rm -rf /home/testuser/.local/share/docker`


```

Unset environment variables PATH and DOCKER_HOST if you have added them to `~/.bashrc`.

To remove the data directory, run `rootlesskit rm -rf ~/.local/share/docker`.

To remove the binaries, remove `docker-ce-rootless-extras` package if you installed Docker with package managers. If you installed Docker with https://get.docker.com/rootless ([Install without packages](#install)), remove the binary files under `~/bin`:

```
$ cd ~/bin
$ rm -f containerd containerd-shim containerd-shim-runc-v2 ctr docker docker-init docker-proxy dockerd dockerd-rootless-setuptool.sh dockerd-rootless.sh rootlesskit rootlesskit-docker-proxy runc vpnkit


```

Usage[🔗](#usage)
-----------------

### Daemon[🔗](#daemon)

*   With systemd (Highly recommended)
*   Without systemd

The systemd unit file is installed as `~/.config/systemd/user/docker.service`.

Use `systemctl --user` to manage the lifecycle of the daemon:

```
$ systemctl --user start docker


```

To launch the daemon on system startup, enable the systemd service and lingering:

```
$ systemctl --user enable docker
$ sudo loginctl enable-linger $(whoami)


```

Starting Rootless Docker as a systemd-wide service (`/etc/systemd/system/docker.service`) is not supported, even with the `User=` directive.

To run the daemon directly without systemd, you need to run `dockerd-rootless.sh` instead of `dockerd`.

The following environment variables must be set:

*   `$HOME`: the home directory
*   `$XDG_RUNTIME_DIR`: an ephemeral directory that is only accessible by the expected user, e,g, `~/.docker/run`. The directory should be removed on every host shutdown. The directory can be on tmpfs, however, should not be under `/tmp`. Locating this directory under `/tmp` might be vulnerable to TOCTOU attack.

Remarks about directory paths:

*   The socket path is set to `$XDG_RUNTIME_DIR/docker.sock` by default. `$XDG_RUNTIME_DIR` is typically set to `/run/user/$UID`.
*   The data dir is set to `~/.local/share/docker` by default. The data dir should not be on NFS.
*   The daemon config dir is set to `~/.config/docker` by default. This directory is different from `~/.docker` that is used by the client.

### Client[🔗](#client)

You need to specify either the socket path or the CLI context explicitly.

To specify the socket path using `$DOCKER_HOST`:

```
$ export DOCKER_HOST=unix://$XDG_RUNTIME_DIR/docker.sock
$ docker run -d -p 8080:80 nginx


```

To specify the CLI context using `docker context`:

```
$ docker context use rootless
rootless
Current context is now "rootless"
$ docker run -d -p 8080:80 nginx


```

Best practices[🔗](#best-practices)
-----------------------------------

### Rootless Docker in Docker[🔗](#rootless-docker-in-docker)

To run Rootless Docker inside “rootful” Docker, use the `docker:<version>-dind-rootless` image instead of `docker:<version>-dind`.

```
$ docker run -d --name dind-rootless --privileged docker:20.10-dind-rootless


```

The `docker:<version>-dind-rootless` image runs as a non-root user (UID 1000). However, `--privileged` is required for disabling seccomp, AppArmor, and mount masks.

### Expose Docker API socket through TCP[🔗](#expose-docker-api-socket-through-tcp)

To expose the Docker API socket through TCP, you need to launch `dockerd-rootless.sh` with `DOCKERD_ROOTLESS_ROOTLESSKIT_FLAGS="-p 0.0.0.0:2376:2376/tcp"`.

```
$ DOCKERD_ROOTLESS_ROOTLESSKIT_FLAGS="-p 0.0.0.0:2376:2376/tcp" \
  dockerd-rootless.sh \
  -H tcp://0.0.0.0:2376 \
  --tlsverify --tlscacert=ca.pem --tlscert=cert.pem --tlskey=key.pem


```

### Expose Docker API socket through SSH[🔗](#expose-docker-api-socket-through-ssh)

To expose the Docker API socket through SSH, you need to make sure `$DOCKER_HOST` is set on the remote host.

```
$ ssh -l <REMOTEUSER> <REMOTEHOST> 'echo $DOCKER_HOST'
unix:///run/user/1001/docker.sock
$ docker -H ssh://<REMOTEUSER>@<REMOTEHOST> run ...


```

### Routing ping packets[🔗](#routing-ping-packets)

On some distributions, `ping` does not work by default.

Add `net.ipv4.ping_group_range = 0 2147483647` to `/etc/sysctl.conf` (or `/etc/sysctl.d`) and run `sudo sysctl --system` to allow using `ping`.

### Exposing privileged ports[🔗](#exposing-privileged-ports)

To expose privileged ports (<1024), set `CAP_NET_BIND_SERVICE` on `rootlesskit` binary and restart the daemon.

```
$ sudo setcap cap_net_bind_service=ep $(which rootlesskit)
$ systemctl --user restart docker


```

Or add `net.ipv4.ip_unprivileged_port_start=0` to `/etc/sysctl.conf` (or `/etc/sysctl.d`) and run `sudo sysctl --system`.

### Limiting resources[🔗](#limiting-resources)

Limiting resources with cgroup-related `docker run` flags such as `--cpus`, `--memory`, `--pids-limit` is supported only when running with cgroup v2 and systemd. See [Changing cgroup version](https://docs.docker.com/config/containers/runmetrics/) to enable cgroup v2.

If `docker info` shows `none` as `Cgroup Driver`, the conditions are not satisfied. When these conditions are not satisfied, rootless mode ignores the cgroup-related `docker run` flags. See [Limiting resources without cgroup](#limiting-resources-without-cgroup) for workarounds.

If `docker info` shows `systemd` as `Cgroup Driver`, the conditions are satisfied. However, typically, only `memory` and `pids` controllers are delegated to non-root users by default.

```
$ cat /sys/fs/cgroup/user.slice/user-$(id -u).slice/user@$(id -u).service/cgroup.controllers
memory pids


```

To allow delegation of all controllers, you need to change the systemd configuration as follows:

```
# mkdir -p /etc/systemd/system/user@.service.d
# cat > /etc/systemd/system/user@.service.d/delegate.conf << EOF
[Service]
Delegate=cpu cpuset io memory pids
EOF
# systemctl daemon-reload


```

> **Note**
> 
> Delegating `cpuset` requires systemd 244 or later.

#### Limiting resources without cgroup

Even when cgroup is not available, you can still use the traditional `ulimit` and [`cpulimit`](https://github.com/opsengine/cpulimit), though they work in process-granularity rather than in container-granularity, and can be arbitrarily disabled by the container process.

For example:

*   To limit CPU usage to 0.5 cores (similar to `docker run --cpus 0.5`): `docker run <IMAGE> cpulimit --limit=50 --include-children <COMMAND>`
*   To limit max VSZ to 64MiB (similar to `docker run --memory 64m`): `docker run <IMAGE> sh -c "ulimit -v 65536; <COMMAND>"`
    
*   To limit max number of processes to 100 per namespaced UID 2000 (similar to `docker run --pids-limit=100`): `docker run --user 2000 --ulimit nproc=100 <IMAGE> <COMMAND>`

Troubleshooting[🔗](#troubleshooting)
-------------------------------------

### Errors when starting the Docker daemon[🔗](#errors-when-starting-the-docker-daemon)

**[rootlesskit:parent] error: failed to start the child: fork/exec /proc/self/exe: operation not permitted**

This error occurs mostly when the value of `/proc/sys/kernel/unprivileged_userns_clone` is set to 0:

```
$ cat /proc/sys/kernel/unprivileged_userns_clone
0


```

To fix this issue, add `kernel.unprivileged_userns_clone=1` to `/etc/sysctl.conf` (or `/etc/sysctl.d`) and run `sudo sysctl --system`.

**[rootlesskit:parent] error: failed to start the child: fork/exec /proc/self/exe: no space left on device**

This error occurs mostly when the value of `/proc/sys/user/max_user_namespaces` is too small:

```
$ cat /proc/sys/user/max_user_namespaces
0


```

To fix this issue, add `user.max_user_namespaces=28633` to `/etc/sysctl.conf` (or `/etc/sysctl.d`) and run `sudo sysctl --system`.

**[rootlesskit:parent] error: failed to setup UID/GID map: failed to compute uid/gid map: No subuid ranges found for user 1001 (“testuser”)**

This error occurs when `/etc/subuid` and `/etc/subgid` are not configured. See [Prerequisites](#prerequisites).

**could not get XDG_RUNTIME_DIR**

This error occurs when `$XDG_RUNTIME_DIR` is not set.

On a non-systemd host, you need to create a directory and then set the path:

```
$ export XDG_RUNTIME_DIR=$HOME/.docker/xrd
$ rm -rf $XDG_RUNTIME_DIR
$ mkdir -p $XDG_RUNTIME_DIR
$ dockerd-rootless.sh


```

> **Note**: You must remove the directory every time you log out.

On a systemd host, log into the host using `pam_systemd` (see below). The value is automatically set to `/run/user/$UID` and cleaned up on every logout.

**`systemctl --user` fails with “Failed to connect to bus: No such file or directory”**

This error occurs mostly when you switch from the root user to an non-root user with `sudo`:

```
# sudo -iu testuser
$ systemctl --user start docker
Failed to connect to bus: No such file or directory


```

Instead of `sudo -iu <USERNAME>`, you need to log in using `pam_systemd`. For example:

*   Log in through the graphic console
*   `ssh <USERNAME>@localhost`
*   `machinectl shell <USERNAME>@`

**The daemon does not start up automatically**

You need `sudo loginctl enable-linger $(whoami)` to enable the daemon to start up automatically. See [Usage](#usage).

**iptables failed: iptables -t nat -N DOCKER: Fatal: can’t open lock file /run/xtables.lock: Permission denied**

This error may happen with an older version of Docker when SELinux is enabled on the host.

The issue has been fixed in Docker 20.10.8. A known workaround for older version of Docker is to run the following commands to disable SELinux for `iptables`:

```
$ sudo dnf install -y policycoreutils-python-utils && sudo semanage permissive -a iptables_t


```

### `docker pull` errors[🔗](#docker-pull-errors)

**docker: failed to register layer: Error processing tar file(exit status 1): lchown <FILE>: invalid argument**

This error occurs when the number of available entries in `/etc/subuid` or `/etc/subgid` is not sufficient. The number of entries required vary across images. However, 65,536 entries are sufficient for most images. See [Prerequisites](#prerequisites).

**docker: failed to register layer: ApplyLayer exit status 1 stdout: stderr: lchown <FILE>: operation not permitted**

This error occurs mostly when `~/.local/share/docker` is located on NFS.

A workaround is to specify non-NFS `data-root` directory in `~/.config/docker/daemon.json` as follows:

```
{"data-root":"/somewhere-out-of-nfs"}


```

### `docker run` errors[🔗](#docker-run-errors)

**docker: Error response from daemon: OCI runtime create failed: ...: read unix @->/run/systemd/private: read: connection reset by peer: unknown.**

This error occurs on cgroup v2 hosts mostly when the dbus daemon is not running for the user.

```
$ systemctl --user is-active dbus
inactive

$ docker run hello-world
docker: Error response from daemon: OCI runtime create failed: container_linux.go:380: starting container process caused: process_linux.go:385: applying cgroup configuration for process caused: error while starting unit "docker
-931c15729b5a968ce803784d04c7421f791d87e5ca1891f34387bb9f694c488e.scope" with properties [{Name:Description Value:"libcontainer container 931c15729b5a968ce803784d04c7421f791d87e5ca1891f34387bb9f694c488e"} {Name:Slice Value:"use
r.slice"} {Name:PIDs Value:@au [4529]} {Name:Delegate Value:true} {Name:MemoryAccounting Value:true} {Name:CPUAccounting Value:true} {Name:IOAccounting Value:true} {Name:TasksAccounting Value:true} {Name:DefaultDependencies Val
ue:false}]: read unix @->/run/systemd/private: read: connection reset by peer: unknown.


```

To fix the issue, run `sudo apt-get install -y dbus-user-session` or `sudo dnf install -y dbus-daemon`, and then relogin.

If the error still occurs, try running `systemctl --user enable --now dbus` (without sudo).

**`--cpus`, `--memory`, and `--pids-limit` are ignored**

This is an expected behavior on cgroup v1 mode. To use these flags, the host needs to be configured for enabling cgroup v2. For more information, see [Limiting resources](#limiting-resources).

### Networking errors[🔗](#networking-errors)

**`docker run -p` fails with `cannot expose privileged port`**

`docker run -p` fails with this error when a privileged port (<1024) is specified as the host port.

```
$ docker run -p 80:80 nginx:alpine
docker: Error response from daemon: driver failed programming external connectivity on endpoint focused_swanson (9e2e139a9d8fc92b37c36edfa6214a6e986fa2028c0cc359812f685173fa6df7): Error starting userland proxy: error while calling PortManager.AddPort(): cannot expose privileged port 80, you might need to add "net.ipv4.ip_unprivileged_port_start=0" (currently 1024) to /etc/sysctl.conf, or set CAP_NET_BIND_SERVICE on rootlesskit binary, or choose a larger port number (>= 1024): listen tcp 0.0.0.0:80: bind: permission denied.


```

When you experience this error, consider using an unprivileged port instead. For example, 8080 instead of 80.

```
$ docker run -p 8080:80 nginx:alpine


```

To allow exposing privileged ports, see [Exposing privileged ports](#exposing-privileged-ports).

**ping doesn’t work**

Ping does not work when `/proc/sys/net/ipv4/ping_group_range` is set to `1 0`:

```
$ cat /proc/sys/net/ipv4/ping_group_range
1       0


```

For details, see [Routing ping packets](#routing-ping-packets).

**`IPAddress` shown in `docker inspect` is unreachable**

This is an expected behavior, as the daemon is namespaced inside RootlessKit’s network namespace. Use `docker run -p` instead.

**`--net=host` doesn’t listen ports on the host network namespace**

This is an expected behavior, as the daemon is namespaced inside RootlessKit’s network namespace. Use `docker run -p` instead.

**Network is slow**

Docker with rootless mode uses [slirp4netns](https://github.com/rootless-containers/slirp4netns) as the default network stack if slirp4netns v0.4.0 or later is installed. If slirp4netns is not installed, Docker falls back to [VPNKit](https://github.com/moby/vpnkit).

Installing slirp4netns may improve the network throughput. See [RootlessKit documentation](https://github.com/rootless-containers/rootlesskit/tree/v0.13.0#network-drivers) for the benchmark result.

Also, changing MTU value may improve the throughput. The MTU value can be specified by creating `~/.config/systemd/user/docker.service.d/override.conf` with the following content:

```
[Service]
Environment="DOCKERD_ROOTLESS_ROOTLESSKIT_MTU=<INTEGER>"


```

And then restart the daemon:

```
$ systemctl --user daemon-reload
$ systemctl --user restart docker


```

**`docker run -p` does not propagate source IP addresses**

This is because Docker with rootless mode uses RootlessKit’s builtin port driver by default.

The source IP addresses can be propagated by creating `~/.config/systemd/user/docker.service.d/override.conf` with the following content:

```
[Service]
Environment="DOCKERD_ROOTLESS_ROOTLESSKIT_PORT_DRIVER=slirp4netns"


```

And then restart the daemon:

```
$ systemctl --user daemon-reload
$ systemctl --user restart docker


```

Note that this configuration decreases throughput. See [RootlessKit documentation](https://github.com/rootless-containers/rootlesskit/tree/v0.13.0#port-drivers) for the benchmark result.

### Tips for debugging[🔗](#tips-for-debugging)

**Entering into `dockerd` namespaces**

The `dockerd-rootless.sh` script executes `dockerd` in its own user, mount, and network namespaces.

For debugging, you can enter the namespaces by running `nsenter -U --preserve-credentials -n -m -t $(cat $XDG_RUNTIME_DIR/docker.pid)`.