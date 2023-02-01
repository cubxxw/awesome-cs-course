> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.51cto.com](https://blog.51cto.com/laok8/2609755)

> 在 Ubuntu Linux 中命令行工具使用代理 v2ray privoxy proxychains 学习，在 UbuntuLinux 中很多命令行工具, 如 apt-getpipgitwgetcurl 等, 需要访问外......

#### 在 Ubuntu Linux 中命令行工具使用代理的必要性:

在 Ubuntu Linux 中很多命令行工具, 如 apt-get pip git wget curl 等, 需要访问外网服务器 (主要是 github). 但是由于 github 没有国内的服务器以及其他特殊的原因, 网络链接经常失败, 这时只能求助于代理.

#### Step 1: 配置 V2ray socks5

当前最好的方法是租 v2ray 的服务器, 并在 ubuntu 配置客户端.

首先下载脚本:

```
wget https://install.direct/go.sh


```

然后执行脚本安装 V2Ray:

```
sudo bash go.sh


```

go.sh 会下载 github 上的 v2ray-linux-64.zip 文件, 在国内经常出现网络连接失败的情况, 因此需要修改 go.sh 内容, 并把外网下载的 v2ray-linux-64.zip 复制到 / tmp/v2ray/v2ray.zip .

复制配置文件 config.json 到 / etc/v2ray. config.json 可以直接从 windows 的 v2rayN 客户端夹中直接复制过来.

启动 v2ray 服务

```
systemctl restart v2ray


```

socks5 代理已经启动, 默认端口是 10808, (socks5://127.0.0.1:10808).

#### Step 2: 配置 http 代理 privoxy

有些命令行工具只能使用 http 代理, 不能使用 socks5 代理, 因此需要用 privoxy 把 socks5 代理转换为 http 代理.

安装 privoxy

```
sudo apt install -y privoxy


```

修改配置文件 / etc/privoxy/config

```
listen-address  :10809
forward-socks5    /    127.0.0.1:10808  .


```

启动 privoxy 服务

```
systemctl restart privoxy


```

http 代理已经启动, 默认端口是 10809, (http://127.0.0.1:10809).

#### Step 3: 配置 proxychains

有些 linux 命令行工具没有配置代理的方法, 可以用 proxychains 强制应用使用代理网络.

安装 proxychains

```
sudo apt install -y proxychains


```

修改配置文件 / etc/proxychains.conf 最后一行

```
socks5  127.0.0.1 10808


```

使用 proxychains 方法, 在命令前加上 proxychains, 如:

```
proxychains apt update


```

#### python pip 使用 http 代理加速

##### 方法 1:

```
pip3 install -r requirement.txt --proxy http://127.0.0.1:10809


```

##### 方法 2:

```
proxychains pip3 install -r requirement.txt


```

#### git 使用 http 代理加速

##### 方法 1:

```
git config --global http.proxy http://127.0.0.1:10809
git config --global https.proxy http://127.0.0.1:10809


```

取消设置

```
git config --global --unset http.proxy


```

##### 方法 2:

```
proxychains git clone https://github.com/opencv/opencv.git


```

#### docker 使用 http 代理加速

```
mkdir /etc/systemd/system/docker.service.d
/etc/systemd/system/docker.service.d/http-proxy.conf
------
[Service]
Environment="HTTP_PROXY=http://127.0.0.1:10809/"
------
# systemctl daemon-reload
# systemctl restart docker


```