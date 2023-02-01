> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [juejin.cn](https://juejin.cn/post/7123826435357147166)

实际开发工作的时候，我们都是在自己的分支开发，然后将自己的分合并到主分支，那合并分支用 2 种操作，这 2 种操作有什么区别呢？

git 上新建一个项目，默认是有 master 分支的，将项目克隆到本地，我们的准备工作就完成了

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3a014972d03e4fb587dc75720a16601b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

同学 A：
-----

执行 git log ，可以看到有一个提交记录，是初始化提交

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9a908b4ae4964113ad66df84570be069~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

新增一个文件 a.txt, 再次查看我们的提交记录，有 2 条提交记录了

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dc33201537284f2d8a02c743be74cd8e~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

这个时候将本地新 commit 的记录 push 到远程仓库，就可以看到我们的 2 次提交了

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ad55a0b14bae4b6189cc4a024bc7171f~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

同学 B：
-----

同学 B 在已经有提交记录的 master 分支上，检出分支 dev，并将分支推送到远程分支，并进行自己的开发

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/44941ab499264cef9f6ff4b4e6ec2480~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

查看远程仓库，多了一个 dev 分支

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2196095857be49c4aa31707e2f32d6f6~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

此时的 git 分支类图是这样的

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4fcd452041e441fdb176da2e3a3b78be~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

此时 B 同学开始进行开发，完成了自己的 3 次提交工作，使用 git log 看一下

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/43bf2d74a0904133a8697de37f1ed4f8~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

此时 git 的分支类图是这样子的

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/46de5b353ec64dbab3ccee84ea02c868~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

重点
--

现在有这样一个现实的请况，就是 B 同学准备进行第 4 次提交的时候，同学 A 在 master 主分支上进行了一次提交，master 的提交已经向前走了

此时的 git 分支类图是这样的

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/58364605e1c246e686b48261ef0b6384~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

此时我们知道 B 同学开发的 dev 分支是基于 C2 提交点切出来的，而这个时候 master 分支已经被更新了

如果 B 同学开发完毕，需要将其所作的功能合并到 master 分支 ，他可以有两种选择：

直接 git merge，那么这个时候会这么做

（1）找到 master 和 dev 的共同祖先，即 C2

（2）将 dev 的最新提交 C5 和 master 的最新提交即 C6 合并成一个新的提交 C7，有冲突的话，解决冲突

（3）将 C2 之后的 dev 和 master 所有提交点，按照**提交时间**合并到 master

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9ec0bdda85904eada018d424468a0217~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

直接 git rebase

切换分支到需要 rebase 的分支，这里是 dev 分支

执行 git rebase master，有冲突就解决冲突，解决后直接 git add . 再 git rebase --continue 即可

发现采用 rebase 的方式进行分支合并，整个 master 分支并没有多出一个新的 commit，原来 dev 分支上的那几次（C3，C4，C5）commit 记录在 rebase 之后其 hash 值发生了变化，不在是当初在 dev 分支上提交的时候的 hash 值了，但是提交的内容被全部复制保留了，并且整个 master 分支的 commit 记录呈线性记录

此时 git 的分支类图

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/716730b4b1cb45b48e84f4b01c8e000d~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp?)

总结
--

git merge 会让 2 个分支的提交按照提交时间进行排序，并且会把最新的 2 个 commit 合并成一个 commit。最后的分支树呈现非线性的结构

git reabse 将 dev 的当前提交复制到 master 的最新提交之后，会形成一个线性的分支树

参考👀
----

[git merge 和 git rebase 的区别](https://link.juejin.cn?target=https%3A%2F%2Fwww.cnblogs.com%2Frainboy2010%2Fp%2F12671633.html "https://www.cnblogs.com/rainboy2010/p/12671633.html")

[# git merge 和 git rebase 区别](https://link.juejin.cn?target=https%3A%2F%2Fblog.csdn.net%2Fweixin_41829196%2Farticle%2Fdetails%2F124457162 "https://blog.csdn.net/weixin_41829196/article/details/124457162")