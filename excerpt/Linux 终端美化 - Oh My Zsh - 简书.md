> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.jianshu.com](https://www.jianshu.com/p/b8a80dd59414)

[TOC]

前言
--

在类 UNIX 系统（比如 Linux，Mac）环境中，我们常常在终端中输入命令与系统进行交互，大多数系统默认使用的 shell 程序为 Bash。

但是 Bash 本身的色调非常单调，因此，如果我们需要长时间工作在命令行环境种，适当对终端进行美化是一个非常不错的选择。

几个概念释义
------

在我们美化 Linux 终端前，我们先了解一下以下几个概念：

*   **终端（terminal）**：拥有键盘 + 显示器的设备。
    
*   **终端模拟器（terminal emulator）**：采用图形界面模拟 **终端** 操作的程序。
    
*   **shell**：操作系统和终端之间的一个 “壳” 应用，可以将终端发送的字符（命令）发送给系统，并将系统运行命令的结果传递给终端显示。
    
*   **Bash**：大多数类 UNIX 系统的默认 shell 程序。
    

**注**：在类 UNIX 系统中，通常将 **终端模拟器** 简称为 **终端**，因此下文涉及到 **终端** 的地方，如无特别强调，均指 **终端模拟器**。

[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F)
--------------------------------------------------------------------------

在前面对 **终端**，**终端模拟器**，**shell** 进行介绍后，我们应当能知道，大多数类 UNIX 系统自带的终端（即命令行工具），其界面相对比较单调。

因此，如果我们想美化终端，可以更换另一个更加美观的终端（比如 Mac 系统的 [iterm2](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.iterm2.com%2F)），或者更换另一个可以配置终端的 shell 程序。

我们这里主要对 Ubuntu 系统终端进行美化，所以选择更换 shell。

目前类 UNIX 系统上最常用的第三方 shell 就是 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F)：[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 又称为 Z shell，是一个交互式 shell 程序，也可以作为脚本解释器。[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 基于 [Bourne Shell（sh)](https://links.jianshu.com/go?to=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FBourne_shell)，提供了很多新特性，比如插件支持和主题更换。

[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 的安装方法如下：这里主要介绍在 Ubuntu 系统中进行安装：

1.  下载并安装 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F)：

```
sudo apt install zsh


```

2.  查看是否安装成功：

```
zsh --version # ==> Expected result: zsh 5.4.2 or more recent


```

3.  设置为终端默认 shell：

```
chsh -s $(which zsh)


```

4.  如果第 3 步设置失败，则进行如下设置：

```
# ~/.bash_profile 添加如下内容
export SHELL=`which zsh`
[ -z "$ZSH_VERSION" ] && exec "$SHELL" -l


```

5.  登出，然后再登入，输入以下命令查看下是否已登录 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F)：

```
echo $SHELL # ==> 如果显示: /bin/zsh，则为成功


```

6.  输入以下命令，查看当前是否使用 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F)：

```
$SHELL --version # ==> Expected result: 'zsh 5.4.2' or similar


```

更多安装方法，请查看：[Installing ZSH](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FInstalling-ZSH)

[Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh)
-----------------------------------------------------------------------------------------

[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 本身的配置十分复杂，因此一般不直接对其进行配置，而是使用第三方框架进行设置。

当前最受欢迎的 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 配置管理框架为：[Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh)

以下是 [Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh) 的安装方法：

```
# 使用 curl
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# 或者使用 wget
sh -c "$(wget -O- https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# 或者手动进行安装
curl -Lo install.sh https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh
sh install.sh


```

[Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh) 常用配置
----------------------------------------------------------------------------------------------

当安装完成 [Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh) 后，会发现创建了`~/.zshrc`文件，这就是 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 的配置文件。

[Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh) 为 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 提供了很多的[插件（Plugin）](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FPlugins)和[主题（Theme）](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FThemes)配置。

*   **[插件（Plugin）](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FPlugins)**：[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 插件，简单来说，就是 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 可以解释执行的一段 shell 脚本。因此，一个插件可以简单地执行一些命令，也可以提供对特定命令和函数的补全功能。

要使用 [插件](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FPlugins)，首先需要使能插件功能，方法如下：  
在`~/.zshrc`文件中，添加如下内容：

```
plugins=(
    git
    adb
    ruby
)


```

如上就是添加了 [git](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Ftree%2Fmaster%2Fplugins%2Fgit)，[adb](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Ftree%2Fmaster%2Fplugins%2Fadb)，[ruby](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Ftree%2Fmaster%2Fplugins%2Fruby) 插件，更多其他插件，请参考：[zsh Plugins](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FPlugins)

*   **[主题（Theme）](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FThemes)**：[zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 主题是一个可以更改控制台的 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 脚本。一个主题可以做很多事情，比如，控制台自动更新当前 git 仓库的状态，或者显示每秒更新的一个时钟...

要使用 [主题](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FThemes)，首先需要使能该功能，方法如下：  
在`~/.zshrc`文件中，设置`ZSH_THEME`为要使用的具体主题名，比如：

```
ZSH_THEME=robbyrussell


```

如上就是让 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 启用`robbyrussell`主题。更多其他主题，请参考：[zsh Themes](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FThemes)。

**注**：如果不想使用主题，直接设置：`ZSH_THEME=""`

这里推荐一个主题：**[powerlevel10k](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k)**，安装方法如下：

```
# 首先下载到 on-my-zsh 的本地目录
git clone --depth=1 https://github.com/romkatv/powerlevel10k.git $ZSH_CUSTOM/themes/powerlevel10k

# ~/.zshrc 设置如下内容
ZSH_THEME="powerlevel10k/powerlevel


```

进阶配置
----

[Oh-My-Zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh) 对[插件](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FPlugins)和[主题](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fohmyzsh%2Fohmyzsh%2Fwiki%2FThemes)的配置虽然很简单，但是对于非内置组件，我们还需要进行手动下载，再进行配置。而这些步骤，完全可以进行自动化。

因此，网络上其实已存在很多的 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 的插件管理器，比如：[antigen](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzsh-users%2Fantigen)，[zgen](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Ftarjoilija%2Fzgen)，[zplug](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzplug%2Fzplug)...

但是这里我推荐的是：[zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit)。

多方测评表明，[zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 是当前加载速度最快的 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 插件管理器，并且随着插件的数量增多，[zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 的优势会更大。

[zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 的插件加载速度快的一个主要原因就是：其提供了一个 **插件延迟加载（Turbo）** 的功能，使得插件可以在 [zsh](https://links.jianshu.com/go?to=http%3A%2F%2Fzsh.sourceforge.net%2F) 启功后，再进行加载。

下面是 [zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 的安装方法：

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/zdharma/zinit/master/doc/install.sh)"


```

安装完成 [zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 后，我们就可以在`~/.zshrc`中配置插件 / 主题，比如，对于上文建议使用的 [powerlevel10k](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k) 主题，其安装方法如下：

1.  在`~/.zshrc`文件中配置主题为 [powerlevel10k](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k)：

```
# ~/.zshrc
# 原生的主题配置可去除
# ZSH_THEME="powerlevel10k/powerlevel
zinit ice depth=1; zinit light romkatv/powerlevel10k


```

2.  重新加载`~/.zshrc`：

```
source ~/.zshrc


```

此时就可以看到 [zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 会去下载 [powerlevel10k](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k)，并自动进行设置。

**注**：可以通过执行`p10k configure`命令来配置 [powerlevel10k](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k)，在执行该命令之前，建议先安装[以下字体](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k%23meslo-nerd-font-patched-for-powerlevel10k)：

*   [MesloLGS NF Regular.ttf](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k-media%2Fraw%2Fmaster%2FMesloLGS%2520NF%2520Regular.ttf)
*   [MesloLGS NF Bold.ttf](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k-media%2Fraw%2Fmaster%2FMesloLGS%2520NF%2520Bold.ttf)
*   [MesloLGS NF Italic.ttf](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k-media%2Fraw%2Fmaster%2FMesloLGS%2520NF%2520Italic.ttf)
*   [MesloLGS NF Bold Italic.ttf](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fromkatv%2Fpowerlevel10k-media%2Fraw%2Fmaster%2FMesloLGS%2520NF%2520Bold%2520Italic.ttf)

直接双击下载的文件，选择安装，然后在设置终端字体为`MesloLGS NF`即可。

更多 [zinit](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2Fzdharma%2Fzinit) 的用法，请查看：[zsh 插件管理器 - Zinit](https://www.jianshu.com/p/2e098dfecf4a)

最后放上效果图以及附上本人配置：[.zshrc](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FWhy8n%2Fdotfiles%2Fblob%2Fmaster%2F.zshrc)、[.p10k.zsh](https://links.jianshu.com/go?to=https%3A%2F%2Fgithub.com%2FWhy8n%2Fdotfiles%2Fblob%2Fmaster%2F.p10k.zsh)

![](http://upload-images.jianshu.io/upload_images/2222997-7b8143cd66d37f70.gif) oh-my-zsh

终端发展史
-----

以下是在查阅资料的时候，从网上看到的一个问答：「[What's the difference between terminal, shell, command line, and bash?](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.reddit.com%2Fr%2Flearnprogramming%2Fcomments%2F6xr0l9%2Fwhats_the_difference_between_terminal_shell%2F)」，里面有人对终端的发展演化进行了介绍，我觉得介绍很简洁，就简单记录一下：

在 19 世纪末期，有一种叫做 **_teletype_** 或者 **_TTY_** 的技术。一个由几个电子元器件组成的老式机械[打印机](https://links.jianshu.com/go?to=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FTypewriter)，基本上可以被划分为两部分：键盘 和 打印机。两者之间由电线进行连接，你可以通过在键盘上打字，此时远端的打印机就会一个一个的回显键盘输入的字符。

在 20 世纪时，计算机开始兴起，电传打字技术（teletype technology ）就很快地被应用到计算机上。用户可以使用键盘进行输入，从而对计算机进行控制，打印机可以同时连接到相同的计算机上，实时回显用户的操作输出。

电传打字技术提供了一个 _命令行交互接口（command line interface）_ 风格的用户接口。命令行交互接口其实就只是用于与计算机进行交互的一种方式：即用户输入一个命令给到计算机，然后计算机回显操作结果并且等待下一个命令。

CRT 视频显示技术（CRT video display technology ）在计算机出现之前就已经存在了，因此人们很快就察觉到可以将视频显示与计算机结合起来。一个显著的例子就是 1950 年时的 SAGE air defense system，详情可查看[该视频](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DIvJpZYQy_7Q)（从 3:40 开始）。

由于计算机与视频显示技术联合越来越常见，现存的 _命令行接口_ 思想就很快地被应用于新的系统 -- 保留了键盘，但是将打印机替换为屏幕（显示器）。这种 键盘 + 文字显示 的设备就被称为 **终端（terminal）**，由于它在计算机连接的另一端，你可以认为它 “终止” 了连接。

所以 **终端（terminal）** 就是带有键盘和屏幕的设备，要么通过电话线经由调制解调器，或者通过实体线缆连接到一台大型计算机（通常是大型机或者早期的 UNIX-based 系统）上。**终端** 的功能只能在屏幕上显示远程计算机发送来的文字信息，或者发送按键字符给到远程计算机。创建文字显示 UI 是由计算机负责的。之所以称为 **终端** 是因为它位于计算机连线的末端，或者说 “终止端”。

因此为了让系统能够发挥效用，计算机中必须运行一种可以告诉 **终端** 显示数据和处理用户按键输入的应用程序，在 UNIX（以及后来的 Linux）世界中，这种应用程序就被称作 **shell**。

**shell** 就是提供命令行接口的应用程序。任何提供用户接口的应用在宽松定义上都可认为是 **shell**，比如 Windows，Mac 和 手机提供的图形用户界面就可以认为是一种图形化 shell。

不同的 **shell** 可以有不同方式进行命令输入，重定向输入 / 输出流，管理复杂任务，比如同时运行多个程序等等。在早期的 UNIX 中，创建了许多不同的 **shell** 程序，很多到现在仍然存在。**Bash** 就是最受欢迎的 shell 程序，并且是许多 Linux 和 UNIX 系统的默认 shell。

今天，许多需要使用 **终端** 来执行操作的用户都使用了拥有图形界面的 GUI 应用程序，这种有 GUI 界面的应用我们称之为 **终端模拟器（terminal emulator）**。它模拟了具备字符输入功能的终端设备。这看起来可能不重要，当在它们过时之前，终端就已具备额外的功能，比如鼠标和颜色文字。因此，终端模拟器是更加复杂的应用。

在类 UNIX 系统（包括一些 Linux 发行版本 和 Mac）中，**终端模拟器** 常常被简称为 **终端（terminal）**，并且它的默认行为就是去启动默认的 shell 程序（通常是 Bash），并将 **终端** 的输入 / 输出连接到 shell。*

综上，**终端（模拟器）** 就是指有图形界面的命令行工具（Command line）。  
在类 UNIX 中，shell 默认为 Bash。  
在 Windows 系统中，它有两种 shell：`cmd.exe`和 `powershell.exe`

参考
--

*   [What's the difference between terminal, shell, command line, and bash?](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.reddit.com%2Fr%2Flearnprogramming%2Fcomments%2F6xr0l9%2Fwhats_the_difference_between_terminal_shell%2F)
    
*   [zsh 启动速度慢的终极解决方案](https://links.jianshu.com/go?to=https%3A%2F%2Fzhuanlan.zhihu.com%2Fp%2F98450570)