> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [cloud.tencent.com](https://cloud.tencent.com/developer/article/2164600)

> 笔者利用手头几台云服务器搭建 k8s 集群，由于这几台云服务属于不同的云服务厂商，无法搭建局域网环境的 k8s 集群，故笔者搭建的是公网环境的 k8s 集群，在...

笔者利用手头几台[云服务器](https://cloud.tencent.com/product/cvm?from=10680)搭建 k8s 集群，由于这几台云服务属于不同的云服务厂商，无法搭建局域网环境的 k8s 集群，故笔者搭建的是公网环境的 k8s 集群，在此做个记录, 以下均在 ubuntu 20.04 环境下进行

创建虚拟网卡
------

由于主机内看到的只有内网 IP, 而且几台云服务器位于不同的内网, 直接搭建会将内网 IP 注册进集群导致搭建不成功。解决方案：使用虚拟网卡绑定公网 IP, 使用该公网 IP 来注册集群

```
# 所有主机都要创建虚拟网卡，并绑定对应的公网 ip
sudo ifconfig eth0:1 139.198.108.103

```

> 该设置方式在重启服务器后失效，持久化需要将配置写入`/etc/network/interfaces`或`/etc/netplan/50-cloud-init.yaml`

更新 /etc/hosts
-------------

将集群所有节点的公网 ip 和 hostname 对应关系写入`/etc/hosts`中

关闭 swap 分区
----------

```
sudo swapoff -a
# 注释掉 fstab 中 swap 分区的挂载信息
sudo sed -ri 's/.*swap.*/#&/' /etc/fstab

```

配置 systemd 来管理 [docker](https://cloud.tencent.com/product/tke?from=10680) 的 cgroup
----------------------------------------------------------------------------------

```
sudo mkdir /etc/docker
cat <<EOF | sudo tee /etc/docker/daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}
EOF

sudo systemctl enable docker
sudo systemctl daemon-reload
sudo systemctl restart docker

```

允许 iptables 检查桥接流量
------------------

```
cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
br_netfilter
EOF

cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sudo sysctl --system

```

开启相关端口
------

### master 节点

<table><thead><tr><th></th><th></th><th></th><th></th><th></th></tr></thead><tbody><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>6443</p></td><td><p>Kubernetes API 服务器</p></td><td><p>所有组件</p></td></tr><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>2379-2380</p></td><td><p>etcd 服务器客户端 API</p></td><td><p>kube-apiserver、etcd</p></td></tr><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>10250</p></td><td><p>Kubelet API</p></td><td><p>kubelet 自身、控制平面组件</p></td></tr><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>10251</p></td><td><p>kube-scheduler</p></td><td><p>kube-scheduler 自身</p></td></tr><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>10252</p></td><td><p>kube-controller-manager</p></td><td><p>kube-controller-manager 自身</p></td></tr></tbody></table>

### worker 节点

<table><thead><tr><th></th><th></th><th></th><th></th><th></th></tr></thead><tbody><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>10250</p></td><td><p>Kubelet API</p></td><td><p>kubelet 自身、控制平面组件</p></td></tr><tr><td><p>TCP</p></td><td><p>入站</p></td><td><p>30000-32767</p></td><td><p>NodePort 服务</p></td><td><p>所有组件</p></td></tr></tbody></table>

### 所有节点

<table><thead><tr><th></th><th></th><th></th><th></th><th></th></tr></thead><tbody><tr><td><p>UDP</p></td><td><p>入站</p></td><td><p>8472</p></td><td><p>vxlan Overlay 网络通信</p></td><td><p>Overlay 网络</p></td></tr></tbody></table>

安装 kubeadm、kubelet 和 kubectl
----------------------------

*   kubeadm：用来初始化集群的指令
*   kubelet：在集群中的每个节点上用来启动 Pod 和容器等
*   kubectl：用来与集群通信的命令行工具

```
sudo apt install -y apt-transport-https ca-certificates curl

curl -s https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | sudo apt-key add
echo "deb https://mirrors.aliyun.com/kubernetes/apt/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt update
sudo apt install -y kubelet kubeadm kubectl
sudo apt-mark hold kubelet kubeadm kubectl

```

修改 kubelet 启动参数
---------------

添加 kubelet 的启动参数`--node-ip=公网IP`， 每个主机都要添加并指定对应的公网 ip, 添加了这一步才能使用公网 ip 来注册进集群

```
sudo vi /etc/systemd/system/kubelet.service.d/10-kubeadm.conf

```

初始化 master 节点
-------------

```
sudo kubeadm init \
    --kubernetes-version=v1.22.0 \
    --apiserver-advertise-address=139.198.108.103 \
    --control-plane-endpoint=139.198.108.103 \
    --image-repository registry.cn-hangzhou.aliyuncs.com/google_containers \
    --service-cidr=10.10.0.0/16 \
    --pod-network-cidr=10.122.0.0/16

```

报错及解决：

ERROR ImagePull: failed to pull image registry.cn-hangzhou.aliyuncs.com/google_containers/coredns:v1.8.4: output: Error response from daemon: manifest for registry.cn-hangzhou.aliyuncs.com/google_containers/coredns:v1.8.4 not found: manifest unknown: manifest unknown

解决：

1. 从官方镜像拉取 coredns

```
1
docker pull coredns/coredns

```

2. 打 tag，修改镜像名

```
1
docker tag coredns/coredns:latest registry.cn-hangzhou.aliyuncs.com/google_containers/coredns:v1.8.4

```

3. 删除多余镜像

```
 1
docker rmi coredns/coredns:latest

```

初始化成功后输出如下

![](https://ask.qcloudimg.com/http-save/yehe-4867275/d00298ebd1495f9b4ec98f395b335077.png?imageView2/2/w/1620)

根据输出提示执行以下命令

```
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

```

记录下该命令，用于之后将 worker 节点加入集群

```
kubeadm join 139.198.108.103:6443 --token hnop0o.t16okler9962rroq \
        --discovery-token-ca-cert-hash sha256:64c85683ac63f820e64787ed47674c7d470574feebcfe0f2142f45699cfe8ec6

```

### 修改`kube-apiserver`参数

在 master 节点，kube-apiserver 添加`--bind-address`和修改`--advertise-addres`

```
sudo vi /etc/kubernetes/manifests/kube-apiserver.yaml

```

![](https://ask.qcloudimg.com/http-save/yehe-4867275/fda75e3a5e369b8f4b94c45729f243a5.png?imageView2/2/w/1620)

安装 flannel 网络
-------------

在 master 节点执行

```
# 下载 flannel 的 yaml 配置文件
wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

```

修改 yaml 配置文件，添加两处地方和修改一处地方

```
args:
 - --public-ip=$(PUBLIC_IP)
 - --iface=eth0

env:
 - name: PUBLIC_IP
   valueFrom:
     fieldRef:
       fieldPath: status.podIP

net-conf.json: |
  {
    "Network": "10.122.0.0/16",
      "Backend": {
        "Type": "vxlan"
      }
  }

```

![](https://ask.qcloudimg.com/http-save/yehe-4867275/94330d26fd9f3626cdece14fa5302aeb.png?imageView2/2/w/1620)

![](https://ask.qcloudimg.com/http-save/yehe-4867275/65d6d590dc5aec83ecb2a331d9c70354.png?imageView2/2/w/1620)

![](https://ask.qcloudimg.com/http-save/yehe-4867275/06832e7f9f71cdd238aebbfb4bcdfb4f.png?imageView2/2/w/1620)

修改完后，开始安装网络插件

```
kubectl apply -f kube-flannel.yml

```

执行如下命令，等待一会儿，直到所有的容器组处于 Running 状态

```
watch -n 1 kubectl get pod -n kube-system -o wide

```

![](https://ask.qcloudimg.com/http-save/yehe-4867275/6cf3cf2861ecac2908af4780d446f5db.png?imageView2/2/w/1620)

worker 节点加入集群
-------------

使用初始化 master 节点成功后输出的命令来加入集群，或者在 master 节点重新打印 token 和加入命令

```
kubeadm token create --print-join-command

```

在 worker 节点执行命令加入集群

```
sudo kubeadm join 139.198.108.103:6443 --token wm2039.cf8qnsrgyip6qvsz --discovery-token-ca-cert-hash sha256:64c85683ac63f820e64787ed47674c7d470574feebcfe0f2142f45699cfe8ec6

```

![](https://ask.qcloudimg.com/http-save/yehe-4867275/717e5479af32eaf1ef14d45ee72d27fc.png?imageView2/2/w/1620)

等待所有需要加入的节点加入成功后，在 master 节点执行下面命令，并等待所有节点状态变为 Ready （笔者搭建的一主两从的集群，均使用的公网 ip)

测试
--

master 节点执行下面命令来部署 nginx

```
kubectl create deploy my-nginx --image=nginx
kubectl expose deploy my-nginx --port=80 --type=NodePort

```

查看 nginx 部署的 pod 信息，可以看到 Pod ip，以及部署在哪一个节点上

尝试 ping Pod 的 ip，如果无法 ping 通，执行

```
sudo iptables -P FORWARD ACCEPT

```

> docker 从 1.13 版本开始，可能将 **iptables FORWARD chain** 的默认策略设置为了 **DROP**，该设置会导致 ping 其他 node 上的 Pod ip 失败

查看 nginx 对外暴露的端口

可以看到对外暴露的端口是 30950, 如果分别通过集群内所有节点的公网 ip 访问这个端口，能请求到 nginx 主页，则证明部署成功

安装 Dashboard
------------

下载 dashboard 的 yaml 描述文件

```
wget https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.1/aio/deploy/recommended.yaml

```

修改下载下来的 recommend.yaml

```
kind: Service
apiVersion: v1
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kubernetes-dashboard
spec:
  ports:
    - port: 443
      targetPort: 8443
      nodePort: 30001    # 指定对外暴露的 port
  type: NodePort         # 指定服务类型为 NodePort
  selector:
    k8s-app: kubernetes-dashboard

```

应用修改后的 yaml 文件，创建 dashboard 服务

```
kubectl apply -f recommended.yaml

```

现在可以通过 30001 端口访问 dashboard 的登录页面了

如果使用的是 chrome 浏览器并出现了以上页面，可以鼠标点击页面任意地方，然后键盘输入 thisisunsafe。正常访问会进入 Login 页面，提示需要授权

接下来创建 admin 用户来获取 token

```
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard

```

创建 admin 用户并获取 token

```
kubectl apply -f dashboard-admin.yaml
kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep admin-user | awk '{print $1}')

```

在 Login 页面输入 token 后就可以成功访问 dashboard 页面了

**本文作者：** Ifan Tsai  (菜菜) **本文链接：** https://www.caiyifan.cn/p/d6990d10.html **版权声明：** 本文采用 [知识共享署名 - 非商业性使用 - 相同方式共享 4.0 国际许可协议](https://creativecommons.org/licenses/by-nc-sa/4.0/) 进行许可。转载请注明出处！