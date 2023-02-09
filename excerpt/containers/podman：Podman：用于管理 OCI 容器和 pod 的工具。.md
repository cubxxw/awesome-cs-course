> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/containers/podman)

> Podman：用于管理 OCI 容器和 Pod 的工具。- containers/podman: Podman: 一个管理工具......

[![](https://raw.githubusercontent.com/containers/common/main/logos/podman-logo-full-vert.png)](https://raw.githubusercontent.com/containers/common/main/logos/podman-logo-full-vert.png)

[](#podman-a-tool-for-managing-oci-containers-and-pods)Podman：管理 OCI 容器和 Pod 的工具
================================================================================

Podman（POD MANager）是一种用于管理容器和镜像、安装到这些容器中的卷以及由容器组构成的 Pod 的工具。Podman 在 Linux 上运行容器，但也可以使用 Podman 管理的虚拟机在 Mac 和 Windows 系统上使用。Podman 基于 libpod，这是一个用于容器生命周期管理的库，也包含在此存储库中。libpod 库提供了用于管理容器、pod、容器映像和卷的 API。

*   [最新版本：4.4.0](https://github.com/containers/podman/releases/tag/v4.4.0)
    *   适用于 Windows 的最新远程客户端
    *   适用于 macOS 的最新远程客户端
    *   适用于 Linux 的最新静态远程客户端

所有版本都经过 GPG 签名。获准发布的团队成员的公钥位于[此处](https://github.com/containers/release-keys/tree/main/podman)。

*   持续集成：
    *   [![](https://camo.githubusercontent.com/d3bc09b5ced367d00ca92baf7a7755a5f0e5bc6350a82d30188de0b2a87940a8/68747470733a2f2f6170692e6369727275732d63692e636f6d2f6769746875622f636f6e7461696e6572732f706f646d616e2e737667)](https://cirrus-ci.com/github/containers/podman/master)
    *   [去文档：![](https://camo.githubusercontent.com/b13f2cb0c3abb6c23c8bff853f8f77ebe30563b206f586a4f15d92362d70a690/68747470733a2f2f676f646f632e6f72672f6769746875622e636f6d2f636f6e7461696e6572732f706f646d616e2f6c6962706f643f7374617475732e737667)](https://godoc.org/github.com/containers/podman/libpod)
    *   [下载](/containers/podman/blob/main/DOWNLOADS.md)

[](#overview-and-scope)概述和范围
----------------------------

在高层次上，Podman 和 libpod 的范围如下：

*   支持多种容器镜像格式，包括 OCI 和 Docker 镜像。
*   全面管理这些镜像，包括从各种来源（包括信任和验证）提取、创建（通过 Containerfile 或 Dockerfile 构建或从容器提交），以及推送到注册表和其他存储后端。
*   容器生命周期的全面管理，包括创建（从映像和分解的根文件系统）、运行、检查点和恢复（通过 CRIU）和删除。
*   使用 Netavark 全面管理容器网络。
*   支持 pod，共享资源并一起管理的容器组。
*   支持在没有 root 或其他提升权限的情况下运行容器和 pod。
*   容器和 Pod 的资源隔离。
*   支持与 Docker 兼容的 CLI 界面，可以在本地和远程系统上运行容器。
*   没有管理器守护进程，以提高安全性和降低空闲时的资源利用率。
*   支持 REST API，提供与 Docker 兼容的接口和公开高级 Podman 功能的改进接口。
*   支持通过 . 运行的虚拟机在 Windows 和 Mac 上运行`podman machine`。

[](#roadmap)路线图
---------------

1.  功能齐全的 GUI 前端`podman machine`
2.  进一步改进`podman generate kube`和`podman play kube`
3.  对 Pod 的改进，包括添加 Pod 级资源限制

[](#communications)通讯
---------------------

如果您认为您在项目中发现了安全问题，请_不要_通过 GitHub 问题跟踪器、邮件列表或 IRC 公开报告该问题。相反，请将包含尽可能多的详细信息的电子邮件发送至`security@lists.podman.io`。这是核心维护者的私人邮件列表。

对于一般问题和讨论，请使用 Podman 的 [频道](https://podman.io/community/#slack-irc-matrix-and-discord)。

对于围绕问题/错误和功能的讨论，您可以使用 GitHub [问题](https://github.com/containers/podman/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc) 和 [PR](https://github.com/containers/podman/pulls?q=is%3Apr+is%3Aopen+sort%3Aupdated-desc) 跟踪系统。

在上还有一个[邮件列表](https://lists.podman.io/archives/)。`lists.podman.io`您可以通过发送`podman-join@lists.podman.io`主题为 的消息来订阅`subscribe`。

[](#rootless)无根
---------------

Podman 可以作为普通用户轻松运行，不需要 setuid 二进制文件。在没有 root 的情况下运行时，Podman 容器使用用户命名空间将容器中的 root 设置为运行 Podman 的用户。Rootless Podman 运行锁定容器时没有运行容器的用户所没有的权限。其中一些限制可以取消（`--privileged`例如，通过 ），但无根容器永远不会比启动它们的用户拥有更多的权限。如果您以用户身份运行 Podman 并`/etc/passwd`从主机挂载，您仍然无法更改它，因为您的用户没有这样做的权限。

几乎所有正常的 Podman 功能都可用，但也有一些[缺点](https://github.com/containers/podman/blob/main/rootless.md)。任何最新的 Podman 版本都应该能够在没有任何额外配置的情况下无根运行，尽管您的操作系统可能需要[安装指南](https://podman.io/getting-started/installation)中详细说明的一些额外配置。

在可以使用无根 Podman 之前，管理员需要进行一些配置，[此处](https://github.com/containers/podman/blob/main/docs/tutorials/rootless_tutorial.md)记录了必要的设置。

[](#podman-desktop)Podman 桌面
----------------------------

[Podman Desktop](https://podman-desktop.io/)是容器组织下的一个新项目，旨在帮助开发人员使用桌面 UI 在本地环境中使用容器。Podman Desktop 仍处于早期阶段，但已经提供了列出镜像、与容器交互（访问日志、获取终端）、连接到注册表（拉取私有镜像、推送镜像）和配置 podman 设置（代理）的功能。该项目在[Github](https://github.com/containers/podman-desktop)上开发，欢迎贡献者。

[](#out-of-scope)超出范围
---------------------

*   将图像专门签名并推送到各种存储后端。有关这些任务，请参阅[Skopeo](https://github.com/containers/skopeo/)。
*   支持用于容器管理的 Kubernetes CRI 接口。[CRI-O](https://github.com/cri-o/cri-o)守护程序专门从事此工作。

[](#oci-projects-plans)OCI 项目计划
-------------------------------

Podman 在不同方面使用 OCI 项目和最佳库：

*   运行时：我们使用[OCI 运行时工具](https://github.com/opencontainers/runtime-tools)来生成 OCI 运行时配置，这些配置可以与任何符合 OCI 标准的运行时一起使用，例如[crun](https://github.com/containers/crun/)和[runc](https://github.com/opencontainers/runc/)。
*   图像：图像管理使用[容器/图像](https://github.com/containers/image)库。
*   存储：容器和图像存储由[containers/storage](https://github.com/containers/storage)管理。
*   网络：通过使用[Netavark](https://github.com/containers/netavark)和[Aardvark](https://github.com/containers/aardvark-dns)提供网络支持。无根网络通过[slirp4netns](https://github.com/rootless-containers/slirp4netns)处理。
*   构建：通过[Buildah](https://github.com/containers/buildah)支持构建。
*   Conmon：[Conmon](https://github.com/containers/conmon)是一个用于监控 OCI 运行时的工具，Podman 和 CRI-O 都使用它。
*   Seccomp：Podman、Buildah 和 CRI-O 的统一[Seccomp](https://github.com/containers/common/blob/main/pkg/seccomp/seccomp.json)策略。

[](#podman-information-for-developers)面向开发人员的 Podman 信息
-------------------------------------------------------

对于博客、发布公告等，请查看[podman.io](https://podman.io)网站！

**[安装说明](/containers/podman/blob/main/install.md)** 有关如何在您的环境中安装 Podman 的信息。

**[OCI Hooks 支持](/containers/podman/blob/main/pkg/hooks/README.md)** 有关 Podman 如何配置[OCI Hooks](https://github.com/opencontainers/runtime-spec/blob/v1.0.2/config.md#posix-platform-hooks)在启动容器时运行的信息。

**[Podman API](https://docs.podman.io/en/latest/_static/api.html)** 关于 Podman REST API 的文档。

**[Podman 命令 Podman 命令](https://podman.readthedocs.io/en/latest/Commands.html)** 的列表，带有指向其手册页的链接，在许多情况下还有显示正在使用的命令的视频。

**[Podman 故障排除指南 Podman](/containers/podman/blob/main/troubleshooting.md)** 的常见问题和解决方案列表。

**[Podman Usage Transfer 运维](/containers/podman/blob/main/transfer.md)** 和开发转移的有用信息，因为它与使用 Podman 的基础设施相关。此页面包含显示 Docker 命令及其 Podman 等效命令的表格。

**[教程](/containers/podman/blob/main/docs/tutorials)** 使用 Podman 的教程。

**[远程客户端](https://github.com/containers/podman/blob/main/docs/tutorials/remote_client.md)** 关于使用 Podman 远程客户端的简要说明。

**[Podman 在 Rootless 环境中的基本设置和使用](https://github.com/containers/podman/blob/main/docs/tutorials/rootless_tutorial.md)** 本教程展示了运行 Rootless Podman 所需的设置和配置。

**[发行说明](/containers/podman/blob/main/RELEASE_NOTES.md)** 最新 Podman 版本的发行说明。

**[贡献](/containers/podman/blob/main/CONTRIBUTING.md)** 关于贡献这个项目的信息。

[](#buildah-and-podman-relationship)Buildah 和 Podman 的关系
--------------------------------------------------------

Buildah 和 Podman 是两个互补的开源项目，可在大多数 Linux 平台上使用，这两个项目都位于 [GitHub.com](https://github.com)上， [此处为 Buildah，](https://github.com/containers/buildah)[此处](https://github.com/containers/podman)为Podman 。Buildah 和 Podman 都是命令行工具，适用于开放容器计划 (OCI) 图像和容器。这两个项目在专业上有所不同。

Buildah 专注于构建 OCI 图像。Buildah 的命令复制了 Dockerfile 中的所有命令。这允许在不需要任何 root 权限的情况下使用和不使用 Dockerfile 构建图像。Buildah 的最终目标是提供一个较低级别的 coreutils 接口来构建图像。在没有 Dockerfiles 的情况下构建图像的灵活性允许将其他脚本语言集成到构建过程中。Buildah 遵循简单的 fork-exec 模型，不作为守护进程运行，但它基于 golang 中的综合 API，可以供应给其他工具。

Podman specializes in all of the commands and functions that help you to maintain and modify OCI images, such as pulling and tagging. It also allows you to create, run, and maintain those containers created from those images. For building container images via Dockerfiles, Podman uses Buildah's golang API and can be installed independently from Buildah.

A major difference between Podman and Buildah is their concept of a container. Podman allows users to create "traditional containers" where the intent of these containers is to be long lived. While Buildah containers are really just created to allow content to be added back to the container image. An easy way to think of it is the `buildah run` command emulates the RUN command in a Dockerfile while the `podman run` command emulates the `docker run` command in functionality. Because of this and their underlying storage differences, you can not see Podman containers from within Buildah or vice versa.

In short, Buildah is an efficient way to create OCI images while Podman allows you to manage and maintain those images and containers in a production environment using familiar container cli commands. For more details, see the [Container Tools Guide](https://github.com/containers/buildah/tree/main/docs/containertools).

[](#podman-hello)[Podman Hello](https://podman.io/images/podman-hello.jpg)
--------------------------------------------------------------------------

```
$ podman run quay.io/podman/hello
Trying to pull quay.io/podman/hello:latest...
Getting image source signatures
Copying blob a6b3126f3807 done
Copying config 25c667d086 done
Writing manifest to image destination
Storing signatures
!... 你好 Podman 世界 ...！

         .--"--.
       / - - \
      / (O) (O) \
   ~~~| -=(,Y,)=- |
    .---. /`\|~~
 ~/ oo \~~~~.----. ~~
  | =(X)= |~ / (O (O) \
   ~~~~~~~ ~| =(Y_)=- |
  ~~~~~~~| 你|~~

项目：https://github.com/containers/podman
网站：https://podman.io
文档：https://docs.podman.io
推特：@Podman_io


```

[](#podman-former-api-varlink)Podman 前 API (Varlink)
----------------------------------------------------

Podman 以前提供了一个基于 Varlink 的 API，用于容器的远程管理。但是，此 API 已被 REST API 取代。从 3.0 版本开始，Varlink 支持已被删除。更多详细信息，您可以查看[此博客](https://podman.io/blogs/2020/01/17/podman-new-api.html)。