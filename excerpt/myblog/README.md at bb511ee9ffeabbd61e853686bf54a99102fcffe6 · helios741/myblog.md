> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [github.com](https://github.com/helios741/myblog/blob/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/README.md)

[](#文章首发)[文章首发](https://github.com/helios741/myblog/tree/new/learn_go/src/2020/0303_k8s_cni)
--------------------------------------------------------------------------------------------

如果您觉得有什么不理解，或者觉得文章有欠缺的地方，请您点击[这里](https://github.com/helios741/myblog/issues/76)提出。我会很感谢您的建议也会解答您的问题。

[](#深入浅出kubernetes中的cni)深入浅出 kubernetes 中的 CNI
==============================================

容器网络模型要解决的问题有两个：

1.  为容器分配 IP 地址
2.  不同容器之间的互通

[](#一docker的网络模型cnm)一、docker 的网络模型 CNM
--------------------------------------

[![](https://github.com/helios741/myblog/raw/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/docker_model.jpg)](/helios741/myblog/blob/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/docker_model.jpg)

docker 的 CNM 网络模型中三个部分，分别为：

*   sandbox：
    *   容器的网络栈，包括对容器接口、路由表以及 DNS 配置的管理。
    *   一个 sandbox 可能包含多个 endpoint。
    *   简单来说就是 linux 的 namespace
*   endpoint：
    *   一端属于网络一端属于 sandbox
    *   简单来说就是 veth pair
*   network：
    *   一组能够相互通信的 endpoint 组成
    *   可以通过 linux bridge 或者 vxlan 实现

对于跨主机网络来说本质上是实现的一层 overlay 的网络。这也是 swarm 网络的实现方式。

[![](https://github.com/helios741/myblog/raw/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/deep_dive_docker_network.webp)](/helios741/myblog/blob/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/deep_dive_docker_network.webp)

图片来自 [DEEP DIVE INTO DOCKER OVERLAY NETWORKS : PART 1](https://blog.d2si.io/2017/04/25/deep-dive-into-docker-overlay-networks-part-1/)，我们可以看出有如下特点：

*   容器的 sandbox 上至少有两个 endpoint
*   gwbridge 是为了访问外部，br0 是为了容器间互通
*   为了跨主机通信还有一个全局的 KV 数据库（这里用的 consul）
*   容器间通信是通过 vxlan 实现的

如果有深入兴趣的可以读读 [Deep dive into docker overlay networks part](https://blog.d2si.io/2017/04/25/deep-dive-into-docker-overlay-networks-part-1/) 系列文章，相信会有很大的收获。

CNM 的设计可以参考 [libnetwork Design](https://github.com/docker/libnetwork/blob/master/docs/design.md)， 我们在下面会讨论 CNM 和 CNI 差别。

[](#二cni的介绍)二、CNI 的介绍
---------------------

CNI 的全称是 Container Network Interface，Google 和 CoreOS 联合定制的网络标准，这个标准基于 [rkt](https://github.com/rkt/rkt) 实现多容器通信的网络模型。

生产中的网络环境可能是多种多样的，有可能是二层连通的，也可能用的公有云的环境，所以各个厂商的网络解决方案百花争鸣，这些解决方案也不能全都集成在 kubelet 的代码中，所以 CNI 就是能让各个网络厂商对接进来的接口。 CNI 的 [SPEC](https://github.com/containernetworking/cni/blob/master/SPEC.md) 兴趣的读者可以看看，下面是 CNI 规范重要的几点：

*   CNI 插件负责连接容器
*   容器就是 linux network namespace
*   CNI 的网络定义以 json 的格式存储
*   有关网络的配置通过 STDIN 的方式传递给 CNI 插件，其他的参数通过环境变量的方式传递
*   CNI 插件是以可执行文件的方式实现的

[](#三如何使用cni)三、如何使用 CNI
-----------------------

通过下面的例子会对 CNI 有一个感性的了解。

把 CNI 的插件来拉下来

```
[root@m7-qatest-k8s128118 opt]# mkdir cni
[root@m7-qatest-k8s128118 opt]# cd cni/
[root@m7-qatest-k8s128118 cni]# curl -O -L https://github.com/containernetworking/cni/releases/download/v0.4.0/cni-amd64-v0.4.0.tgz
[root@m7-qatest-k8s128118 cni]# tar -xzvf cni-amd64-v0.4.0.tgz
[root@m7-qatest-k8s128118 cni]# ll
总用量 70756
-rwxr-xr-x 1 root root  5924584 1月  14 2017 bridge
-rw-r--r-- 1 root root 16066400 3月   2 21:59 cni-amd64-v0.4.0.tgz
-rwxr-xr-x 1 root root  3614840 1月  14 2017 cnitool
-rwxr-xr-x 1 root root 10354296 1月  14 2017 dhcp
-rwxr-xr-x 1 root root  3684624 1月  14 2017 flannel
-rwxr-xr-x 1 root root  4008016 1月  14 2017 host-local
-rwxr-xr-x 1 root root  5308904 1月  14 2017 ipvlan
-rwxr-xr-x 1 root root  5033704 1月  14 2017 loopback
-rwxr-xr-x 1 root root  5334832 1月  14 2017 macvlan
-rwxr-xr-x 1 root root  3400872 1月  14 2017 noop
-rwxr-xr-x 1 root root  5910424 1月  14 2017 ptp
-rwxr-xr-x 1 root root  3791288 1月  14 2017 tuning

```

创建一个 namespace：

```
[root@m7-qatest-k8s128118 cni]# ip netns add 1234567890

```

新增 CNI 的配置文件：

```
cat > mybridge.conf <<"EOF"
{
    "cniVersion": "0.2.0",
    "name": "mybridge",
    "type": "bridge",
    "bridge": "cni_bridge0",
    "isGateway": true,
    "ipMasq": true,
    "hairpinMode":true,
    "ipam": {
        "type": "host-local",
        "subnet": "10.15.20.0/24",
        "routes": [
            { "dst": "0.0.0.0/0" },
            { "dst": "1.1.1.1/32", "gw":"10.15.20.1"}
        ]
    }
}
EOF

```

其中：

*   cniVersion： CNI 规范的版本
*   name： 这个网络的名字叫 mybridge
*   type：使用 brige 插件
*   isGateway：如果是 true，为网桥分配 ip 地址，以便连接到它的容器可以将其作为网关
*   ipMasq：在插件支持的情况的，设置 ip 伪装。当宿主机充当的网关无法路由到分配给容器的 IP 子网的网关的时候，这个参数是必须有的。
*   ipam：
    *   type：IPAM 可执行文件的名字
    *   要分配给容器的子网
    *   routes
        *   dst： 目的子网
        *   gw：到达目的地址的下一跳 ip 地址，如果不指定则为默认网关
*   hairpinMode: 让网络设备能够让数据包从一个端口发进来一个端口发出去 更多配置信息请参考：[Network Configuration](https://github.com/containernetworking/cni/blob/master/SPEC.md#network-configuration)

将刚才新建的 1234567890 的 namespace 加入到 network 上

```
[root@m7-qatest-k8s128118 cni]# CNI_COMMAND=ADD CNI_CONTAINERID=1234567890 CNI_NETNS=/var/run/netns/1234567890 CNI_IFNAME=eth12 CNI_PATH=`pwd` ./bridge < mybridge.conf
2020/03/02 22:14:57 Error retriving last reserved ip: Failed to retrieve last reserved ip: open /var/lib/cni/networks/mybridge/last_reserved_ip: no such file or directory
{
    "ip4": {
        "ip": "10.15.20.2/24",
        "gateway": "10.15.20.1",
        "routes": [
            {
                "dst": "0.0.0.0/0"
            },
            {
                "dst": "1.1.1.1/32",
                "gw": "10.15.20.1"
            }
        ]
    },
    "dns": {}
}

```

查看新建的 namespace 的网络配置

```
[root@m7-qatest-k8s128118 cni]# ip netns exec 1234567890  ip a
1: lo: <LOOPBACK> mtu 65536 qdisc noop state DOWN group default qlen 1
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
3: eth12@if1137099: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
    link/ether 0a:58:0a:0f:14:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 10.15.20.2/24 scope global eth12
       valid_lft forever preferred_lft forever
    inet6 fe80::34da:9fff:febe:f332/64 scope link
       valid_lft forever preferred_lft forever
[root@m7-qatest-k8s128118 cni]# ip netns exec 1234567890 route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.15.20.1      0.0.0.0         UG    0      0        0 eth12
1.1.1.1         10.15.20.1      255.255.255.255 UGH   0      0        0 eth12
10.15.20.0      0.0.0.0         255.255.255.0   U     0      0        0 eth12
[root@m7-qatest-k8s128118 cni]# ip netns exec 1234567890 ifconfig
eth12: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.15.20.2  netmask 255.255.255.0  broadcast 0.0.0.0
        inet6 fe80::34da:9fff:febe:f332  prefixlen 64  scopeid 0x20<link>
        ether 0a:58:0a:0f:14:02  txqueuelen 0  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 9  bytes 738 (738.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

```

[](#四cni原理)四、CNI 原理
-------------------

CNI 的原理主要分为两个部分：

*   二进制插件配置 POD 的网络栈（runtime）：给 POD 插上网线
*   Deamon 进程实现网络互通（plugin）： 给 POD 连上网络

[![](https://github.com/helios741/myblog/raw/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/cni.png)](/helios741/myblog/blob/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/cni.png)

cni 的插件可以分为下面三类（这些插件官网已经独立出一个 repo，有兴趣可以查看：[containernetworking/plugins](https://github.com/containernetworking/plugins/tree/master/plugins)）：

*   Main 插件：用来创建具体的网络设备的二进制文件，包括：
    *   bridge： 在宿主机上创建网桥然后通过 veth pair 的方式连接到容器
    *   macvlan：虚拟出多个 macvtap，每个 macvtap 都有不同的 mac 地址
    *   ipvlan：和 macvlan 相似，也是通过一个主机接口虚拟出多个虚拟网络接口，不同的是 ipvlan 虚拟出来的是共享 MAC 地址，ip 地址不同
    *   loopback： lo 设备（将回环接口设置成 up）
    *   ptp： Veth Pair 设备
    *   vlan： 分配 vlan 设备
    *   host-device： 移动宿主上已经存在的设备到容器中
*   IPAM(IP Address Management) 插件: 负责分配 IP 地址
    *   dhcp： 宿主机上运行的守护进程，代表容器发出 DHCP 请求
    *   host-local： 使用提前分配好的 IP 地址段来分配
    *   static：用于为容器分配静态的 IP 地址，主要是调试使用
*   Meta 插件： 由 CNI 社区维护的内部插件
    *   flannel: 专门为 Flannel 项目提供的插件
    *   tuning：通过 sysctl 调整网络设备参数的二进制文件
    *   portmap：通过 iptables 配置端口映射的二进制文件
    *   bandwidth：使用 Token Bucket Filter (TBF) 来进行限流的二进制文件
    *   firewall：通过 iptables 或者 firewalled 添加规则控制容器的进出流量

CNI 的思想就是在 kubelet 启动 infra 容器后，就可以直接调用 CNI 插件为这个 infra 容器的 Network Namespace 配置符合预期的网络栈。

注： 一个 Network Namespace 的网络栈包括：网卡（Network interface）、回环设备（Loopback Device）、路由表（Routing Table）和 iptables 规则。

通过这些插件我们就能看出，如果给 kubernetes 实现一个容器网络方案，有两部分要做（下面以 flannel 为例）：

*   （创建网络）实现网络方案本身。也就是实现 flanneld 进程，包括创建和配置 flannel.1 设备、配置宿主机路由、配置 ARP 和 FDB 表里面的信息
*   （将容器加入网络）实现该网络方案对应的 CNI 插件。配置 infra 容器的网络栈，并把它连接到 CNI 网桥上

CNI 的原理如下图： [![](https://github.com/helios741/myblog/raw/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/cni-process.png)](/helios741/myblog/blob/bb511ee9ffeabbd61e853686bf54a99102fcffe6/src/2020/0303_k8s_cni/cni-process.png)

*   在宿主机安装网络方案本身：flannel 启动后会在每台宿主机上生成它对应的 CNI 配置文件，来告诉 k8s 要用 flannel 作为容器网络方案

```
# cat /etc/cni/net.d/10-flannel.conflist
{
  "name": "cbr0",
  "plugins": [
    {
      "type": "flannel",
      "delegate": {
        "hairpinMode": true,
        "isDefaultGateway": true
      }
    },
    {
      "type": "portmap",
      "capabilities": {
        "portMappings": true
      }
    }
  ]
}

```

*   kubelet： 网络的处理不是在 kubelet 的主干代码中，而是在具体的 CRI 中实现的
*   CRI(docker-shim)： docker-shim 是 kubelet 的默认值 CRI 实现，在 kubelet 的代码中能找到它，当然也可以使用其他的 CRI 实现（比如 kata、rkt）我们后面文章专门说 CRI
*   CRI 加载`/etc/cni/net.d`下的插件：目前不支持多个插件混用，但是允许在 CNI 的配置文件中通过 plugins 字段，定义多个插件合作
    *   比如在第一步里面的 _flannel_ 和 _portmap_ 分别完成 “配置容器网络” 和“配置端口映射”的操作
    *   docker-shim 把 CNI 配置文件加载之后把将列表中的第一个插件（flannel 插件）作为默认插件
*   准备 CNI 插件参数，包含两部分：
    1.  CRI 设置的一组环境变量：
        *   CNI_COMMAND： ADD/DEL，ADD 表示将容器加入网络，DEL 则相反（通过 veth pair 实现）
        *   CNI_IFNAME：容器里网卡的名字，比如 eth0
        *   CNI_NETNS：POD 的 Network Namespace 文件路径
        *   CNI_CONTAINERID： 容器 ID
        *   CNI_PATH： CNI 插件的路径
    2.  CRI 从 CNI 配置中加载信息（完整配置可参考 [Network Configuration](https://github.com/containernetworking/cni/blob/master/SPEC.md#network-configuration)）：
        
        *   docker-shim 会把 Network Configuration 以 json 的数据格式，通过 STDIN 的方式传递给 Flannel CNI 插件
        *   第一步中 flannel 插件配置文件中的 delegate 字段的意思是，CNI 插件会调用 delegate 指定的某个 CNI 插件来完成（Flannel 调用的 Delegate 插件就是 CNI bridge 插件）
        *   flannel CNI 插件就是把 dockershim 传过来的配置文件进行补充，比如将 Delegate 的 type 设置为 bridge，将 Delegate 的 IPAM 的字段设置为 host-local
        *   经过 flannel CNI 补充之后，完整的 Delegate 文件如下：
        
        ```
        {
        	"hairpinMode":true,
        	"ipMasq":false,
        	"ipam":{
        		"routes":[
        			{
        				"dst":"10.244.0.0/16"
        			}
        		],
        		"subnet":"10.244.1.0/24",
        		"type":"host-local"
        	},
        	isDefaultGateway":true,
        	"isGateway":true,
        	"mtu":1410,
        	"name":"cbr0",
        	"type":"bridge"
        }
        
        ```
        
        *   ipam 字段里面的信息，比如 _10.244.0.0/16_ 读取自 Flannel 在宿主机上生成的 Flannel 配置文件（/run/flannel/subnet.env ）
*   传递给 CNI 插件：经过上述步骤后，得到 CNI 配置的参数，接下来，Flannel CNI 插件就会调用 CNI bridhe 插件，有了上一步骤的两部分配置（环境变量 + Network Configration），CNI brige 插件就能代替 Flannel CNI 插件 “执行将容器加入网络操作”
*   执行将容器加入网络操作：
    
    1.  检查 CNI 网桥是否存在，如果没有就创建
    
    ```
    # 宿主机
    ip link add cni0 type bridg
    ip link set cni0 up
    
    ```
    
    2.  CNI bridge 插件会通过 infra 容器的 Network Namespace 文件进入 Network Namespace，创建 veth pair 设备
    
    ```
    # 在容器里
    # 创建一对 Veth Pair 设备。其中一个叫作 eth0，另一个叫作 vethb4963f3
    ip link add eth0 type veth peer name vethb4963f3
    
    # 启动 eth0 设备
    ip link set eth0 up
    
    # 将 Veth Pair 设备的另一端(也就是 vethb4963f3 设备)放到宿主机(也就是 Host Namespace)里
    ip link set vethb4963f3 netns $HOST_NS
    # 通过 Host Namespace，启动宿主机上的 vethb4963f3 设备
    ip netns exec $HOST_NS ip link set vethb4963f3 up
    
    ```
    
    3.  CNI bridge 插件就可以把 vethb4963f3 设备连接在 CNI 网桥上
    
    ```
    # 在宿主机上
    ip link set vethb4963f3 master cni0
    
    ```
    
    4.  CNI bridge 插件会为 cni0 网桥设置 Hairpin Mode
    5.  调用 ipam 插件从网段中分配一个 ip，把 ip 添加到容器的 eth0 网卡同时设置默认路由
    
    ```
    # 在容器里
    ip addr add 10.244.0.2/24 dev eth0
    ip route add default via 10.244.0.1 dev eth0
    
    ```
    
    6.  CNI Bridge 插件为 CNI 网桥添加 ip 地址
    
    ```
    # 在宿主机上
    ip addr add 10.244.0.1/24 dev cni0
    
    ```
    
    7.  所有操作结束之后，CNI 插件会把容器的 IP 等信息返回给 dockershim，然后被 kubelet 添加到 POD 的 status 字段

[](#五为什么有cnm还要有cni呢)五、为什么有 CNM 还要有 CNI 呢
----------------------------------------

<table><thead><tr><th align="center">特点</th><th align="center">CNM</th><th align="center">CNI</th></tr></thead><tbody><tr><td align="center">标准规范</td><td align="center"><a href="https://github.com/docker/libnetwork">Libnetwork</a></td><td align="center"><a href="https://github.com/containernetworking/cni">cni</a></td></tr><tr><td align="center">最小单元</td><td align="center">容器</td><td align="center">POD</td></tr><tr><td align="center">对守护进程的依赖</td><td align="center">依赖 dockerd</td><td align="center">不依赖任何守护进程</td></tr><tr><td align="center">跨主通信</td><td align="center">要依赖外部 KV 数据库</td><td align="center">用本身的 KV 的数据库</td></tr><tr><td align="center">灵活程度</td><td align="center">被 docker 绑架</td><td align="center">插件可随意替换</td></tr></tbody></table>

CNI 的设计更加符合 kubernetes 的容器设计模式，即把一组容器看作一个整体 (POD)。当 POD 启动的时候最先启动的肯定是 infra 容器，infra 容器会创建 network namespace，后续创建的容器都是加入这个 network namespace。

CNI 的设计更加 kubernetes 的分层架构，具体来说就是遇到问题分一层，大家可能还会想到 CRI 的设计，PV/PVC。当然这也是架构设计中常用的方案，目的是解决各个模块（组件）之间的耦合。大家可以回想一下 CRI 的发展过程，以前在 kubelet 的代码中是有 docker-shim 和 rkt 两种容器的实现，每次新增特性还要加两套，如果有了新的容器运行时（比如 kata）那还得加入到 kubelet 的代码中么？所以就干脆搞一个接口，谁想用什么的容器运行时根据这个接口实现就行了，当然在 kubelet 中也集成了默认的 CRI 实现，即 dockershim 关于 CRI 的事情，我们在后面的文章中细谈。

CNI 的设计能够提供给不同插件相互组合的机会，比如在 Main 插件中，ipvlan 和 macvlan 都能和 IPAM 中的插件使用。

[](#六总结)六、总结
------------

本文从先介绍了 docker 的容器网络解决方案 CNM，然后从 CNI 的起源介绍 CNI 的原理，最后介绍到为什么 kubernetes 选择了 CNI 不是选择 CNM。本文作为一篇入门进阶文章没有对 CNI 具体网络插件（比如 calico，flannel）进行详细的解释原理，以及不同的业务场景如何对他们进行选择，后续会写一遍这样的文章对不同的网络实现方案进行对比选择。下一遍文章会从 k8s 的源码级别解析 CNI。

如果您觉得有什么不理解，或者觉得文章有欠缺的地方，请您点击[这里](https://github.com/helios741/myblog/issues/76)提出。我会很感谢您的建议也会解答您的问题。

[](#参考)参考
---------

*   [浅聊几种主流 Docker 网络的实现原理](https://www.infoq.cn/article/9vfPPfZPrXLM4ssLlxSR)
*   [DEEP DIVE INTO DOCKER OVERLAY NETWORKS : PART 1（深入理解 Docker 的 Overlay 网络 1）](https://www.jianshu.com/p/3b9389084701)
*   极客时间《深入剖析 kubernetes》
*   [Understanding CNI (Container Networking Interface)](http://www.dasblinkenlichten.com/understanding-cni-container-networking-interface/)
*   [Using CNI with Docker](http://www.dasblinkenlichten.com/using-cni-docker/)
*   [THE CONTAINER NETWORKING LANDSCAPE: CNI FROM COREOS AND CNM FROM DOCKER](https://thenewstack.io/container-networking-landscape-cni-coreos-cnm-docker/)
*   [Kubernetes 网络插件 CNI 调研整理](https://yucs.github.io/2017/12/06/2017-12-6-CNI/)
*   [深入理解 CNI](https://www.cnblogs.com/YaoDD/p/7419383.html)
*   [《CNI specification》翻译](https://www.cnblogs.com/YaoDD/p/7405725.html)
*   [Extension conventions](https://github.com/containernetworking/cni/blob/master/CONVENTIONS.md)
*   [CNM design](https://github.com/docker/libnetwork/blob/master/docs/design.md)
*   [introduction-to-cni](https://kccncna19.sched.com/event/Uaif/introduction-to-cni-the-container-network-interface-project-bryan-boreham-weaveworks-dan-williams-red-hat)