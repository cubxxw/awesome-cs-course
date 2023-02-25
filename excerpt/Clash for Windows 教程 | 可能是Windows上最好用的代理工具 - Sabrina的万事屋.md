> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [merlinblog.xyz](https://merlinblog.xyz/wiki/cfw.html)

> 1. 简介Clash 是一个使用 Go 语言编写，基于规则的跨平台代理软件核心程序。Clash for Windows 是目前在 Windows 上唯一可用的图形化 Clash 分支。通过 Cl...

1. 简介
-----

Clash 是一个使用 Go 语言编写，基于规则的跨平台代理软件核心程序。  
Clash for Windows 是目前在 Windows 上唯一可用的图形化 Clash 分支。通过 Clash API 来配置和控制 Clash 核心程序，便于用户可视化操作和使用。  
**支持的协议：** Vmess, Shadowsocks, Snell , SOCKS5 , ShadowsocksR（在 0.11.2 版本加入）  
【注】：ClashR for Windows 是第三方修改版本，已汉化。支持 SSR 协议，使用方法与原版相同。下载地址请看第 3 节。  
ClashR(for Windows)（Whojave）项目地址：[https://github.com/WhoJave/clash/releases](https://github.com/WhoJave/clash/releases)  

Clash for Windows 从 0.11.2 版本开始原生支持 SSR 协议，ClashR 已经完成了它的历史使命。

2. 整理教程时的系统环境
-------------

Windows 10 1903  
Clash for Windows 0.8.0  

此教程针对手持 Clash 订阅链接的用户，  
在操作前请先在机场官网复制好自己的 Clash 订阅链接。  
文档中的某些内容可能随时间变化而失效。

3. 下载和安装 Clash for Windows
--------------------------

安全漏洞警告

最新版本 v0.20.14 已经修复恶意订阅漏洞问题，请务必更新至最新版本。  
欲了解之前存在的漏洞，请查看该 issue： [https://github.com/Fndroid/clash_for_windows_pkg/issues/3891](https://github.com/Fndroid/clash_for_windows_pkg/issues/3891)

原版 CFW 的 Github 发布地址：[https://github.com/Fndroid/clash_for_windows_pkg/releases](https://github.com/Fndroid/clash_for_windows_pkg/releases)  
本站提供的地址：  
【提示】.7z 文件可以使用 [7-Zip](https://sparanoid.com/lab/7z/) 这个软件进行解压。

  Clash for Windows (v0.20.14) 下载地址汇总：

[https://sabrinathings.lanzouf.com/b01kd8dmd](https://sabrinathings.lanzouf.com/b01kd8dmd)

⭐支持解锁网易云音乐灰色歌曲的版本（不推荐使用）👇

[ClashR for Windows 0.9.4 静海流沙汉化版【支持 SSR | 自带网易云解锁】](https://sabrinathings.lanzoui.com/iDtu4g41s4d)

⭐点击下方文章了解 ClashR 如何解锁网易云音乐灰色歌曲👇  
[https://merlinblog.xyz/wiki/clashr-unblockneteasemusic-locally.html](https://merlinblog.xyz/wiki/clashr-unblockneteasemusic-locally.html)  

由于 Clash for Windows 没有任何有效的数字签名，因此 SmartScreen 可能会弹出提示，请点击「更多信息」，然后选择「仍要运行」。  
采用 VMess 协议的自建节点用户请注意：  
v0.19.5 已跟进最新 Clash 内核，将强制开启 Vmess AEAD 认证，相关详情请参考官方文档：  
[https://www.v2fly.org/config/protocols/vmess.html#vmess-md5-%E8%AE%A4%E8%AF%81%E4%BF%A1%E6%81%AF-%E6%B7%98%E6%B1%B0%E6%9C%BA%E5%88%B6](https://www.v2fly.org/config/protocols/vmess.html#vmess-md5-%E8%AE%A4%E8%AF%81%E4%BF%A1%E6%81%AF-%E6%B7%98%E6%B1%B0%E6%9C%BA%E5%88%B6)  
在服务器端可以通过设置环境变量 v2ray.vmess.aead.forced = true 以关闭对于 MD5 认证信息的兼容。 或者 v2ray.vmess.aead.forced = false 以强制开启对于 MD5 认证信息 认证机制的兼容 （不受到 2022 年自动禁用机制的影响） 。 (v4.35.0+)  
VMess 机场用户请及时联系机场管理员或客服，以获取更多咨询。  
其它协议的用户不受此次更新影响，可以放心使用 v0.19.5 以及之后的版本。  
Clash for Windows v0.19.8 及以下的版本中存在 XSS 漏洞（详情见： [https://github.com/Fndroid/clash_for_windows_pkg/issues/2710](https://github.com/Fndroid/clash_for_windows_pkg/issues/2710)），可以通过配置文件在目标计算机上远程执行 JavaScript 代码。该漏洞可以通过 URL 下载 Profile 引发，请勿使用来历不明 URL 下载配置文件（包括 URL Scheme 下载）！  
在 v0.19.9 版本中已修复，请尽快升级！

4. 如何汉化 Clash for Windows？
--------------------------

对于部分新用户来说，纯英文的界面确实有一些不太友好，但是我们可以通过很简单的操作来自行汉化 Clash for Windows。以下操作需要在 Clash 未在运行的前提下进行：

如果是安装版的 Clash，那么可以对着桌面图标单击右键，选择`打开文件所在位置`，即可找到 clash 主程序所在的`Clash for Windows`文件夹。然后双击进入`resources`文件夹，找到`app.asar`这个文件。

下载下方的 Clash for Windows 汉化文件，然后用它替换掉原来的`app.asar`文件。  
[Clash for Windows 汉化文件](https://sabrinathings.lanzoui.com/b01hweblc)  

汉化需要下载对应版本的汉化文件哦，不同版本之间混用可能会造成一些麻烦。

替换完成之后运行软件即可看到效果。

如果下载的是便携版的 Clash for Windows，直接找到`resources`文件夹进行替换操作即可。

也可以直接从博客下载已经汉化好的版本~

**汉化文件网盘地址：**[https://sabrinathings.lanzoui.com/b01hweblc](https://sabrinathings.lanzoui.com/b01hweblc)  
**汉化包来源：**[https://t.me/ClashR_For_Windows](https://t.me/ClashR_For_Windows)  
**汉化需要下载对应版本的汉化文件哦，不同版本之间混用可能会造成一些麻烦。**

![](https://merlinblog.xyz/usr/uploads/2020/08/887204836.png)

![](https://merlinblog.xyz/usr/uploads/2020/08/190211110.png)

![](https://merlinblog.xyz/usr/uploads/2020/08/2146019476.png)

![](https://merlinblog.xyz/usr/uploads/2020/08/839242124.png)

5. 快速配置 Clash for Windows
-------------------------

请先在机场官网复制 `Clash(R)订阅链接`。  

打开 Clash for Windows 应用程序，在左侧的标签页中选择「Profiles」， 在顶部输入您的 `Clash 订阅链接` ，然后点击「Download」按钮。

![](https://merlinblog.xyz/usr/uploads/2020/10/2110331695.png)

Clash for Windows 会自动拉取配置文件进行更新，如果一切顺利，你应当可以看到绿色提示信息「Success!」，并且可以看到一个新增的配置文件：

![](https://merlinblog.xyz/usr/uploads/2020/10/1262785329.png)

点击新增的配置文件来切换到该配置，然后点击「Proxies」标签页来切换接入点，将顶部的出站模式选择为「Rule」。  
此模式下你的网络访问请求将通过 Clash for Winows 进行**分流处理**。

以往的 VPN 是可以代理各种协议，这使得部分可以正常打开的网站打开缓慢。而到了 SS 协议代理的时代，因为是 socks5 代理，已经不能像 VPN 一样代理各种网络请求。这也带来了改变的可能。即可通过一定的规则进行流量的分发，从而加快访问速度。

分流的意义，初期是为了解决国内 / 国外使用同一网络模式访问缓慢的问题，如挂全局访问国内视频网站等。后来，由于不同的客户端提供了多种多样的分流方法，网上也出现了很多人基于相应制作的规则集。现如今很多人所说的分流已经不仅仅是国内直连，国外代理了某种意义上，折腾分流俨然成了技术活和一部分人的技术象征，他们乐此不疲。

![](https://merlinblog.xyz/usr/uploads/2020/10/3643147102.png)

在「Proxy」策略组中选择所想要使用的接入点。Proxy 策略组是用于访问国际网络的默认策略，在不进行其他修改的情况下，所有国际网络的访问都通过 Proxy 策略组中选择的接入点进行。

图中所示的其它策略组为本人出于自身实际需求自行配置的，请以自己的实际配置为准。

6. 启动 Clash for Windows
-----------------------

请务必确保开关是启用状态。  
经博客聊天窗口统计，大量新手用户因未打开开关，导致连不上代理。

返回到「General」部分，将「System Proxy」的开关更改为绿色状态即可开始使用。此外，建议将「Start with Windows」也更改为绿色来让 Clash for Windows 在开机时自动启动。

![](https://merlinblog.xyz/usr/uploads/2020/10/1116731389.png)

### 视频版教程

#### 从订阅链接下载和更新节点

[vplayer url="https://merlinblog.xyz/usr/uploads/2019/12/3951972166.mp4" pic="https://merlinblog.xyz/usr/uploads/2019/12/2709902916.jpg" /]

#### 本地拖拽 YAML 文件

在无法直接从软件里更新自己的订阅时，可以复制你的链接，直接粘贴到浏览器打开，然后复制全部的文本，在桌面新建一个文本文档（TXT 格式），然后粘贴到文档里，保存后将文件的扩展名更改为 YAML 。然后直接拖拽到软件的 Proflies 界面。  
[scode type="green"] 这是一种曲线救国的方法。此方法导入的配置无法进行更新，需要更新时请重新来一遍。[/scode]

[vplayer url="https://merlinblog.xyz/usr/uploads/2019/12/2045285109.mp4" pic="https://merlinblog.xyz/usr/uploads/2019/12/2709902916.jpg" /]

[scode type="lblue"] 简单的使用教程到这里就结束了 ，当前已经可以进行正常的科学上网。  
有兴趣的话可以接着了解下边的内容。 [/scode]

请注意  
当配置文件存在错误时，无法在「Profiles」界面切换，请根据提示进行修改  
若出现如图所示的情况，说明您未点击刚才新增的配置文件，请返回「Profiles」点击选择对应的配置文件。

![](https://merlinblog.xyz/usr/uploads/2019/12/571262170.png)

7. 其它知识
-------

### 7.1 Clash for Windows 界面简介

![](https://merlinblog.xyz/usr/uploads/2020/10/884528405.png)

*   `General（常规）`：
    
    *   `Port`、`Socks Port`；分别为 HTTP、SOCKS 代理端口，点击终端图案可以打开一个配置了代理的命令行窗口，点击端口数字可以复制该命令；
    *   `Allow LAN`：启用局域网共享代理功能；‘
    *   `Log Level`：日志等级；
    *   `Home Directory`：点击下方路径直达 `C:\Users\用户名\.config\clash` 文件夹；
    *   `GeoIP Database`：点击下方日期可更新 GeoIP 数据库；
    *   `UWP Loopback` ：可以用来使 UWP 应用解除回环代理限制；
    *   `Tap Device` ：安装 cfw-tap 网卡，可用于处理不遵循系统代理的软件（实际启动 tap 模式需要更改配置文件）；
    *   `General YML`：编辑 `config.yml` 文件，可用于配置部分 **General** 页面内容；
    *   `Dark Theme`：控制暗色模式；
    *   `System Proxy`：启用系统代理；
    *   `Start with Windows`：设置开机自启；
*   `Proxies（代理）`：选择代理方式（Global - 全局、Rule - 规则、Direct - 直连）及策略组节点选择；
*   `Profiles（配置管理）`：
    
    *   用来下载远端配置文件和创建本地副本，且可在多个配置文件间切换；
    *   对配置进行节点、策略组和规则的管理（添加节点、策略组和规则在各自编辑界面选择 `Add`, 调整策略组顺序、节点顺序及策略组节点使用拖拽的方式）；
*   `Logs（日志）`：显示当前请求命中规则类型和策略；
*   `Connections (连接)`: 显示当前的 TCP 连接，可对某个具体连接执行关闭操作；
*   `Feedback（反馈）`：显示软件、作者相关信息，内含捐赠码，欢迎打赏 [Fndroid](https://github.com/Fndroid) 大佬以感谢和支持开发。

### 7.2 UWP 应用

如需使用 UWP 应用，还需要点击「EnableLoopback」来为 UWP 应用启用本地回环代理。在 Windows 10 中，微软出于安全性考虑，不允许 UWP 应用访问本地回环地址，这导致 UWP 应用无法直接使用代理，需要其他工具来破除这一限制。

Clash for Windows 集成了 EnableLoopback 程序，只需要点击「UWP Loopback」就可以使用。

![](https://merlinblog.xyz/usr/uploads/2019/12/1411832154.png)

打开后一般直接点击 Exempt All 勾选所有 UWP 应用，然后点击 Save Changes 即可。请注意安装新的 UWP 应用后需要重新设置。

### 7.3 更新 GeoIP 数据库

在「General」界面点击「GeoIP Database」来更新 MaxMind 的 GeoIP2 Lite 数据库。此数据库用于 Geo 规则和 DNS 判断，在更新软件时，GeoIP 库会同步更新，一般不需要频繁刷新。

[scode type="green"] 详情请移步👇  
[https://merlinblog.xyz/wiki/geoip.html](https://merlinblog.xyz/wiki/geoip.html) [/scode]

![](https://merlinblog.xyz/usr/uploads/2019/12/3353354913.png)

### 7.4 TAP 设备

#### 7.4.1 介绍

Clash for Windows 中提供了一个新的 TAP 模式。对于不遵循系统代理的软件，TAP 模式可以接管其流量并交由 CFW 处理。

#### 7.4.2 CFW0.9.0 及以后版本

##### 安装 TAP 网卡

点击 `General`页面下的 `TAP Device`以安装 TAP 网卡，此网卡将用于接管系统流量，安装完成可在系统网络连接中看到一张新的虚拟网卡 `cfw-tap`。

##### 启动 TAP 模式

在配置文件中，添加以下字段 (包含 fake-ip)：

```
dns:
   enable: true
   enhanced-mode: fake-ip # 或 redir-host
   listen: 127.0.0.1:53
   nameserver:
      - 223.5.5.5
experimental:
  interface-name: WLAN # WLAN 为物理网卡名

```

##### 工作原理

此版本可以使用 interface-name 属性避免回环，所以可以不使用 fake-ip 模式，并且支持了 UDP 及 IP 类请求

#### 7.4.3 CFW0.8.11 及以前版本

##### 安装 TAP 网卡

点击 `General`页面下的 `TAP Device`以安装 TAP 网卡，此网卡将用于接管系统流量，安装完成可在系统网络连接中看到一张新的虚拟网卡 `cfw-tap`。

##### 启动 TAP 模式

在配置文件中，添加以下字段 (包含 fake-ip)：

```
dns:
   enable: true
   enhanced-mode: fake-ip
   listen: 127.0.0.1:53
   nameserver:
      - 223.5.5.5

```

##### 工作原理

为避免循环，Clash for Windows 只会将 Fake IP 段的请求发往 TAP，所以必须要在配置文件中指定 `enhanced-mode: fake-ip`。  
Clash for Windows 会将系统 DNS 修改为 Clash 本地 DNS 服务器，获取到 Fake IP 的请求再发送至 TAP 网卡，这样设置可以让大部分软件能够正常工作，但如果请求直接使用 IP 地址而非域名，则不会被发往 TAP ，例如 Telegram 。

#### 7.4.4 CFW0.9.6 版本

此版本调整了 TAP 逻辑，DNS 在此版本要求监听在 0.0.0.0:53  
同时，此版本已经将 TAP 安装 DNS 改为 10.0.0.1 了，Office 不能联网的问题可以参考这个修改配置文件解决：  
[https://github.com/Fndroid/clash_for_windows_pkg/issues/466#issuecomment-614716133](https://github.com/Fndroid/clash_for_windows_pkg/issues/466#issuecomment-614716133)

### 7.5 URL Scheme

CFW 支持使用 URL Scheme 快速导入配置文件：

```
clash://install-config?url=<encoded URI>

```

### 7.6 便携模式

**版本要求：**  
从 0.4.0 开始，Clash for Windows 提供简单的便携支持

**使用方法：**  
进入 Release 页面下载最新后缀为 `.zip或.7z`的免安装包并解压至希望安装的目录下（如移动硬盘、U 盘等）

**配置文件：**  
进入 `安装目录/resources/static/files/`目录中进行如下操作：

1.  新建 config.yml（文件可以为空，但一定要创建）
2.  重新启动 Clash for Windows  
    此时文件夹目录中还有其他文件及文件夹，请勿对其修改

**启动及更新：**  
安装目录下 Clash for Windows.exe 即为软件入口，点击启动即可  
如需更新 Clash for Windows，只需下载对应 zip 安装包，解压并覆盖至软件目录即可

8. 常见问题
-------

### 8.1 界面显示不全，无法操作

删除 Home Directory 下 config.yml 文件并重启软件 如错误依旧，打开 logs 文件夹，选取最新日志文件分析

### 8.2 升级后提示 xxx not found

0.6.0 版本升级后，Clash 核心增加对规则部分的校验，如果策略不存在，则不再忽略而提示错误，根据错误信息检查配置文件并进行排除即可

### 8.3 系统代理自动关闭或打开

清除系统代理设置 如无法解决，则检查是否有其他安全 / 代理软件修改代理设置

**如果装有 QQ 输入法或者 QQ 音乐等腾讯系软件，建议卸载或者禁止开机自动启动，此类毒瘤软件会更改系统代理设置。**  
**浏览器里有代理类插件的话（包括 Switch Omgega、各种 VPN、集装箱等），请尝试删除代理插件，然后重启电脑。**

详见：[https://github.com/Fndroid/clash_for_windows_pkg/issues/312](https://github.com/Fndroid/clash_for_windows_pkg/issues/312)

### 8.4 无法访问网页

0.6.0 版本升级后，Clash 核心使用自定义 DNS 设置进行服务器及直连域名的解析，所以当日志中出现大量 All DNS Failed! 日志时，请重新设置合适的 DNS

如果不使用 TAP ，建议将 DNS 关闭

### 8.5 TAP 无法安装

检查是否已经安装其他 TAP 设备，若是，可以先在设备管理器中将其删除后重试

### 8.6 安装 0.7.4 之后版本首次使用时不显示接入点

新版 Clash for Windows 在下载配置文件后并不会默认切换到新的配置文件（与旧版本不同），否则仍然使用的是默认配置文件。

### 8.7 替换配置文件后不显示接入点

这可能是因为 `Clash for Windows` 的基础配置文件 `config.yml` 被修改。要解决此问题，点击 `Clash for Windows General` 标签页上的 `Home Directory` 进入 `Clash for Windows` 配置目录，然后删除 `config.yml` 文件（不是 `config.yaml`），退出并重启打开 `Clash for Windows`，重新进行配置步骤。

### 8.8 系统代理异常关闭

**解决办法：**

① 打开注册表编辑器（可以自行百度如何打开）

② 进入 HKEY_CURRENT_USERSoftwareMicrosoftWindowsCurrentVersionInternet SettingsConnections

③ 选择文件 - 导出，输入文件名备份当前分支

④ 删除所有条目

⑤ 重新开关 System Proxy

⑥ 刷新注册表编辑器，查看条目是否重新出现，若否，选择文件 - 导入恢复设置

**如果装有 QQ 输入法或者 QQ 音乐等腾讯系软件，建议卸载或者禁止开机自动启动，此类毒瘤软件会更改系统代理设置。**  
**浏览器里有代理类插件的话（包括 Switch Omgega、各种 VPN、集装箱等），请尝试删除代理插件，然后重启电脑。**

详见：[https://github.com/Fndroid/clash_for_windows_pkg/issues/312](https://github.com/Fndroid/clash_for_windows_pkg/issues/312)

### 8.9 TAP 无法正常工作

首先，重新启动 CFW，且勿使用管理员权限启动。  
然后重新在 `General`中点击 `Install TAP Device`。

9. GeoIP 相关问题
-------------

若遇到首页和 log 中有类似于下边这样的提示：

```
time="2020-01-08T03:23:08Z" level=fatal 
msg="Can't load mmdb: error opening database: invalid MaxMind DB file"

```

则说明 country.mmdb 文件有问题，请下载正常的 country.mmdb 文件，然后移动到 `.config\clash` 文件夹进行替换。

如果找不到该文件夹，可以在 CFW 首页点击 `Open Folder` 直达。

点击右边按钮下载 [country.mmdb](https://merlinblog.xyz/usr/uploads/2020/01/530802067.zip) 文件（下载后请自行解压）

10. 常见的订阅错误报告
-------------

① 如果遇到以下提示：

```
Invalid Config:yaml:
unmarshal errors:line 1:cannot unmarshal !!str c3M6Ly9...

```

说明用错了订阅链接，请检查自己是不是复制错了或者多了空格之类的。

没有 Clash 订阅链接的可以使用[订阅转换 API](https://bianyuan.xyz/) 来转换订阅链接。

② 如果遇到此类提示：

```
Invalid Config:
Value for 'Proxy' is invalid:Unexpected null or empty

```

说明你还没买套餐，或者订阅为空。请联系你所在机场的管理员。

③ 如果遇到此提示：

```
...cipher not supported

```

说明你使用的加密算法不被 Clash 支持。请更换加密算法。推荐：`ChaCha20-ietf-poly1305`

11. Clash 家族
------------

每一款都好用！吹爆!

*   关于 ClashDotNetFramework ：[https://merlinblog.xyz/wiki/ClashDotNetFramework.html](https://merlinblog.xyz/wiki/ClashDotNetFramework.html)
*   关于 ClashA （安卓）：[https://merlinblog.xyz/wiki/clasha.html](https://merlinblog.xyz/wiki/clasha.html)
*   关于 Clash for Android ：[https://merlinblog.xyz/wiki/cfa.html](https://merlinblog.xyz/wiki/cfa.html)
*   关于 ClashX (Mac) ：[https://merlinblog.xyz/wiki/ClashX.html](https://merlinblog.xyz/wiki/ClashX.html)
*   关于 Clash for Windows for Mac ：[https://merlinblog.xyz/wiki/clash-for-windows-for-mac.html](https://merlinblog.xyz/wiki/clash-for-windows-for-mac.html)
*   关于 ClashWeb · 一款轻量化的 Clash for Windows ：[https://merlinblog.xyz/wiki/clashweb.html](https://merlinblog.xyz/wiki/clashweb.html)

Clash for Android 终于更新了支持 SSR 的新内核!!  
至此三大平台的 Clash 客户端都支持了 SSR，可喜可贺 🎉🎉🎉  
由于各种原因，需要使用以下客户端才能正常使用：

*   Clash for Android ([https://github.com/Kr328/ClashForAndroid/releases)](https://github.com/Kr328/ClashForAndroid/releases)) v2.1.1 或更高
*   Clash for Windows ([https://github.com/Fndroid/clash_for_windows_pkg/releases)](https://github.com/Fndroid/clash_for_windows_pkg/releases)) v0.11.2 或更高
*   ClashX ([https://github.com/yichengchen/clashX/releases)](https://github.com/yichengchen/clashX/releases)) 1.30.1 或更高
*   Clash Core ([https://github.com/Dreamacro/clash/releases)](https://github.com/Dreamacro/clash/releases)) v1.1.0 或更高

12. 尾巴
------

觉得 Clash for Windows 内存占用过高？试试 [ClashWeb](https://merlinblog.xyz/wiki/clashweb.html) 吧~~~  
[https://merlinblog.xyz/wiki/clashweb.html](https://merlinblog.xyz/wiki/clashweb.html)