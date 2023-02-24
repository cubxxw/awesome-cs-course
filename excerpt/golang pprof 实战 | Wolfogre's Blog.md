> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.wolfogre.com](https://blog.wolfogre.com/posts/go-ppof-practice/)

> 如果要说在 golang 开发过程进行性能调优，pprof 一定是一个大杀器般的工具。

前言
--

如果要说在 golang 开发过程进行性能调优，pprof 一定是一个大杀器般的工具。但在网上找到的教程都偏向简略，难寻真的能应用于实战的教程。这也无可厚非，毕竟 pprof 是当程序占用资源异常时才需要启用的工具，而我相信大家的编码水平和排场问题的能力是足够高的，一般不会写出性能极度堪忧的程序，且即使发现有一些资源异常占用，也会通过排查代码快速定位，这也导致 pprof 需要上战场的机会少之又少。即使大家有心想学习使用 pprof，却也常常相忘于江湖。

**既然如此，那我就送大家一个性能极度堪忧的 “炸弹” 程序吧！**

这程序没啥正经用途缺极度占用资源，基本覆盖了常见的性能问题。本文就是希望读者能一步一步按照提示，使用 pprof 定位这个程序的的性能瓶颈所在，借此学习 pprof 工具的使用方法。

因此，本文是一场 “实验课” 而非“理论课”，请读者腾出时间，脚踏实地，一步一步随实验步骤进行操作，这会是一个很有趣的冒险，不会很无聊，希望你能喜欢。

实验准备
----

这里假设你有基本的 golang 开发功底，拥有 golang 开发环境并配置了 $GOPATH，能熟练阅读简单的代码或进行简单的修改，且知道如何编译运行 golang 程序。此外，需要你大致知道 pprof 是干什么的，有一个基本印象即可，你可以花几分钟时间读一下[《Golang 大杀器之性能剖析 PProf》](https://blog.wolfogre.com/redirect/v3/A0uO_9v0ECGYMC6hksfOB8YSAwM8Cv46xcU7LxImWv3FQhhTHP5qU8VaFgY7xVoWBlrFrU0bxXVQYcU8Bk0KxTsGxRQGFgrF_wQyMDE4zP8CMDnM_wIxNcw7BswUBhbMPDxzLG4tGDESAwM8Cv46xcVaFgY7bkEGFtw7If3FPAZNCsU7Bsw8PAXMPIIcSojF)的开头部分，这不会耽误太久。

此外由于你需要运行一个 “炸弹” 程序，请务必确保你用于做实验的机器有充足的资源，你的机器至少需要：

*   2 核 CPU；
*   2G 内存。

注意，以上只是最低需求，你的机器配置能高于上述要求自然最好。实际运行 “炸弹” 时，你可以关闭电脑上其他不必要的程序，甚至 IDE 都不用开，我们的实验操作基本上是在命令行里进行的。

此外，务必确保你是在个人机器上运行 “炸弹” 的，能接受机器死机重启的后果（虽然这发生的概率很低）。请你务必不要在危险的边缘试探，比如在线上服务器运行这个程序。

可能说得你都有点害怕了，为打消你顾虑，我可以剧透一下 “炸弹” 的情况，让你安心：

*   程序会占用约 2G 内存；
*   程序占用 CPU 最高约 100%（总量按 “核数 * 100%” 来算）；
*   程序不涉及网络或文件读写；
*   程序除了吃资源之外没有其他危险操作。

且程序所占用的各类资源，均不会随着运行时间的增长而增长，换句话说，只要你把 “炸弹” 启动并正常运行了一分钟，就基本确认安全了，之后即使运行几天也不会有更多的资源占用，除了有点费电之外。

获取 “炸弹”
-------

炸弹程序的代码我已经放到了 [GitHub](https://blog.wolfogre.com/redirect/v3/A_4-v86v-9Btg9a9FuRKCcgSAwM8Cv46xcU7LxImWv3FQQYW3DshxTsGzDw8cyzMPIIcSogxEgMDPAr-OsXFWhYGO25BBhbcOyH9xTwGTQrFOwbMPDwFzDyCHEqIxQ) 上，你只需要在终端里运行 `go get` 便可获取，注意加上 `-d` 参数，避免下载后自动安装：

```
go get -d github.com/wolfogre/go-pprof-practice
cd $GOPATH/src/github.com/wolfogre/go-pprof-practice


```

我们可以简单看一下 `main.go` 文件，里面有几个帮助排除性能调问题的关键的的点，我加上了些注释方便你理解，如下：

```
package main

import (
	
	_ "net/http/pprof" 
	
)

func main() {
	

	runtime.GOMAXPROCS(1) 
	runtime.SetMutexProfileFraction(1) 
	runtime.SetBlockProfileRate(1) 

	go func() {
		
		if err := http.ListenAndServe(":6060", nil); err != nil {
			log.Fatal(err)
		}
		os.Exit(0)
	}()

	
}


```

除此之外的其他代码你一律不用看，那些都是我为了模拟一个 “逻辑复杂” 的程序而编造的，其中大多数的问题很容易通过肉眼发现，但我们需要做的是通过 pprof 来定位代码的问题，所以为了保证实验的趣味性请不要提前阅读代码，可以实验完成后再看。

接着我们需要编译一下这个程序并运行，你不用担心依赖问题，这个程序没有任何外部依赖。

```
go build
./go-pprof-practice


```

运行后注意查看一下资源是否吃紧，机器是否还能扛得住，坚持一分钟，如果确认没问题，咱们再进行下一步。

控制台里应该会不停的打印日志，都是一些 “猫狗虎鼠在不停地吃喝拉撒” 的屁话，没有意义，不用细看。

![](https://image.wolfogre.com/a8201c14149e1c25a1b097ff5fd87d24e758f58b1313a7706ba8610999a4f028.jpg)

使用 pprof
--------

保持程序运行，打开浏览器访问 `http://localhost:6060/debug/pprof/`，可以看到如下页面：

![](https://image.wolfogre.com/699b70b9ef6fb586e66e8d696153fe1f7392c0639c849026af556911ee29d6a4.jpg)

页面上展示了可用的程序运行采样数据，分别有：

<table><thead><tr><th>类型</th><th>描述</th><th>备注</th></tr></thead><tbody><tr><td>allocs</td><td>内存分配情况的采样信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>blocks</td><td>阻塞操作情况的采样信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>cmdline</td><td>显示程序启动命令及参数</td><td>可以用浏览器打开，这里会显示 <code>./go-pprof-practice</code></td></tr><tr><td>goroutine</td><td>当前所有协程的堆栈信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>heap</td><td>堆上内存使用情况的采样信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>mutex</td><td>锁争用情况的采样信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>profile</td><td>CPU 占用情况的采样信息</td><td>浏览器打开会下载文件</td></tr><tr><td>threadcreate</td><td>系统线程创建情况的采样信息</td><td>可以用浏览器打开，但可读性不高</td></tr><tr><td>trace</td><td>程序运行跟踪信息</td><td>浏览器打开会下载文件，本文不涉及，可另行参阅<a href="https://blog.wolfogre.com/redirect/v3/AwBGKjtUXC4lQ2UdNqHTCoMSAwM8Cv46xcUtPG6RCPoPbv8CcXH9xQrF_wJJOfr_AlNN-lP_AjMyHP8IQUxTTlFBTjhBFgn-UTESAwM8Cv46xcVaFgY7bkEGFtw7If3FPAZNCsU7Bsw8PAXMPIIcSojF" target="_blank">《深入浅出 Go trace》</a></td></tr></tbody></table>

因为 cmdline 没有什么实验价值，trace 与本文主题关系不大，threadcreate 涉及的情况偏复杂，所以这三个类型的采样信息这里暂且不提。除此之外，其他所有类型的采样信息本文都会涉及到，且炸弹程序已经为每一种类型的采样信息埋藏了一个对应的性能问题，等待你的发现。

由于直接阅读采样信息缺乏直观性，我们需要借助 `go tool pprof` 命令来排查问题，这个命令是 go 原生自带的，所以不用额外安装。

我们先不用完整地学习如何使用这个命令，毕竟那太枯燥了，我们一边实战一边学习。

以下正式开始。

排查 CPU 占用过高
-----------

我们首先通过活动监视器（或任务管理器、top 命令，取决于你的操作系统和你的喜好），查看一下炸弹程序的 CPU 占用：

![](https://image.wolfogre.com/2cf60326868a0e1fb7bfed3a74569a30c7d83fdeb4b3713f565fe9f7ed1e3238.jpg)

可以看到 CPU 占用相当高，这显然是有问题的，我们使用 `go tool pprof` 来排场一下：

```
go tool pprof http://localhost:6060/debug/pprof/profile


```

等待一会儿后，进入一个交互式终端：

![](https://image.wolfogre.com/3e0ed37e1c1663678ea9d607c05e1f4d6a172bfb4d284116de855f3f77346d37.jpg)

 输入 top 命令，查看 CPU 占用较高的调用：

![](https://image.wolfogre.com/fd4ba502e04023fe4a9d012b89be0e1bb7b303dd2991247892e59bf910bfe7eb.jpg)

很明显，CPU 占用过高是 `github.com/wolfogre/go-pprof-practice/animal/felidae/tiger.(*Tiger).Eat` 造成的。

> 注：为了保证实验节奏，关于图中 `flat`、`flat%`、`sum%`、`cum`、`cum%` 等参数的含义这里就不展开讲了，你可以先简单理解为数字越大占用情况越严重，之后可以在[《Golang 大杀器之性能剖析 PProf》](https://blog.wolfogre.com/redirect/v3/A3jsjsv0r3pCsn4x_qmqFKwSAwM8Cv46xcU7LxImWv3F_wdFRERZQ0pZxVoWBjvFWhYGWsWtTRvFOwaJVMX_BDIwMTjM_wIwOcz_AjE1zP5HBolU_1AlMjAlRTUlQTQlQTclRTYlOUQlODAlRTUlOTklQTglRTQlQjklOEIlRTYlODAlQTclRTglODMlQkQlRTUlODklOTYlRTYlOUUlOTAlMjBQUHMsbi0YMRIDAzwK_jrFxVoWBjtuQQYW3Dsh_cU8Bk0KxTsGzDw8Bcw8ghxKiMU)等其他资料中深入学习。

输入 `list Eat`，查看问题具体在代码的哪一个位置：

![](https://image.wolfogre.com/f97d93983b67f3866950abe70364326f0b4a4ed861d8e7713f40fa7931645217.jpg)

可以看到，是第 24 行那个一百亿次空循环占用了大量 CPU 时间，至此，问题定位成功！

接下来有一个扩展操作：图形化显示调用栈信息，这很酷，但是需要你事先在机器上安装 `graphviz`，大多数系统上可以轻松安装它：

```
brew install graphviz 
apt install graphviz 
yum install graphviz 


```

或者你也可以访问 [graphviz 官网](https://blog.wolfogre.com/redirect/v3/A421Yoc_xEV4GG_UO8tV1nMSAwM8Cv46xcU7gjwSbQjbbjsviVpukMUYBkEJFgboxTESAwM8Cv46xcVaFgY7bkEGFtw7If3FPAZNCsU7Bsw8PAXMPIIcSojF)寻找适合自己操作系统的安装方法。

安装完成后，我们继续在上文的交互式终端里输入 `web`，注意，虽然这个命令的名字叫 “web”，但它的实际行为是产生一个 .svg 文件，并调用你的系统里设置的默认打开 .svg 的程序打开它。如果你的系统里打开 .svg 的默认程序并不是浏览器（比如可能是你的代码编辑器），这时候你需要设置一下默认使用浏览器打开 .svg 文件，相信这难不倒你。

你应该可以看到：

![](https://image.wolfogre.com/ba40ec37b1824ec2b9e5b2a193d10e57bca92b6f6d5cd3bac1f220c257ca35f0.png)

图中，`tiger.(*Tiger).Eat` 函数的框特别大，箭头特别粗，pprof 生怕你不知道这个函数的 CPU 占用很高，这张图还包含了很多有趣且有价值的信息，你可以多看一会儿再继续。

至此，这一小节使用 pprof 定位 CPU 占用的实验就结束了，你需要输入 `exit` 退出 pprof 的交互式终端。

为了方便进行后面的实验，我们修复一下这个问题，不用太麻烦，注释掉相关代码即可：

```
func (t *Tiger) Eat() {
	log.Println(t.Name(), "eat")
	
	
	
	
}


```

之后修复问题的的方法都是注释掉相关的代码，不再赘述。你可能觉得这很粗暴，但要知道，这个实验的重点是如何使用 pprof 定位问题，我们不需要花太多时间在改代码上。

排查内存占用过高
--------

重新编译炸弹程序，再次运行，可以看到 CPU 占用率已经下来了，但是内存的占用率仍然很高：

![](https://image.wolfogre.com/3327309fce0b784f2e25c3248abfb81eeed34b78a8969c7ce4df13f136116cfd.jpg)

我们再次运行使用 pprof 命令，注意这次使用的 URL 的结尾是 heap：

```
go tool pprof http://localhost:6060/debug/pprof/heap


```

再一次使用 `top`、`list` 来定问问题代码：

![](https://image.wolfogre.com/bfceafa49e93dda200292e28a8fdf9b8eba59f88ef02014ff6598f71e8dd272d.jpg)

可以看到这次出问题的地方在 `github.com/wolfogre/go-pprof-practice/animal/muridae/mouse.(*Mouse).Steal`，函数内容如下：

```
func (m *Mouse) Steal() {
	log.Println(m.Name(), "steal")
	max := constant.Gi
	for len(m.buffer) * constant.Mi < max {
		m.buffer = append(m.buffer, [constant.Mi]byte{})
	}
}


```

可以看到，这里有个循环会一直向 m.buffer 里追加长度为 1 MiB 的数组，直到总容量到达 1 GiB 为止，且一直不释放这些内存，这就难怪会有这么高的内存占用了。

使用 `web` 来查看图形化展示，可以再次确认问题确实出在这里：

![](https://image.wolfogre.com/42e4a92a2534acfc79d91e1564d5be2851605b0292ad21d4d162b122567d7b93.png)

现在我们同样是注释掉相关代码来解决这个问题。

再次编译运行，查看内存占用：

![](https://image.wolfogre.com/9280aebe822aa106f0be9283a7e3a4e096eabe8cccdd7d9f3d252b46161c698b.jpg)

可以看到内存占用已经将到了 35 MB，似乎内存的使用已经恢复正常，一片祥和。

但是，内存相关的性能问题真的已经全部解决了吗？

排查频繁内存回收
--------

你应该知道，频繁的 GC 对 golang 程序性能的影响也是非常严重的。虽然现在这个炸弹程序内存使用量并不高，但这会不会是频繁 GC 之后的假象呢？

为了获取程序运行过程中 GC 日志，我们需要先退出炸弹程序，再在重新启动前赋予一个环境变量，同时为了避免其他日志的干扰，使用 grep 筛选出 GC 日志查看：

```
GODEBUG=gctrace=1 ./go-pprof-practice | grep gc


```

日志输出如下：

![](https://image.wolfogre.com/4a500684e36566cc74cef1e69b4898c99dfb008f3293f3892ad64990e2c62283.jpg)

可以看到，GC 差不多每 3 秒就发生一次，且每次 GC 都会从 16MB 清理到几乎 0MB，说明程序在不断的申请内存再释放，这是高性能 golang 程序所不允许的。

如果你希望进一步了解 golang 的 GC 日志可以查看[《如何监控 golang 程序的垃圾回收》](https://blog.wolfogre.com/redirect/v3/A9DNc05mRFLA-ZPsjfPhLuZDu-oKbuLF_wQyMDE2xf8CMDfF_wIwMcUtHy8qzDsGiVTMOxzFMRIDAzwK_jrFxVoWBjtuQQYW3Dsh_cU8Bk0KxTsGzDw8Bcw8ghxKiMU), 为保证实验节奏，这里不做展开。

所以接下来使用 pprof 排查时，我们在乎的不是什么地方在占用大量内存，而是什么地方在不停地申请内存，这两者是有区别的。

由于内存的申请与释放频度是需要一段时间来统计的，所有我们保证炸弹程序已经运行了几分钟之后，再运行命令：

```
go tool pprof http://localhost:6060/debug/pprof/allocs


```

同样使用 top、list、web 大法：

![](https://image.wolfogre.com/7c0bc1cc6839e904865d5c20e363a2876aa1d99076bfdce81e439fbbde4cc42f.png)

![](https://image.wolfogre.com/087bc92dffda268e29817ccdfd2f61c94a0a1b7e74298a00c8b568b07db1a3b3.png)

可以看到 `github.com/wolfogre/go-pprof-practice/animal/canidae/dog.(*Dog).Run` 会进行无意义的内存申请，而这个函数又会被频繁调用，这才导致程序不停地进行 GC:

```
func (d *Dog) Run() {
	log.Println(d.Name(), "run")
	_ = make([]byte, 16 * constant.Mi)
}


```

这里有个小插曲，你可尝试一下将 `16 * constant.Mi` 修改成一个较小的值，重新编译运行，会发现并不会引起频繁 GC，原因是在 golang 里，对象是使用堆内存还是栈内存，由编译器进行逃逸分析并决定，如果对象不会逃逸，便可在使用栈内存，但总有意外，就是对象的尺寸过大时，便不得不使用堆内存。所以这里设置申请 16 MiB 的内存就是为了避免编译器直接在栈上分配，如果那样得话就不会涉及到 GC 了。

我们同样注释掉问题代码，重新编译执行，可以看到这一次，程序的 GC 频度要低很多，以至于短时间内都看不到 GC 日志了：

![](https://image.wolfogre.com/32e5e14a2d2000367e988242fb463d8ae809d44fac7093c5a9b06e39ecb81370.jpg)

排查协程泄露
------

由于 golang 自带内存回收，所以一般不会发生内存泄露。但凡事都有例外，在 golang 中，协程本身是可能泄露的，或者叫协程失控，进而导致内存泄露。

我们在浏览器里可以看到，此时程序的协程数已经多达 106 条：

![](https://image.wolfogre.com/a8e2b63a63274980eecbac63f848356039f719559e4664e8937f271403aa5d75.jpg)

虽然 106 条并不算多，但对于这样一个小程序来说，似乎还是不正常的。为求安心，我们再次是用 pprof 来排查一下：

```
go tool pprof http://localhost:6060/debug/pprof/goroutine


```

同样是 top、list、web 大法：

![](https://image.wolfogre.com/75512a296f062e71f29251273f8496ea5deedcb9f104b99342f8dccb9bb1277e.png)

![](https://image.wolfogre.com/6bbba6ec436951c6857ba231dcb14cefce7133d6833ece711230264e2bd9ce9c.png)

可能这次问题藏得比较隐晦，但仔细观察还是不难发现，问题在于 `github.com/wolfogre/go-pprof-practice/animal/canidae/wolf.(*Wolf).Drink` 在不停地创建没有实际作用的协程：

```
func (w *Wolf) Drink() {
	log.Println(w.Name(), "drink")
	for i := 0; i < 10; i++ {
		go func() {
			time.Sleep(30 * time.Second)
		}()
	}
}


```

可以看到，Drink 函数每次会释放 10 个协程出去，每个协程会睡眠 30 秒再退出，而 Drink 函数又会被反复调用，这才导致大量协程泄露，试想一下，如果释放出的协程会永久阻塞，那么泄露的协程数便会持续增加，内存的占用也会持续增加，那迟早是会被操作系统杀死的。

我们注释掉问题代码，重新编译运行可以看到，协程数已经降到 4 条了：

![](https://image.wolfogre.com/cd7e95c42e61bcd16d65ea7892adb09b668b62b9f2c47966982a6e779a3a8917.jpg)

排查锁的争用
------

到目前为止，我们已经解决这个炸弹程序的所有资源占用问题，但是事情还没有完，我们需要进一步排查那些会导致程序运行慢的性能问题，这些问题可能并不会导致资源占用，但会让程序效率低下，这同样是高性能程序所忌讳的。

我们首先想到的就是程序中是否有不合理的锁的争用，我们倒一倒，回头看看上一张图，虽然协程数已经降到 4 条，但还显示有一个 mutex 存在争用问题。

相信到这里，你已经触类旁通了，无需多言，开整。

```
go tool pprof http://localhost:6060/debug/pprof/mutex


```

同样是 top、list、web 大法：

![](https://image.wolfogre.com/7484245a189adcd85181806fe1e380e6c043f0188d0d7949448819b3f55ce20f.jpg)

![](https://image.wolfogre.com/4413eeb48e86038a581ee76b53476099c026a1e1be5a8dc7f4b8f88a46711d3d.png)

可以看出来这问题出在 `github.com/wolfogre/go-pprof-practice/animal/canidae/wolf.(*Wolf).Howl`。但要知道，在代码中使用锁是无可非议的，并不是所有的锁都会被标记有问题，我们看看这个有问题的锁那儿触雷了。

```
func (w *Wolf) Howl() {
	log.Println(w.Name(), "howl")

	m := &sync.Mutex{}
	m.Lock()
	go func() {
		time.Sleep(time.Second)
		m.Unlock()
	}()
	m.Lock()
}


```

可以看到，这个锁由主协程 Lock，并启动子协程去 Unlock，主协程会阻塞在第二次 Lock 这儿等待子协程完成任务，但由于子协程足足睡眠了一秒，导致主协程等待这个锁释放足足等了一秒钟。虽然这可能是实际的业务需要，逻辑上说得通，并不一定真的是性能瓶颈，但既然它出现在我写的 “炸弹” 里，就肯定不是什么 “业务需要” 啦。

所以，我们注释掉这段问题代码，重新编译执行，继续。

排查阻塞操作
------

好了，我们开始排查最后一个问题。

在程序中，除了锁的争用会导致阻塞之外，很多逻辑都会导致阻塞。

![](https://image.wolfogre.com/d830dc8a7323e7106d05857e97ceed34541212f47cb30071fe4a67ff8ab15aeb.jpg)

可以看到，这里仍有 2 个阻塞操作，虽然不一定是有问题的，但我们保证程序性能，我们还是要老老实实排查确认一下才对。

```
go tool pprof http://localhost:6060/debug/pprof/block


```

top、list、web，你懂得。

![](https://image.wolfogre.com/1f2e7e8ecc6f6ee7e0f6f1890a8b3d0293ba2b52be3eb1ac1df15d2d169029f7.jpg)

![](https://image.wolfogre.com/12ac6115ebdee8b2e1217e3d47442298d714f2e3bd61b2132911ba694dabc615.png)

可以看到，阻塞操作位于 `github.com/wolfogre/go-pprof-practice/animal/felidae/cat.(*Cat).Pee`：

```
func (c *Cat) Pee() {
	log.Println(c.Name(), "pee")

	<-time.After(time.Second)
}


```

你应该可以看懂，不同于睡眠一秒，这里是从一个 channel 里读数据时，发生了阻塞，直到这个 channel 在一秒后才有数据读出，这就导致程序阻塞了一秒而非睡眠了一秒。

这里有个疑点，就是上文中是可以看到有两个阻塞操作的，但这里只排查出了一个，我没有找到其准确原因，但怀疑另一个阻塞操作是程序监听端口提供 porof 查询时，涉及到 IO 操作发生了阻塞，即阻塞在对 HTTP 端口的监听上，但我没有进一步考证。

不管怎样，恭喜你完整地完成了这个实验。

思考题
---

另有一些问题，虽然比较重要，但碍于篇幅（其实是我偷懒），就以思考题的形式留给大家了。

1.  每次进入交互式终端，都会提示 “type ‘help’ for commands, ‘o’ for options”，你有尝试过查看有哪些命令和哪些选项吗？有重点了解一下“sample_index” 这个选项吗？
2.  关于内存的指标，有申请对象数、使用对象数、申请空间大小、使用空间大小，本文用的是什么指标？如何查看不同的指标？（提示：在内存实验中，试试查看、修改 “sample_index” 选项。）
3.  你有听说过火焰图吗？要不要在试验中生成一下火焰图？
4.  main 函数中的 `runtime.SetMutexProfileFraction` 和 `runtime.SetBlockProfileRate` 是如何影响指标采样的？它们的参数的含义是什么？
5.  评论区有很多很有价值的提问，你有注意到吗？

最后
--

碍于我的水平有限，实验中还有很多奇怪的细节我只能暂时熟视无睹（比如 “排查内存占用过高” 一节中，为什么实际申请的是 1.5 GiB 内存），如果这些奇怪的细节你也发现了，并痛斥我假装睁眼瞎，那么我的目的就达到了……

——还记得吗，本文的目的是让你熟悉使用 pprof，消除对它的陌生感，并能借用它进一步得了解 golang。而你通过这次试验，发现了程序的很多行为不同于你以往的认知或假设，并抱着好奇心，开始向比深处更深处迈进，那么，我何尝不觉得这是本文的功德呢？

与君共勉。