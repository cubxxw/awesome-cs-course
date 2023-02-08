> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [kubernetes.io](https://kubernetes.io/docs/tasks/administer-cluster/kubelet-in-userns/)

> 功能状态：Kubernetes v1.22 [alpha] 本文档描述了如何运行 Kubernetes Node 组件......

**特征状态：** `Kubernetes v1.22 [alpha]`

[本文档描述了如何使用用户命名空间](https://man7.org/linux/man-pages/man7/user_namespaces.7.html)在没有 root 权限的情况下运行 Kubernetes 节点组件，例如 kubelet、CRI、OCI 和 CNI 。

这种技术也称为_无根模式_。

**笔记：**

本文档描述了如何以非根用户身份运行 Kubernetes 节点组件（以及 Pod）。

如果您只是想了解如何以非 root 用户身份运行 pod，请参阅[SecurityContext](https://kubernetes.io/docs/tasks/configure-pod-container/security-context/)。

在你开始之前[](#before-you-begin)
---------------------------

您的 Kubernetes 服务器必须为 1.22 或更高版本。要检查版本，请输入`kubectl version`.

*   [启用 Cgroup v2](https://rootlesscontaine.rs/getting-started/common/cgroup2/)
*   [使用用户会话启用 systemd](https://rootlesscontaine.rs/getting-started/common/login/)
*   [配置多个 sysctl 值，具体取决于主机 Linux 发行版](https://rootlesscontaine.rs/getting-started/common/sysctl/)
*   [确保您的非特权用户列在`/etc/subuid`和`/etc/subgid`](https://rootlesscontaine.rs/getting-started/common/subuid/)
*   启用`KubeletInUserNamespace` [功能门](https://kubernetes.io/docs/reference/command-line-tools-reference/feature-gates/)

在 Rootless Docker/Podman 中运行 Kubernetes[](#running-kubernetes-inside-rootless-docker-podman)
--------------------------------------------------------------------------------------------

### 种类[](#kind)

[kind](https://kind.sigs.k8s.io/)支持在 Rootless Docker 或 Rootless Podman 中运行 Kubernetes。

请参阅[使用无根 Docker 运行类](https://kind.sigs.k8s.io/docs/user/rootless/)。

### 迷你立方体[](#minikube)

[minikube](https://minikube.sigs.k8s.io/)还支持在 Rootless Docker 或 Rootless Podman 中运行 Kubernetes。

请参阅 Minikube 文档：

*   [无根码头工人](https://minikube.sigs.k8s.io/docs/drivers/docker/)
*   [无根豆荚人](https://minikube.sigs.k8s.io/docs/drivers/podman/)

在非特权容器中运行 Kubernetes[](#running-kubernetes-inside-unprivileged-containers)
--------------------------------------------------------------------------

**注意：** 本节链接到提供 Kubernetes 所需功能的第三方项目。Kubernetes 项目作者不对这些按字母顺序列出的项目负责。要将项目添加到此列表，请在提交更改之前阅读[内容指南。](https://kubernetes.io/docs/contribute/style/content-guide/#third-party-content)[更多信息。](#third-party-content-disclaimer)

### 系统箱[](#sysbox)

[Sysbox](https://github.com/nestybox/sysbox)是一个开源容器运行时（类似于“runc”），它支持在与 Linux 用户命名空间隔离的非特权容器中运行系统级工作负载，例如 Docker 和 Kubernetes。

有关详细信息，请参阅[Sysbox 快速入门指南：Kubernetes-in-Docker](https://github.com/nestybox/sysbox/blob/master/docs/quickstart/kind.md)。

Sysbox 支持在非特权容器内运行 Kubernetes，无需 Cgroup v2 且无需`KubeletInUserNamespace`功能门。它通过在容器内公开特制的文件系统以及其他几种高级操作系统虚拟化技术来实现`/proc`这一点。`/sys`

直接在主机上运行无根 Kubernetes[](#running-rootless-kubernetes-directly-on-a-host)
------------------------------------------------------------------------

**注意：** 本节链接到提供 Kubernetes 所需功能的第三方项目。Kubernetes 项目作者不对这些按字母顺序列出的项目负责。要将项目添加到此列表，请在提交更改之前阅读[内容指南。](https://kubernetes.io/docs/contribute/style/content-guide/#third-party-content)[更多信息。](#third-party-content-disclaimer)

### K3s[](#k3s)

[K3s](https://k3s.io/)实验性支持无根模式。

使用方法参见[以无根模式运行 K3s](https://rancher.com/docs/k3s/latest/en/advanced/#running-k3s-with-rootless-mode-experimental)。

### 用户网[](#usernetes)

[Usernetes](https://github.com/rootless-containers/usernetes)是 Kubernetes 的参考发行版，`$HOME`无需 root 权限即可安装在目录下。

Usernetes 支持将 containerd 和 CRI-O 作为 CRI 运行时。Usernetes 支持使用 Flannel (VXLAN) 的多节点集群。

有关用法，请参阅[Usernetes 存储库](https://github.com/rootless-containers/usernetes)。

在用户命名空间中手动部署运行 kubelet 的节点[](#userns-the-hard-way)
--------------------------------------------------

本节提供了在用户命名空间中手动运行 Kubernetes 的提示。

**注意：**本节旨在供 Kubernetes 发行版的开发人员而非最终用户阅读。

### 创建用户命名空间[](#creating-a-user-namespace)

第一步是创建[用户命名空间](https://man7.org/linux/man-pages/man7/user_namespaces.7.html)。

如果您尝试在用户命名空间容器（例如 Rootless Docker/Podman 或 LXC/LXD）中运行 Kubernetes，那么您已经准备就绪，可以转到下一小节。

否则你必须自己创建一个用户命名空间，通过调用`unshare(2)`with `CLONE_NEWUSER`。

也可以使用命令行工具取消共享用户命名空间，例如：

*   [`unshare(1)`](https://man7.org/linux/man-pages/man1/unshare.1.html)
*   [RootlessKit](https://github.com/rootless-containers/rootlesskit)
*   [成为根](https://github.com/giuseppe/become-root)

取消共享用户命名空间后，您还必须取消共享其他命名空间，例如装载命名空间。

您不需要_在_取消共享挂载命名空间后调用`chroot()`nor ，但是，您必须_在_命名空间中的多个目录上挂载可写文件系统。`pivot_root()`

至少，以下目录需要在命名空间中是可写_的_（而不是在命名空间_之外_）：

*   `/etc`
*   `/run`
*   `/var/logs`
*   `/var/lib/kubelet`
*   `/var/lib/cni`
*   `/var/lib/containerd`（对于容器）
*   `/var/lib/containers`（对于 CRI-O）

### 创建委托的 cgroup 树[](#creating-a-delegated-cgroup-tree)

除了用户命名空间之外，您还需要具有 cgroup v2 的可写 cgroup 树。

**注意：** Kubernetes 支持在用户命名空间中运行 Node 组件需要 cgroup v2。不支持 Cgroup v1。

如果您尝试在基于 systemd 的主机上的 Rootless Docker/Podman 或 LXC/LXD 中运行 Kubernetes，那么您已经准备就绪。

否则你必须创建一个具有`Delegate=yes`属性的 systemd 单元来委托具有可写权限的 cgroup 树。

在您的节点上，systemd 必须已经配置为允许委派；有关更多详细信息，请参阅 无根容器文档中的[cgroup v2 。](https://rootlesscontaine.rs/getting-started/common/cgroup2/)

### 配置网络[](#configuring-network)

**注意：** 本节链接到提供 Kubernetes 所需功能的第三方项目。Kubernetes 项目作者不对这些按字母顺序列出的项目负责。要将项目添加到此列表，请在提交更改之前阅读[内容指南。](https://kubernetes.io/docs/contribute/style/content-guide/#third-party-content)[更多信息。](#third-party-content-disclaimer)

节点组件的网络命名空间必须有一个非环回接口，例如可以配置 [slirp4netns](https://github.com/rootless-containers/slirp4netns)、 [VPNKit](https://github.com/moby/vpnkit)或 [lxc-user-nic(1)](https://www.man7.org/linux/man-pages/man1/lxc-user-nic.1.html)。

Pod 的网络命名空间可以使用常规 CNI 插件进行配置。对于多节点网络，已知 Flannel（VXLAN、8472/UDP）可以工作。

kubelet 端口 (10250/TCP) 和`NodePort`服务端口等端口必须从 Node 网络命名空间暴露给具有外部端口转发器（例如 RootlessKit、slirp4netns 或 [socat(1)）](https://linux.die.net/man/1/socat)的主机。

您可以使用 K3s 的端口转发器。 有关更多详细信息，请参阅[以无根模式运行 K3s 。](https://rancher.com/docs/k3s/latest/en/advanced/#known-issues-with-rootless-mode)实现可以在k3s[的`pkg/rootlessports`包中找到。](https://github.com/k3s-io/k3s/blob/v1.22.3+k3s1/pkg/rootlessports/controller.go)

### 配置 CRI[](#configuring-cri)

kubelet 依赖于容器运行时。您应该部署一个容器运行时，例如 containerd 或 CRI-O，并确保它在 kubelet 启动之前在用户命名空间内运行。

*   [集装箱](#cri-0)
*   [克里奥](#cri-1)

从 containerd 1.4 开始支持在用户命名空间中运行 containerd 的 CRI 插件。

在用户命名空间中运行 containerd 需要以下配置。

```
版本 = 2

[插件."io.containerd.grpc.v1.cri"]
# 禁用 AppArmor
  disable_apparmor = 真
# 在设置 oom_score_adj 时忽略错误
  restrict_oom_score_adj = true
# 禁用 hugetlb cgroup v2 控制器（因为 systemd 不支持委派 hugetlb 控制器）
  disable_hugetlb_controller = true

[plugins."io.containerd.grpc.v1.cri".containerd]
# Using non-fuse overlayfs is also possible for kernel >= 5.11, but requires SELinux to be disabled
  snapshotter = "fuse-overlayfs"

[plugins."io.containerd.grpc.v1.cri".containerd.runtimes.runc.options]
# We use cgroupfs that is delegated by systemd, so we do not use SystemdCgroup driver
# (unless you run another systemd in the namespace)
  SystemdCgroup = false


```

The default path of the configuration file is `/etc/containerd/config.toml`. The path can be specified with `containerd -c /path/to/containerd/config.toml`.

Running CRI-O in a user namespace is supported since CRI-O 1.22.

CRI-O requires an environment variable `_CRIO_ROOTLESS=1` to be set.

The following configurations are also recommended:

```
[crio]
  storage_driver = "overlay"
# Using non-fuse overlayfs is also possible for kernel >= 5.11, but requires SELinux to be disabled
  storage_option = ["overlay.mount_program=/usr/local/bin/fuse-overlayfs"]

[crio.runtime]
# We use cgroupfs that is delegated by systemd, so we do not use "systemd" driver
# (unless you run another systemd in the namespace)
  cgroup_manager = "cgroupfs"


```

The default path of the configuration file is `/etc/crio/crio.conf`. The path can be specified with `crio --config /path/to/crio/crio.conf`.

### Configuring kubelet[](#configuring-kubelet)

Running kubelet in a user namespace requires the following configuration:

```
apiVersion: kubelet.config.k8s.io/v1beta1
kind: KubeletConfiguration
featureGates:
  KubeletInUserNamespace: true
# We use cgroupfs that is delegated by systemd, so we do not use "systemd" driver
# (unless you run another systemd in the namespace)
cgroupDriver: "cgroupfs"


```

When the `KubeletInUserNamespace` feature gate is enabled, the kubelet ignores errors that may happen during setting the following sysctl values on the node.

*   `vm.overcommit_memory`
*   `vm.panic_on_oom`
*   `kernel.panic`
*   `kernel.panic_on_oops`
*   `kernel.keys.root_maxkeys`
*   `kernel.keys.root_maxbytes`.

Within a user namespace, the kubelet also ignores any error raised from trying to open `/dev/kmsg`. This feature gate also allows kube-proxy to ignore an error during setting `RLIMIT_NOFILE`.

The `KubeletInUserNamespace` feature gate was introduced in Kubernetes v1.22 with "alpha" status.

Running kubelet in a user namespace without using this feature gate is also possible by mounting a specially crafted proc filesystem (as done by [Sysbox](https://github.com/nestybox/sysbox)), but not officially supported.

### Configuring kube-proxy[](#configuring-kube-proxy)

Running kube-proxy in a user namespace requires the following configuration:

```
apiVersion: kubeproxy.config.k8s.io/v1alpha1
kind: KubeProxyConfiguration
mode: "iptables" # or "userspace"
conntrack:
# Skip setting sysctl value "net.netfilter.nf_conntrack_max"
  maxPerCore: 0
# Skip setting "net.netfilter.nf_conntrack_tcp_timeout_established"
  tcpEstablishedTimeout: 0s
# Skip setting "net.netfilter.nf_conntrack_tcp_timeout_close"
  tcpCloseWaitTimeout: 0s


```

Caveats[](#caveats)
-------------------

*   Most of "non-local" volume drivers such as `nfs` and `iscsi` do not work. Local volumes like `local`, `hostPath`, `emptyDir`, `configMap`, `secret`, and `downwardAPI` are known to work.
    
*   Some CNI plugins may not work. Flannel (VXLAN) is known to work.
    

For more on this, see the [Caveats and Future work](https://rootlesscontaine.rs/caveats/) page on the rootlesscontaine.rs website.

See Also[](#see-also)
---------------------

*   [rootlesscontaine.rs](https://rootlesscontaine.rs/)
*   [Rootless Containers 2020 (KubeCon NA 2020)](https://www.slideshare.net/AkihiroSuda/kubecon-na-2020-containerd-rootless-containers-2020)
*   [Running kind with Rootless Docker](https://kind.sigs.k8s.io/docs/user/rootless/)
*   [Usernetes](https://github.com/rootless-containers/usernetes)
*   [Running K3s with rootless mode](https://rancher.com/docs/k3s/latest/en/advanced/#running-k3s-with-rootless-mode-experimental)
*   [KEP-2033: Kubelet-in-UserNS (aka Rootless mode)](https://github.com/kubernetes/enhancements/tree/master/keps/sig-node/2033-kubelet-in-userns-aka-rootless)

Items on this page refer to third party products or projects that provide functionality required by Kubernetes. The Kubernetes project authors aren't responsible for those third-party products or projects. See the [CNCF website guidelines](https://github.com/cncf/foundation/blob/master/website-guidelines.md) for more details.

You should read the [content guide](https://kubernetes.io/docs/contribute/style/content-guide/#third-party-content) before proposing a change that adds an extra third-party link.