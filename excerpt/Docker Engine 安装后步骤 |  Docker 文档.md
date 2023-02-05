> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [docs.docker.com](https://docs.docker.com/engine/install/linux-postinstall/)

> Linux 的可选安装后步骤

这些可选的安装后过程向您展示了如何配置 Linux 主机以更好地与 Docker 一起工作。

以非 root 用户身份管理 Docker [🔗](#manage-docker-as-a-non-root-user)
-------------------------------------------------------------

Docker 守护进程绑定到 Unix 套接字，而不是 TCP 端口。默认情况下，它 `root`是拥有 Unix 套接字的用户，其他用户只能使用 `sudo`. Docker 守护进程始终以`root`用户身份运行。

如果您不想在`docker`命令前加上`sudo`，请创建一个名为 的 Unix 组`docker`并将用户添加到其中。当 Docker 守护进程启动时，它会创建一个可供`docker`组成员访问的 Unix 套接字。在某些 Linux 发行版上，系统会在使用包管理器安装 Docker Engine 时自动创建此组。在这种情况下，您无需手动创建组。

> **警告**
> 
> 该`docker`组向用户授予根级权限。有关这如何影响系统安全性的详细信息，请参阅 [Docker 守护进程攻击面](https://docs.docker.com/engine/security/#docker-daemon-attack-surface)。

> **笔记**
> 
> 要在没有 root 权限的情况下运行 Docker，请参阅 [以非 root 用户身份运行 Docker 守护程序（无根模式）](https://docs.docker.com/engine/security/rootless/)。

要创建`docker`组并添加您的用户：

1.  创建`docker`组。
    
2.  将您的用户添加到`docker`组中。
    
    ```
    $ sudo usermod -aG docker $USER
    
    
    ```
    
3.  注销并重新登录，以便重新评估您的组成员资格。
    
    > 如果您在虚拟机中运行 Linux，可能需要重新启动虚拟机才能使更改生效。
    
    您还可以运行以下命令来激活对组的更改：
    
4.  验证您是否可以在`docker`没有`sudo`.
    
    此命令下载测试图像并在容器中运行它。当容器运行时，它会打印一条消息并退出。
    
    `sudo`如果您在将用户添加到组之前最初运行 Docker CLI 命令`docker`，您可能会看到以下错误：
    
    ```
    警告：加载配置文件时出错：/home/user/.docker/config.json -
    stat /home/user/.docker/config.json: 权限被拒绝
    
    
    ```
    
    此错误表示`~/.docker/` 目录的权限设置不正确，因为之前使用了该`sudo`命令。
    
    要解决此问题，请删除该`~/.docker/`目录（它会自动重新创建，但所有自定义设置都会丢失），或者使用以下命令更改其所有权和权限：
    
    ```
    $ sudo chown "$USER":"$USER" /home/"$USER"/.docker -R
    $ sudo chmod g+rwx "$HOME/.docker" -R
    
    
    ```
    

配置 Docker 以使用 systemd 启动启动[🔗](#configure-docker-to-start-on-boot-with-systemd)
-------------------------------------------------------------------------------

许多现代 Linux 发行版使用[systemd](https://docs.docker.com/config/daemon/systemd/)来管理在系统启动时启动哪些服务。在 Debian 和 Ubuntu 上，Docker 服务默认在启动时启动。要使用 systemd 为其他 Linux 发行版在启动时自动启动 Docker 和 containerd，请运行以下命令：

```
$ sudo systemctl 启用 docker.service
$ sudo systemctl 启用 containerd.service


```

要停止此行为，请`disable`改用。

```
$ sudo systemctl 禁用 docker.service
$ sudo systemctl 禁用 containerd.service


```

如果您需要添加 HTTP 代理，为 Docker 运行时文件设置不同的目录或分区，或进行其他自定义，请参阅 [自定义您的 systemd Docker 守护程序选项](https://docs.docker.com/config/daemon/systemd/)。

配置默认日志记录驱动程序[🔗](#configure-default-logging-driver)
---------------------------------------------------

Docker 提供[日志记录驱动程序](https://docs.docker.com/config/containers/logging/)，用于收集和查看主机上运行的所有容器的日志数据。默认日志记录驱动程序`json-file`将日志数据写入主机文件系统上的 JSON 格式文件。随着时间的推移，这些日志文件的大小会不断扩大，从而可能导致磁盘资源耗尽。

为避免日志数据过度使用磁盘的问题，请考虑以下选项之一：

*   配置`json-file`日志记录驱动程序以打开 [日志轮换](https://docs.docker.com/config/containers/logging/json-file/)
*   使用 [替代日志记录驱动程序](https://docs.docker.com/config/containers/logging/configure/#configure-the-default-logging-driver) ，例如 默认执行日志轮换的[“本地”日志记录驱动程序](https://docs.docker.com/config/containers/logging/local/)
*   使用将日志发送到远程日志记录聚合器的日志记录驱动程序。

下一步[🔗](#next-steps)
--------------------

*   查看[入门](https://docs.docker.com/get-started/)培训模块，了解如何构建映像并将其作为容器化应用程序运行。
*   查看[使用 Docker 开发中](https://docs.docker.com/develop/)的主题，了解如何使用 Docker 构建新应用程序。