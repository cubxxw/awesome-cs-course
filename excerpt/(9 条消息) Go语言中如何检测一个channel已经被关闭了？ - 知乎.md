> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.zhihu.com](https://www.zhihu.com/question/450188866?utm_id=0)

> Go语言中是否提供了某种机制能保证我push数据进入channel前可以检测到该channel是关闭了的？(或者某种其…

![](https://pic1.zhimg.com/v2-2b368e4b33fd6412a7083d401f571bbd_l.jpg?source=1940ef5c)qiya.css-1cd9gw4{margin-left:.3em;}

**个人理解哈。先说结论：golang 的 channel 不应该直接对 channel 本身判断是否 close 了。**

**以前我分享过一个文章，叫做：Go 为什么没有判断 close 的接口？**

*   [Go 为什么没有判断 close 的接口？](https://www.zhihu.com/question/450188866)
*   [关闭 channel 究竟做了什么？](https://www.zhihu.com/question/450188866)

*   [`closechan`](https://www.zhihu.com/question/450188866)

*   [一个判断 chan 是否 close 的函数](https://www.zhihu.com/question/450188866)

*   [思考方法一：通过 “写”chan 实现](https://www.zhihu.com/question/450188866)
*   [思考方法二：通过 “读”chan 实现](https://www.zhihu.com/question/450188866)
*   [chan close 原则](https://www.zhihu.com/question/450188866)
*   [其实并不需要 `isChanClose` 函数 !!!](https://www.zhihu.com/question/450188866)

*   [怎么优雅关闭 chan ？](https://www.zhihu.com/question/450188866)

*   [方法一：panic-recover](https://www.zhihu.com/question/450188866)
*   [方法二：sync.Once](https://www.zhihu.com/question/450188866)
*   [方法三：事件同步来解决](https://www.zhihu.com/question/450188866)

*   [总结](https://www.zhihu.com/question/450188866)

**Go 为什么没有判断 close 的接口？**

相信大家初学 golang chan 的时候应该都遇到过 **"send on closed channel"** 的 panic 。这个 panic 是当你意图往一个已经 close 的 channel 里面投递元素的时候触发。那么你当你第一次遇到这个问题是否想过 channel 是否能提供一个接口方法来判断是否已经 close 了？我想过这个问题，但是把 chan 的源代码翻了个遍没有找到。为什么？

我先 hold 这个问题，我们捋一下跟 channel close 相关的事情，主要思考到 3 个问题：

1.  关闭 channel 究竟做了什么 ？
2.  怎么避免 close channel 导致的 panic 问题 ？
3.  怎么优雅的关闭 channel ？

**关闭 channel 究竟做了什么？**
----------------------

首先，用户可以 close channel，如下：

```
c := make(chan int)
// ...
close(c)


```

用 gdb 或者 delve 调试下就能发现 close 一个 channel，编译器会转换成 `closechan` 函数，在这个函数里是关闭 channel 的全部实现了，我们可以分析下。

### **`closechan`**

对应编译函数为 `closechan` ，该函数很简单，大概做 3 个事情：

1.  标志位置 1 ，也就是 `c.closed = 1`；
2.  释放资源，唤醒所有等待取元素的协程；
3.  释放资源，唤醒所有等待写元素的协程；

```
func closechan(c *hchan) {
    // 以下为锁内操作
    lock(&c.lock)
    // 不能重复 close 一个 channel，否则 panic
    if c.closed != 0 {
        unlock(&c.lock)
        panic(plainError("close of closed channel"))
    }

    // closed 标志位置 1
    c.closed = 1

    var glist gList
    // 释放所有等待取元素的 waiter 资源
    for {
        // 等待读的 waiter 出队
        sg := c.recvq.dequeue()
        // 资源一个个销毁
        if sg.elem != nil {
            typedmemclr(c.elemtype, sg.elem)
            sg.elem = nil
        }
        gp := sg.g
        gp.param = nil
        //  相应 goroutine 加到统一队列，下面会统一唤醒

        glist.push(gp)
    }

    // 释放所有等待写元素的 waiter 资源（他们之后将会 panic）
    for {
        // 等待写的 waiter 出队
        sg := c.sendq.dequeue()
        // 资源一个个销毁
        sg.elem = nil
        gp := sg.g
        gp.param = nil
        // 对应 goroutine 加到统一队列，下面会统一唤醒
        glist.push(gp)
    }
    unlock(&c.lock)

    // 唤醒所有的 waiter 对应的 goroutine （这个协程列表是上面 push 进来的）
    for !glist.empty() {
        gp := glist.pop()
        gp.schedlink = 0
        goready(gp, 3)
    }
}


```

通过上面的代码逻辑，我们窥视到两个重要的信息：

1.  close chan 是有标识位的；
2.  close chan 是会唤醒哪些等待的人们的；

但是很奇怪的是，我们 golang 官方没有提供一个接口用于判断 chan 是否关闭？那我们能不能实现一个判断 chan 是否 close 的方法呢？

**一个判断 chan 是否 close 的函数**
--------------------------

怎么实现？首先 `isChanClose` 函数有几点要求：

1.  能够指明确实是 close 的；
2.  任何时候能够正常运行，且有返回的（非阻塞）；

回忆下 **[golang channel 最详细剖析](https://www.zhihu.com/question/450188866)** 章节，想想 `send`, `recv` 相关的函数，我们可以知道，当前 channel 给到用户的使用姿势本质上只有两种：读和写，我们实现的 `isChanClose` 也只能在这个基础上做。

*   写：`c <- x`
*   读：`<-c` 或 `v := <-c` 或 `v, ok := <-c`

### **思考方法一：通过 “写”chan 实现**

“写” 肯定不能作为判断，总不能为了判断 chan 是否 close，我尝试往里面写数据吧？这个会导致 `chansend` 里面直接 panic 的，如下：

```
func chansend(c *hchan, ep unsafe.Pointer, block bool, callerpc uintptr) bool {
        //  ...
        // 当 channel close 之后的处理逻辑
        if c.closed != 0 {
            unlock(&c.lock)
            panic(plainError("send on closed channel"))
        }
        //  ...
}


```

当然了，你路子要是野一点，这样做技术上也能实现，因为 panic 是可以捕捉的，只不过这也太野了吧，不推荐。

### **思考方法二：通过 “读”chan 实现**

“读” 来判断。分析函数 `chanrecv` 可以知道，当尝试从一个已经 close 的 chan 读数据的时候，返回 （selected=true, received=false），我们通过 received = false 即可知道 channel 是否 close 。`chanrecv` 有如下代码：

```
func chanrecv(c *hchan, ep unsafe.Pointer, block bool) (selected, received bool) {
    // ...
    // 当 channel close 之后的处理逻辑
    if c.closed != 0 && c.qcount == 0 {
        unlock(&c.lock)
        if ep != nil {
            typedmemclr(c.elemtype, ep)
        }
        return true, false
    }
    // ...
}


```

所以，我们现在知道了，可以通过 “读” 的效果来判断，但是我们不能直接写成这样：

```
// 错误示例
func isChanClose(ch chan int) bool {
    _, ok := <- c
}


```

上面是个**错误示例**，因为 `_, ok := <-c` 编译出来的是 `chanrecv2` ，这个函数 block 赋值 true 传入的，所以当 c 是正常的时候，这里是阻塞的，所以这个不能用来作为一个正常的函数调用，因为会卡死协程，怎么解决这个问题？用 `select` 和 `<-chan` 来结合可以解决这个问题，`select` 和 `<-chan` 结合起来是对应 `selectnbrecv` 和 `selectnbrecv2` 这两个函数，这两个函数是非阻塞的（`block = false` ）。

正确示例：

```
func isChanClose(ch chan int) bool {
    select {
    case _, received := <- ch:
        return !received
    default:
    }
    return false
}


```

网上很多人举了一个 `isChanClose` 错误的例子，错误示例：

```
func isChanClose(ch chan int) bool {
    select {
    case  <- ch:
        return true
    default:
    }
    return false
}


```

思考下：为什么第一个例子是对的，第二个例子是错的？

因为，第一个例子编译出来对应的函数是 `selectnbrecv2` ，第二个例子编译出来对应的是 `selectnbrecv1` ，这两个函数的区别在于 `selectnbrecv2` 多了一个返回参数 `received`，只有这个函数才能指明是否元素出队成功，而 `selected` 只是判断是否要进到 select case 分支。我们通过 `received` 这个返回值（其实是一个入参，只不过是指针类型，函数内可修改）来反向推断 chan 是否 close 了。

**小结**：

1.  case 的代码必须是 `_, received := <- ch` 的形式，如果仅仅是 `<- ch` 来判断，是错的逻辑，因为我们关注的是 `received` 的值；
2.  select 必须要有 default 分支，否则会阻塞函数，我们这个函数要保证一定能正常返回；

### **chan close 原则**

1.  永远不要尝试在读取端关闭 channel ，写入端无法知道 channel 是否已经关闭，往已关闭的 channel 写数据会 panic ；
2.  一个写入端，在这个写入端可以放心关闭 channel；
3.  多个写入端时，不要在写入端关闭 channel ，其他写入端无法知道 channel 是否已经关闭，关闭已经关闭的 channel 会发生 panic （你要想个办法保证只有一个人调用 close）；
4.  channel 作为函数参数的时候，最好带方向；

其实这些原则只有一点：一定要是安全的是否才能去 close channel 。

### **其实并不需要 `isChanClose` 函数 !!!**

上面实现的 `isChanClose` 是可以判断出 channel 是否 close，但是适用场景优先，因为可能等你 `isChanClose` 判断的时候返回值 false，你以为 channel 还是正常的，但是下一刻 channel 被关闭了，这个时候往里面 “写” 数据就又会 panic ，如下：

```
if isChanClose( c ) {
    // 关闭的场景，exit  
    return
}
// 未关闭的场景，继续执行（可能还是会 panic）
c <- x


```

因为判断之后还是有时间窗，所以 `isChanClose` 的适用还是有限，那么是否有更好的办法？

我们换一个思路，你其实并不是一定要判断 channel 是否 close，真正的目的是：**安全的使用 channel，避免使用到已经关闭的 closed channel，从而导致 panic** 。

这个问题的本质上是保证一个事件的时序，官方推荐通过 `context` 来配合使用，我们可以通过一个 ctx 变量来指明 close 事件，而不是直接去判断 channel 的一个状态。举个栗子：

```
select {
case <-ctx.Done():
    // ... exit
    return
case v, ok := <-c:
    // do something....
default:
    // do default ....
}


```

`ctx.Done()` 事件发生之后，我们就明确不去读 channel 的数据。

或者

```
select {
case <-ctx.Done():
    // ... exit
    return
default:
    // push 
    c <- x
}


```

`ctx.Done()` 事件发生之后，我们就明确不写数据到 channel ，或者不从 channel 里读数据，那么保证这个时序即可。就一定不会有问题。

我们只需要确保一点：

1.  触发时序保证：一定要先触发 ctx.Done() 事件，再去做 close channel 的操作，保证这个时序的才能保证 select 判断的时候没有问题；

1.  只有这个时序，才能保证在获悉到 Done 事件的时候，一切还是安全的；

  
4.  条件判断顺序：select 的 case 先判断 ctx.Done() 事件，这个很重要哦，否则很有可能先执行了 chan 的操作从而导致 panic 问题；

**怎么优雅关闭 chan ？**
-----------------

### **方法一：panic-recover**

关闭一个 channel 直接调用 close 即可，但是关闭一个已经关闭的 channel 会导致 panic，怎么办？panic-recover 配合使用即可。

```
func SafeClose(ch chan int) (closed bool) {
    defer func() {
        if recover() != nil {
            closed = false
        }
    }()
    // 如果 ch 是一个已经关闭的，会 panic 的，然后被 recover 捕捉到；
    close(ch)
    return true
}


```

这并不优雅。

### **方法二：sync.Once**

可以使用 `sync.Once` 来确保 `close` 只执行一次。

```
type ChanMgr struct {
    C    chan int
    once sync.Once
}
func NewChanMgr() *ChanMgr {
    return &ChanMgr{C: make(chan int)}
}
func (cm *ChanMgr) SafeClose() {
    cm.once.Do(func() { close(cm.C) })
}


```

这看着还可以。

### **方法三：事件同步来解决**

对于关闭 channel 这个我们有两个简要的原则：

1.  永远不要尝试在读端关闭 channel ；
2.  永远只允许一个 goroutine（比如，只用来执行关闭操作的一个 goroutine ）执行关闭操作；

可以使用 `sync.WaitGroup` 来同步这个关闭事件，遵守以上的原则，举几个例子：

**第一个例子：一个 sender**

```
package main

import "sync"

func main() {
    // channel 初始化
    c := make(chan int, 10)
    // 用来 recevivers 同步事件的
    wg := sync.WaitGroup{}

    // sender（写端）
    go func() {
        // 入队
        c <- 1
        // ...
        // 满足某些情况，则 close channel
        close(c)
    }()

    // receivers （读端）
    for i := 0; i < 10; i++ {
        wg.Add(1)
        go func() {
            defer wg.Done()
            // ... 处理 channel 里的数据
            for v := range c {
                _ = v
            }
        }()
    }
    // 等待所有的 receivers 完成；
    wg.Wait()
}


```

这里例子里面，我们在 sender 的 goroutine 关闭 channel，因为只有一个 sender，所以关闭自然是安全的。receiver 使用 `WaitGroup` 来同步事件，receiver 的 for 循环只有在 channel close 之后才会退出，主协程的 `wg.Wait()` 语句只有所有的 receivers 都完成才会返回。所以，事件的顺序是：

1.  写端入队一个整形元素
2.  关闭 channel
3.  所有的读端安全退出
4.  主协程返回

一切都是安全的。

**第二个例子：多个 sender**

```
package main

import (
    "context"
    "sync"
    "time"
)

func main() {
    // channel 初始化
    c := make(chan int, 10)
    // 用来 recevivers 同步事件的
    wg := sync.WaitGroup{}
    // 上下文
    ctx, cancel := context.WithCancel(context.TODO())

    // 专门关闭的协程
    go func() {
        time.Sleep(2 * time.Second)
        cancel()
        // ... 某种条件下，关闭 channel
        close(c)
    }()

    // senders（写端）
    for i := 0; i < 10; i++ {
        go func(ctx context.Context, id int) {
            select {
            case <-ctx.Done():
                return
            case c <- id: // 入队
                // ...
            }
        }(ctx, i)
    }

    // receivers（读端）
    for i := 0; i < 10; i++ {
        wg.Add(1)
        go func() {
            defer wg.Done()
            // ... 处理 channel 里的数据
            for v := range c {
                _ = v
            }
        }()
    }
    // 等待所有的 receivers 完成；
    wg.Wait()
}


```

这个例子我们看到有多个 sender 和 receiver ，这种情况我们还是要保证一点：close(ch) 操作的只能有一个人，我们单独抽出来一个 goroutine 来做这个事情，并且使用 context 来做事件同步，事件发生顺序是：

1.  10 个写端协程（sender）运行，投递元素；
2.  10 个读端协程（receiver）运行，读取元素；
3.  2 分钟超时之后，单独协程执行 `close(channel)` 操作；
4.  主协程返回；

一切都是安全的。

**总结**
------

1.  channel 并没有直接提供判断是否 close 的接口，官方推荐使用 context 和 select 语法配合使用，事件通知的方式，达到优雅判断 channel 关闭的效果；
2.  channel 关闭姿势也有讲究，永远不要尝试在读端关闭，永远保持一个关闭入口处，使用 sync.WaitGroup 和 context 实现事件同步，达到优雅关闭效果；

文章链接：

Go 最细节篇 — chan 为啥没有判断 close 的接口 ?
---------------------------------

[](https://link.zhihu.com/?target=https%3A//mp.weixin.qq.com/s/8Ks_6Y3wI39llLPxakP1pg)

golang chan 最详细原理剖析，全面源码分析！看完不可能不懂的！
------------------------------------

[](https://link.zhihu.com/?target=https%3A//mp.weixin.qq.com/s/03z4LWUDpK7g5s9nFHon9A)

希望对你有所帮助，谢谢。

![](https://pic1.zhimg.com/v2-381e03e1fba8ccff6cacb08457a2ca7e_l.jpg?source=1940ef5c)良猿

**Channel**
-----------

channel（通道）在 go 语言中通常用于 goroutine 之间通信，可以连接不同的 goroutine ， channel 是一种可以让一个 goroutine 发送特定值到另一个 goroutine 的通信机制。

channel 是一种特殊的类型，类似于数据结构中的队列，其中的元素遵循先入先出的规则，同时每一个通道都需要指定对应的类型，指定类型之后该通道就只能发送或接受对应类型的数据。

**通道声明**
--------

```
// 语法：
var 变量名 chan 元素类型
// 示例：
var ch1 chan int     // 声明一个int类型的通道
var ch2 chan string   // 声明一个string类型的通道


```

**通道初始化**
---------

通道是引用类型，其默认值为 nil ，如果一个通道只声明没有初始化那么直接使用这个通道会触发 panic 。**通道初始化可以使用 make 函数进行。**

语法：

```
make(chan 元素类型, [缓冲大小])


```

使用 make 函数初始化通道时，需要指定通道的元素类型，通道的缓冲大小可以省略。

代码示例：

```
ch1 := make(chan int)   // 初始化一个int类型的通道
ch2 := make(chan string, 2)  // 初始化一个string类型的通道，且缓冲区大小为2


```

**通道操作**
--------

在 go 语言中，通道一共有 3 种操作，分别是：发送、接收和关闭，其中发送和接收都需要使用到一个符号：`<-` ，关闭使用 `close` 函数。

初始化一个通道：

```
ch := make(chan int)


```

发送：将一个 int 类型发送到通道里面

```
ch <- 10   // 把 10 发送到通道 ch 中   


```

接收：从通道里面接收一个 int 类型的元素

```
x := <- ch   // 从通道 ch 中接收元素并赋值给变量 x


```

关闭：关闭一个通道

```
close(ch)  // 调用内置的 close 函数关闭通道 ch


```

代码示例：

```
package main

import (
 "fmt"
 "time"
)

func main() {
 ch := make(chan int)
 go send(ch)
 go receive(ch)
 time.Sleep(time.Second)
}

func send(ch chan int) {
 ch <- 10
 fmt.Println("已发送数字 10 到通道中")
}

func receive(ch chan int) {
 x := <- ch
 fmt.Println("从通道接收到元素：", x)
}


```

上述代码中启动了两个 goroutine ，一个将元素发送到通道中，另一个从通道中取出元素。

运行结果：

![](https://picx.zhimg.com/v2-b2ba85bf974c201b1eccd21e817a567e_r.jpg?source=1940ef5c)

**无缓冲通道**
---------

在使用 make 函数初始化通道时，如果没有指定缓冲区的大小，那么就默认是无缓冲通道，例如上面的代码示例中使用的就是无缓冲通道，** 无缓冲通道在发送值的时候必须要有一个对应的 goroutine 接收值才行，** 否则就会报错。

代码示例：

```
func main() {
 ch := make(chan int)
 ch <- 10  // 发送一个值到通道中
    fmt.Println("成功发送 10 到通道中")
}


```

上述代码中，使用的就是无缓冲通道，所以在第 3 行发送一个值到通道中时就会报错，导致程序运行失败。

运行结果：

![](https://picx.zhimg.com/v2-75232d54a3806206f9b1f537d5efd2dc_r.jpg?source=1940ef5c)

**有缓冲通道**
---------

有缓冲通道表示通道里面可以缓冲一部分数据，发送的数据可以暂时的保存在通道的缓冲区，不需要有 goroutine 立即去接收。要使用有缓冲通道只需要在使用 make 函数初始化通道时指定缓冲区的大小即可，只要指定的缓冲区的大小大于 0 就行，缓冲区大小表示通道内能够暂存的元素个数。

针对上面无缓冲的代码，只需要在初始化时指定缓冲区大小，使其变成有缓冲通道，就不会报错了。

代码示例：

```
func main() {
 ch := make(chan int, 1)  // 初始化一个缓冲区为 1 的有缓冲通道
 ch <- 10     // 发送一个值到通道中
    fmt.Println("成功发送 10 到通道中")
}


```

运行结果：

![](https://picx.zhimg.com/v2-a9a2b4fcf282818c8bdedfb71a432d4e_r.jpg?source=1940ef5c)

**close**
---------

可以使用 close 关闭通道，当一个通道不再往里面发送值或者接收值的时候，就需要将通道关闭，通道是可以被垃圾回收机制回收的，它和关闭文件不一样，在结束操作之后关闭文件是必须的，但关闭通道不是必须的。

代码示例：

```
func main() {
 ch := make(chan int, 2)
 go send(ch)
 go receive(ch)
 time.Sleep(time.Second)
}

func send(ch chan int) {
 for i := 0; i < 10; i++ {
  ch <- i
 }
 close(ch)
}

func receive(ch chan int) {
 for  {
  x, ok := <- ch
  if ok {
   fmt.Println("接收到的值：", x)
  } else {
   break
  }
 }
}


```

代码中 send 方法循环发送从 0 到 9 到通道中，当发送完成之后就关闭通道，然后 receive 循环循环接收通道中的值，当通道被关闭之后接收到的 ok 值为 false ，则跳出循环。

关闭通道注意事项：

1.  往一个已关闭的通道发送值会发生 panic。
2.  从一个已关闭的通道接收值会一直接收到通道为空，通道为空之后再接收返回通道元素类型的默认值。
3.  对一个已关闭的通道再次关闭会发生 panic

**循环接收**
--------

在上面的代码中，receive 函数通过接收值的时候接收 ok 返回值判断通道是否关闭，除了使用这种方法之外，还可以使用`for range`循环接收的方式判断发送方是否已经关闭通道，如果发送方关闭通道了，那么循环就会自动退出。

代码示例：

```
package main

import (
 "fmt"
 "time"
)

func main() {
 ch := make(chan int, 2)
 go send(ch)
 go receive(ch)
 time.Sleep(time.Second)
}

func send(ch chan int) {
 for i := 0; i < 10; i++ {
  ch <- i
 }
 close(ch)
}

func receive(ch chan int) {
 for i := range ch {
  fmt.Println("接收到的值：", i)
 }
}


```

上述代码 receive 函数中通过 for range 的方式从通道中获取值，当发送方关闭了通道之后，for range 循环也就自动退出了。

**单向通道**
--------

之前介绍的一直都是一个通道既可以发送值也可以接收值，在并发编程中，通道经常被作为参数传递给不同的函数，有时候需要在不同的函数中对通道进行限制，这就需要用到单向通道了。

*   `chan <- 类型`：只能发送值到通道中，不能从通道中接收值
*   `<- chan 类型`：只能从通道中接收值，不能发送值到通道中

对上面代码进行修改：

```
package main

import (
 "fmt"
 "time"
)

func main() {
 ch := make(chan int, 2)
 go send(ch)
 go receive(ch)
 time.Sleep(time.Second)
}

func send(ch chan <- int) {
 for i := 0; i < 10; i++ {
  ch <- i
 }
 close(ch)
}

func receive(ch <- chan int) {
 for i := range ch {
  fmt.Println("接收到的值：", i)
 }
}


```

在代码中，send 函数的参数为只能发送类型，receive 函数的参数为只能接收类型，所以在 send 函数体内部就不能从通道接收，在 receive 函数体内部就不能发送值到通道中。例如在 receive 内往通道发送一个值就会报错：`send to the receive-only type <-chan int`。

运行结果：

![](https://pica.zhimg.com/v2-d0e12c19c6c49381d152a2b49d670a4f_r.jpg?source=1940ef5c)