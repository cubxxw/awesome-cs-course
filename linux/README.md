# linux

## 🖥️Linux网络通信

**这个板块包括计算机网络、TCP/IP、网络抓包的实现、Linux网络编程**

:taiwan:基础篇没有对这些命令很详细的解释，算是一种补充，这些命令的不一样的用法🐧

+ [x] [网络学习基础](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/web/README.md)

----

- [x] [😎第1节 ifconfig命令](./linux-web/1.md)

- [x] [😎第2节 route路由命令](./linux-web/2.md)

- [x] [😎第3节 IP命令](./linux-web/3.md)

- [x] [😎第4节 netstat网络端口查看](./linux-web/4.md)

- [x] [😎第5节 ping命令和telnet命令](./linux-web/5.md)

- [x] [😎第6节 nc命令](./linux-web/6.md)

+ [x] [😎第7节 ssh命令](./linux-web/7.md)

- [x] [😎第8节 wget命令](./linux-web/8.md)
- [x] [👍第9节 bash特性](./linux-web/9.md)
- [x] [🫡第10节 scp和rsync](./linux-web/14.md)
- [x] [🫰第11节 curl命令](./linux-web/15.md)



## ⚔️三剑客

> 同[docker](https://docker.nsddd.top)三驾马车

+ [正则表达式](./linux-web/10.md)
+ [grep文本过滤器](./linux-web/11.md)
+ [sed文本编辑工具](./linux-web/12md)
+ [awk格式化文本](./linux-web/13.md)



## 🐧不一样的篇幅

> 在一些操作里面或许有着鲜为人知但是很好用的技巧，比如说在 Bash 中，可以按下 **ctrl-w** 删除你键入的最后一个单词，**ctrl-u** 可以删除行内光标所在位置之前的内容，**alt-b** 和 **alt-f** 可以以单词为单位移动光标，**ctrl-a** 可以将光标移至行首，**ctrl-e** 可以将光标移至行尾，**ctrl-k** 可以删除光标至行尾的所有内容，**ctrl-l** 可以清屏。键入 `man readline` 可以查看 Bash 中的默认快捷键。内容有很多，例如 **alt-.** 循环地移向前一个参数，而 **alt-*** 可以展开通配符。
>
> 我们都会用`tree`显示状态数，但是很少有人知道`pstree -p` 可以显示精美的状态树
>
> `curl` 和 `curl -I` 可以被轻松地应用于 web 调试中，它们的好兄弟 `wget` 也是如此，但是其实我们也可以试试更潮的 [`httpie`](https://github.com/jkbrzt/httpie)。
>
> ```bash
> root@ubuntu:/c# http
> http          httpie.http   https         
> httpie        httpie.https  
> ```
>

- 🎊 [第1节 命令行的艺术](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/17.md) 
- 🎊 [第2节 Linux和windows命令行一些很有用的技巧](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/2.md)
-  🎊 [第3节 实用工具箱 – 导航](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/3.md)
-  🎊 [第4节 Makefile](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/4.md)
-  🎊 [第5节 Makefile书写规则](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/5.md)
-  🎊 [第6节 计算机专业书籍分享](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/6.md)
-  🎊 [第7节 万能的shell](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/7.md)
-  🎊 [第8节 数据整理](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/8.md)
-  🎊 [第9节 命令行环境](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/9.md)
-  🎊 [第10节 调试以及性能分析](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/10.md)

> 一些好用的Linux命令也是非常有用的，甚至后面衍生了很多不一样的工具，或许你认为用到Linux再去查，大可不必，沉迷于Linux的命令行的世界，或许真的不一样。
>
> 1. **关于Linux和Windows中一些很有用的技巧，我们可以看[这篇文章](https://github.com/3293172751/cs-awesome-Block_Chain/blob/master/cs/markdown/2.md)**
> 2. **我们可以在这里面学习到不一样的[命令行艺术](https://nsddd.top/archives/shell-cmd)**
> 3. **当您使用 shell 进行工作时，可以使用[一些方法改善您的工作流](https://nsddd.top/archives/9)**
>
> **接下来就是系统的Linux学习**



## 📚Linux系统篇（OneNote迁移，待美化……）

> 我们在写脚本的时候，有很多的技巧，可以节省我们很多的精力：
>
> + **批量创建文件：`touch file{0..9}.txt`**
>
> + git脚本中，我们删除所有文件但是想保留`.git`文件夹
>
>   ```bash
>   find . 										# 插件当前目录下的所有文件 
>   find . -not -name "1.tt"  					# 忽略1.tt，查找所有文件
>   find . -not -name "1.tt" -exec rm -rf {} \  # 忽略1.tt，执行
>   ```

+ [x] [😎🧋linux基本命令](markdown/7.md)
+ [x] [😎🧋sudo命令](markdown/8.md)
+ [x] [😎🧋系统指令和找回密码](markdown/9.md)
+ [x] [😎🧋帮助与查找](markdown/10.md)
+ [x] [😎🧋时间指令和压缩解压指令](markdown/11.md)
+ [x] [😎🧋at 命令](markdown/12.md)
+ [x] [😎🧋用户和组](markdown/13.md)
+ [x] [😎🧋linux权限](markdown/14.md)
+ [x] [😎🧋任务调度](markdown/15.md)
+ [x] [😎🧋Linux磁盘分区](markdown/16.md)
+ [x] [😎🧋磁盘查询](markdown/17.md)
+ [x] [😎🧋Linux补充常用技巧指令](markdown/18.md)
+ [x] [😎🧋网络配置 – 设置静态IP地址](markdown/19.md)
+ [x] [😎🧋DNS域名解析](markdown/20.md)
+ [x] [😎🧋修改host文件](markdown/21.md)
+ [x] [😎🧋linux rpm包](markdown/22.md)
***
+ [x] [😎🧋进程管理](markdown/23.md)
+ [x] [😎🧋服务](markdown/24.md)
+ [x] [😎🧋动态服务监测](markdown/25.md)
+ [x] [😎🧋日志管理](markdown/26.md)
+ [x] [😎🧋日志管理服务](markdown/27.md)
+ [x] [😎🧋日志交替](markdown/28.md)
+ [x] [😎🧋ubuntu系统一些解决方案](markdown/29.md)
+ [x] [😎🧋远程链接](markdown/30.md)
+ [x] [😎🧋Linux常用命令手册 ](markdown/31.md)
+ [x] [😎🧋AWK]([markdown/32.md)
+ [x] [😎🧋SED](markdown/33.md)
+ [x] [😎🧋shell编程](markdown/36.md)
+ [x] [😎🧋shell预定义变量](markdown/37.md)
+ [x] [😎🧋READ](markdown/38.md)
+ [x] [😎🧋shell备份数据库](markdown/39.md)
+ [x] [😎🧋多用户状态有网络服务](markdown/40.md)
+ [x] [😎🧋linux的备份和恢复](markdown/41.md)
+ [x] [😎🧋浅浅了解Linux内核](markdown/42.md)
+ [x] [😎🧋set ff = linux](markdown/43.md)
+ [x] [😎🧋Vim的正则表达式](markdown/44.md)
+ [x] [😎🧋make](markdown/34.md)
+ [x] [😎🧋linux C](markdown/35.md)
+ [x] [😎🧋定制Linux系统](./定制Linux系统.pdf)



## 🔦vim篇

> ⭐vim是我在Linux花费了大量的时间和尽力去学习的工具，从大一下半年到现在，Linux中一直使用的是vim。作为一个神器，vim给了我们太多的惊喜。插件、窗口、宏、各种操作。甚至离开了Linux，你也随处可见：在浏览器中、ide中……
>
> 熟悉至少一个基于文本的编辑器。通常而言 Vim （`vi`） 会是你最好的选择，毕竟在终端中编辑文本时 Vim 是最好用的工具（甚至大部分情况下 Vim 要比 Emacs、大型 IDE 或是炫酷的编辑器更好用）。
>
> 甚至你在命令行中，可以执行 `set -o vi` 来使用 vi 风格的快捷键，而执行 `set -o emacs` 可以把它改回来。
>
> 😍 在vim中批量处理多行（注释），先`ctrl + v`选择多需要的行坐标，再按下大写`I`，最后输入你要批量输入的文字，输入完成后按下`Esc`。

+ [x] [😎🧋我的vimrc配置](markdown/my_vim.md)

+ [x] [😎🧋vim简介](markdown/1.md)

+ [x] [😎🧋vim基本语法](markdown/2.md)

+ [x] [😎🧋vim-plug](markdown/3.md)

+ [x] [😎🧋vim窗口和宏](markdown/4.md)

+ [x] [😎🧋linux防火墙](markdown/5.md)

+ [x] [😎🧋EASYMOTION快速跳转](markdown/6.md)

> 我们花费了很多时间去配置git，但是也有很多问题。有时候，我们会在几台不同的电脑上使用Vim. 例如，我们可能在自己的电脑和公司的电脑上都安装了Vim. 有时候，我们需要实现，如果我们配置好了其中一个Vim环境，就能轻松的把这些配置很容易的复制到另外一台机器，甚至于我们更新其中一台机器甚至几台机器的配置之后，可以很容易的将其同步到其他机器。好在现在有较多的云存储的选择。利用它们，加上一个版本控制软件，我们就可以很容易的做到这一点。

+ [x] [🅱️⭕Git学习指南](https://github.com/3293172751/awesome-cs-course/tree/master/Git)
+ [x] [🅱️⭕Vim同步](../markdown/vim-git.md)

