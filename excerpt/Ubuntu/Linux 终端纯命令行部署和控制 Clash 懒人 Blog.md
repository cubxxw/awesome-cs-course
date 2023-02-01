> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [nekocat.top](https://nekocat.top/clash/)

> 自行前往上获取对应版本的下载链接，以 linux-amd64-v1.11.4 为例下载并重命名为 clash.gzwget-Oclash.gzhttps://github.com/Dreamacro/clas......

**下载 clash**
------------

自行前往 [Github](https://github.com/Dreamacro/clash/) 上获取对应版本的下载链接，以 linux-amd64-v1.11.4 为例

下载并重命名为 clash.gz

编辑 clash 文件

此时 clash 已经能运行了，但无法正常启动，因为缺少`Country.mmdb`文件

网上一个说法是`Country.mmdb`得放在~/.config/clash 路径下，不管那么多，直接复制进去以防万一

**获取配置文件**
----------

clash 运行时需要加载配置文件 (也就是需要订阅链接中的内容)。先获取配置文件，直接在浏览器打开你的[订阅链接] 看看内容

正常情况下浏览器中显示的应该是明文显示的配置信息，如图所示![](https://nekocat.top/wp-content/uploads/2022/07/img_62d55a71f3aa5.png)

此时可以用 wget 获取配置文件

但一些代理商的订阅链接打开是没有规律的乱码，此时就自己新建并编辑配置文件，把 windows clash 中已订阅的配置文件内容复制进去。利用 windows clash --- 配置 --- 你的代理商 --- 鼠标右键编辑 --- 全选内容并复制 (ctrl a 然后 ctrl c) 该方法获取内容，然后输入到 config.yaml 中并保存

终端运行 clash 后需要使用 ui 进行交互，先把配置文件处理好，需要修改的一般就三处，参考下面的配置

**测试 clash**
------------

上述操作后，在 / opt/clash 内应该有三个文件

![](https://nekocat.top/wp-content/uploads/2022/07/img_62d5654e83b83.png)

启动 clash

如图所示表示启动正常

![](https://nekocat.top/wp-content/uploads/2022/07/img_62d565cd80ef6.png)

![](https://nekocat.top/wp-content/uploads/2022/07/img_62d565d7ae256.png)

使用 ctrl+c 停止 clash  

**启动 clash 并使用 ui**
-------------------

运行 calsh 后，一旦关闭终端会导致 clash 也关闭，所以使用 nohup 命令后台运行 clash

进入 ui

浏览器访问网址 [http://clash.razord.top/](http://clash.razord.top/)，根据配置文件填入对应信息，然后选择节点。用下面命令测试能否使用

使用后台运行 clash 后，关闭 clash 使用下面命令

**设置终端代理，并配置变量**
----------------

终端默认是直连的，设置代理命令如下

取消终端代理

本次终端设置代理后，下次打开终端依旧需要重新设置，每次输入太麻烦，直接添加变量解决

在文件中添加下面的内容，其中 7890 为你设置的端口。“proxy”和 “unproxy” 为你喜欢的变量名字，一个用于开启代理，一个用于关闭

编译一下

以后终端代理直接终端输入`proxy`即可。取消用`unproxy`。查看终端代理状态：

**使用变量启动 clash**
----------------

上面的终端代理可以用变量快速进行，同理 clash 也可以快速启动。不过上面的命令是在 / opt/clash 目录下且该目录有配置文件才能正确执行，所以变量的命令需要将路径都写为绝对路径。

在文件中添加下面的内容。“clash”和 “unclash” 为你喜欢的变量名字，一个用于开启 clash，一个用于关闭

后面的意思是将输出文件写入到 / dev/null 下，这个目录会丢弃所有写入数据，就是垃圾站，而数字是 linux 的重定向，里面的数字含义如下所示

*   1：标准输出, 在一般使用时，默认的是标准输出；
*   2：表示错误信息输出。

这里整句话含义是将错误信息重定向到标准输出，其他信息丢进 / dev/null

不要忘了编译

此时就可用`clash`和`unclash`命令来启动和关闭 clash，通过命令`ps -ef | grep clash`来判断 clash 是否运行，测试一下

**clash 使用本地 ui**
-----------------

上面的 ui 是 clash 官方提供的，但可能出现无法直连的情况，不如直接部署本地 ui 好。先克隆项目

为了方便使用把文件夹命名为 ui

本地有 ui 文件后，只需要修改 clash 的配置文件即可

在配置文件中加上`external-ui`参数

参考配置

之后启动 clash，访问你的 ip 地址: 端口 / ui 即可

为代理添加用户认证 / 允许其他主机访问
--------------------

有时使用其他主机，为它配置代理不方便，可用浏览器自带的代理设置功能。此时，只需开放先前布置的端口即可。但这样很容易被其他人盗用，所以应该给代理配置中加上用户名和密码认证

此时，使用代理的 url 应该如下

![](https://nekocat.top/wp-content/uploads/2022/09/img_632d492cabb55.png)

Url 型

当然如果有些浏览器，是代理服务器依然填写 ip: 端口，然后访问网络时会弹出认证要求填写用户和密码，需要自己判断

**clash 自定义规则**
---------------

规则参数

规则模板为：规则, 域名 / ip, 类型 参考：