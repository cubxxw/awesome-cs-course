> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [juejin.cn](https://juejin.cn/post/6844903921794859021)

1、简单概括
------

先用一张图来理一下`git fetch`和`git pull`的概念：

![](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/8/12/16c84ff492a9de1a~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.awebp)

可以简单的概括为：

`git fetch`是将远程主机的最新内容拉到本地，用户在检查了以后决定是否合并到工作本机分支中。

而`git pull` 则是将远程主机的最新内容拉下来后直接合并，即：`git pull = git fetch + git merge`，这样可能会产生冲突，需要手动解决。

下面我们来详细了解一下`git fetch` 和`git pull` 的用法。  

2、分支的概念
-------

在介绍两种方法之前，我们需要先了解一下分支的概念：  
分支是用来标记特定代码的提交，每一个分支通过 SHA1sum 值来标识，所以对分支的操作是轻量级的，你改变的仅仅是 SHA1sum 值。

如下图所示，当前有 2 个分支，A,C,E 属于 master 分支，而 A,B，D,F 属于 dev 分支。

```
A----C----E（master）
 \
  B---D---F(dev)复制代码

```

*   1
*   2
*   3

它们的 head 指针分别指向 E 和 F，对上述做如下操作：

```
git checkout master  //选择or切换到master分支
git merge dev        //将dev分支合并到当前分支(master)中复制代码

```

*   1
*   2

合并完成后：

```
A---C---E---G(master)
 \         /
  B---D---F（dev）复制代码

```

*   1
*   2
*   3

现在 ABCDEFG 属于 master，G 是一次合并后的结果，是将 E 和Ｆ的代码合并后的结果，可能会出现冲突。而 ABDF 依然属于 dev 分支。可以继续在 dev 的分支上进行开发:

```
A---C---E---G---H(master)
 \         /
  B---D---F---I（dev）
复制代码

```

*   1
*   2
*   3
*   4

分支（branch）的基本操作：

```
git branch //查看本地所有分支 

git branch -r //查看远程所有分支

git branch -a //查看本地和远程的所有分支

git branch <branchname> //新建分支

git branch -d <branchname> //删除本地分支

git branch -d -r <branchname> //删除远程分支，删除后还需推送到服务器
git push origin:<branchname>  //删除后推送至服务器

git branch -m <oldbranch> <newbranch> //重命名本地分支
/**
*重命名远程分支：
*1、删除远程待修改分支
*2、push本地新分支到远程服务器
*/

//git中一些选项解释:

-d
--delete：删除

-D
--delete --force的快捷键

-f
--force：强制

-m
--move：移动或重命名

-M
--move --force的快捷键

-r
--remote：远程

-a
--all：所有复制代码

```

3、git fetch 用法
--------------

git fetch 命令：

```
$ git fetch <远程主机名> //这个命令将某个远程主机的更新全部取回本地复制代码

```

*   1

如果只想取回特定分支的更新，可以指定分支名：

```
$ git fetch <远程主机名> <分支名> //注意之间有空格复制代码

```

*   1

最常见的命令如取回`origin` 主机的`master` 分支：

```
$ git fetch origin master复制代码

```

*   1

取回更新后，会返回一个`FETCH_HEAD` ，指的是某个 branch 在服务器上的最新状态，我们可以在本地通过它查看刚取回的更新信息：

```
$ git log -p FETCH_HEAD复制代码

```

*   1

如图：  
![](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/8/12/16c84ff492b700ad~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.awebp)

可以看到返回的信息包括更新的文件名，更新的作者和时间，以及更新的代码（19 行红色 [删除] 和绿色 [新增] 部分）。

我们可以通过这些信息来判断是否产生冲突，以确定是否将更新 merge 到当前分支。  

4、git pull 用法
-------------

前面提到，`git pull` 的过程可以理解为：

```
git fetch origin master //从远程主机的master分支拉取最新内容 
git merge FETCH_HEAD    //将拉取下来的最新内容合并到当前所在的分支中复制代码

```

*   1
*   2

即将远程主机的某个分支的更新取回，并与本地指定的分支合并，完整格式可表示为：

```
$ git pull <远程主机名> <远程分支名>:<本地分支名>复制代码

```

*   1

如果远程分支是与当前分支合并，则冒号后面的部分可以省略：

```
$ git pull origin next复制代码

```