> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [zhuanlan.zhihu.com](https://zhuanlan.zhihu.com/p/406917270)

`Linux的终端比Windows的终端香的很，有什么办法可以在Windows下使用Linux的终端开进行便捷使用吗？这个时候就需要用到微软大发的WSL2了！`

安装 WSL2
-------

安装 WSL2 有两个方式，一个是直接在 Windows Store 中进行安装，另一个就是通过下载好的 WSL 包进行安装。

### Windows Store 安装

通过 Windows Store 直接搜索 Ubuntu，进行安装即可。

![](https://pic4.zhimg.com/v2-8d2a675b92b66dddf72ddb1aab01a743_b.jpg)

### 下载文件安装

登录下面微软官方文档

[https://docs.microsoft.com/zh-cn/windows/wsl/install-manual](https://link.zhihu.com/?target=https%3A//docs.microsoft.com/zh-cn/windows/wsl/install-manual)

通过这个网页，我们可以找到各种版本的发行版。直接下载下来。

![](https://pic2.zhimg.com/v2-e001996fb3a4ddfc0044b5ac810a3169_b.jpg)

将下载好的文件放在任意地方，双击即可安装，需要注意的是`文件放置的位置就是Ubuntu安装的地方，磁盘空间需要预留够`

安装完之后，我们可以在任意界面，输入`wsl`，即可快速进入默认的 Linux 发行版。

![](https://pic1.zhimg.com/v2-4133f0df4292f3e1e0cb0f6877b7a440_b.jpg)

这个时候我们就可以尽情的使用 Linux 终端来操作我们 Windows 下的文件系统了

![](https://pic1.zhimg.com/v2-4f9324c1de7f27807175ad19142eafa8_b.jpg)

WSL2 迁移
-------

如果我们是通过 Windows Store 进行安装的，就会默认安装到 C 盘，这个对于我们是不允许的，所以我们需要将其迁移到其他非 C 盘的地方。

1.  终止正在运行的 wsl

```
wsl --shutdown

```

1.  将需要迁移的 Linux，进行导出

```
wsl --export Ubuntu D:/export.tar

```

![](https://pic2.zhimg.com/v2-9d994b97c3c8ea7685865fc54a4e3d45_b.jpg)![](https://pic2.zhimg.com/v2-0408d71a0b613056e646b7501fd23d59_b.jpg)

1.  导出完成之后，就需要将原有的分发进行卸载

```
wsl --unregister Ubuntu

```

1.  然后将导出的文件放到需要保存的地方，进行导入即可

```
wsl --import Ubuntu D:\export\ D:\export.tar --version 2

```