> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/helios741/myblog/issues/47)

[文章首发](https://github.com/helios741/myblog/issues/47)

[原文地址](https://www.lifewire.com/layers-of-the-osi-model-illustrated-818017)

[![](https://user-images.githubusercontent.com/12036324/59970252-bfac9300-9593-11e9-8b36-54cc89abada4.png)](https://user-images.githubusercontent.com/12036324/59970252-bfac9300-9593-11e9-8b36-54cc89abada4.png)

开发式系统通信互联（OSI）模型
================

开发式系统通信互联（OSI）模型定义了一种实现了在层之间实现网络协议，并且控制一层传递到下一层的网络架构。当今它最主要的用途就是作为教学工具。它在概念上将计算机网络划分为七个不同的层次。低层处理电信号、二进制帧以及通过网络路由这些数据。高层涉及到网络的请求和相应、数据表示以及从用户角度网络协议的展示。

物理层
---

[![](https://user-images.githubusercontent.com/12036324/59970408-36e42600-9598-11e9-86f1-cc38a5bb12fd.png)](https://user-images.githubusercontent.com/12036324/59970408-36e42600-9598-11e9-86f1-cc38a5bb12fd.png)

第一层，在 OSI 模型中的物理层是控制数字数据[位（bits）](https://www.lifewire.com/definition-of-bit-816250)从发送设备（源）的物理层通过网络通讯媒介到达接收（目的）设备物理层。物理层的技术包括：[以太网电缆（ Ethernet cables）](https://www.lifewire.com/what-is-an-ethernet-cable-817548)和[令牌环网络（Token Ring networks）](https://www.lifewire.com/what-is-token-ring-817952)。除此之外，[集线器（hub）](https://www.lifewire.com/ethernet-and-network-hubs-816358)和[其他中继器（repeaters）](https://www.lifewire.com/definition-of-repeater-816359)以及电缆连接器也是工作在网络层的标准网络设备。

在物理层，使用物理介质（电压、无线电频率、红外线或者普通脉冲波）支持的信号类型传输数据。

数据链路层
-----

[![](https://user-images.githubusercontent.com/12036324/59970669-f89e3500-959e-11e9-90fb-257178f0ffc5.png)](https://user-images.githubusercontent.com/12036324/59970669-f89e3500-959e-11e9-90fb-257178f0ffc5.png)

当从物理层获得到数据的时候，数据链路层检查物理传输错误，然后将位打包位数据帧。数据链路层也管理物理寻址方案，例如以太网的 [MAC](https://www.lifewire.com/media-access-control-mac-817973) 地址，控制不同的网络设备对物理媒介的访问。以为数据链路层是 OSI 模型中最复杂的层，它也被拆为 “媒体访问控制” 和“逻辑连接控制”两个子层。

网络层
---

[![](https://user-images.githubusercontent.com/12036324/59970746-96deca80-95a0-11e9-8801-29080a3b0d7d.png)](https://user-images.githubusercontent.com/12036324/59970746-96deca80-95a0-11e9-8801-29080a3b0d7d.png)

网络层在数据链路层之上增加了路由的概念。当数据到达网络层，包含在帧内的源地址和目的地址将要被检查，为了确定数据是否到达了目的地。如果确认后到达的是目的地，那么网络层将数据格式化为能够传递到传输层数数据包。否则，网络层将要更新目的地址并且将这个数据帧退回下层。

为了支撑路由，网络层保存了设备在网路中的逻辑地址，例如 [ip Address](https://www.lifewire.com/what-is-an-ip-address-2625920)。网络层也管理逻辑地址和物理地址的映射。在 IP 网络中，这个映射是通过 [Address Resolution Protocol (ARP)](https://www.lifewire.com/address-resolution-protocol-817941) 完成的。

传输层
---

[![](https://user-images.githubusercontent.com/12036324/59970887-9f84d000-95a3-11e9-85d7-921fe0737fc3.png)](https://user-images.githubusercontent.com/12036324/59970887-9f84d000-95a3-11e9-85d7-921fe0737fc3.png)

传输层通过连接的网络传输数据。[TCP](https://www.lifewire.com/transmission-control-protocol-and-internet-protocol-816255) 是传输第四层网络协议 [network protocol](https://www.lifewire.com/definition-of-protocol-network-817949) 最常见的例子。不同的传输协议可能支持一系列可选的功能，比如：错误回复，流量控制以及重传的支持。

会话层
---

[![](https://user-images.githubusercontent.com/12036324/59971306-0d34fa00-95ac-11e9-8e5d-fc882407e552.png)](https://user-images.githubusercontent.com/12036324/59971306-0d34fa00-95ac-11e9-8e5d-fc882407e552.png)

会话层管理启动和拆除网络连接事件的顺序和流程。在第五层，它支持多种类型的连接，这些连接能够被动态的创建和运行在各个网络中

表示层
---

[![](https://user-images.githubusercontent.com/12036324/59971336-c693cf80-95ac-11e9-89f0-4d284c9eeb28.png)](https://user-images.githubusercontent.com/12036324/59971336-c693cf80-95ac-11e9-89f0-4d284c9eeb28.png)

表示层是 OSI 模型中功能最简单的一层。它处理消息数据的语法格式，例如格式转换以及支持应用层的加密和解密操作。

应用层
---

[![](https://user-images.githubusercontent.com/12036324/59971520-2c358b00-95b0-11e9-89d9-5f50ddcfd886.png)](https://user-images.githubusercontent.com/12036324/59971520-2c358b00-95b0-11e9-89d9-5f50ddcfd886.png)

应用层提供网络服务给端用户的应用。网络服务通常是用户数据一起使用的协议。例如 web 浏览器应用，应用层协议 [HTTP](https://www.lifewire.com/hypertext-transfer-protocol-817944) 打包发送和接受页面所需要的数据。第七层想表示层提供数据（并从中获取数据）。