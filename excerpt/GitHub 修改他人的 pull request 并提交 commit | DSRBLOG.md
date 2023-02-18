> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.dsrkafuu.net](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/)

> 使用一种更简单的方案修改其他社区开发者提交的 pull request。

GitHub 修改他人的 pull request 并提交 commit
------------------------------------

有时我们需要对社区开发者向我们的项目提交的 pull request 进行一些修改，例如一些配置文件以及资源文件的补充等。这种情况下我们可以直接回复 request 提出修改的要求，但除此之外，我们也可以使用一种更简单的方案：直接向 pull request 提交 commit。

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20201001170900.webp)

在下文的实现例子中，仓库维护者 (我) 的用户名是 `amzrk2`，提交 request 的开发者用户名是 `Hyask`。示例的 pull request 修改了文件，并且直接从对方的 `master` 分支推送到本仓库 [hugo-theme-fuji](https://github.com/dsrkafuu/hugo-theme-fuji) 的 `master` 分支。

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928172908.webp)

以下操作基于本地已经有 clone 完成的远程仓库文件的假设，从上面的图可以看出，GitHub 提示我们可以直接提交内容到 request 发起者的仓库，以下是全过程的步骤。

添加远程仓库
------

首先需要添加 pull request 提出者的仓库至本地，作为另一个远程仓库：

```
git remote add Hyask git@github.com:Hyask/hugo-theme-fuji.git


```

添加完成后尝试使用 `git remote -v`，即可看到其他远程仓库：

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928172829.webp)

同步仓库并切换分支
---------

添加完成后，同步新的远程仓库内容：

```
git fetch Hyask


```

同步完成之后，需要切换到对方发起 pull request 的来源分支，也就是我们希望提交新 commit 的分支：

```
git checkout -b Hyask-master Hyask/master


```

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928173043.webp)

这样就完成了切换，并且本地的 `Hyask-master` 分支对应了对方发起 pull request 的 `Hyask/master` 分支。

修改文件并提交
-------

切换到 `Hyask-master` 分支后就可以进行文件的修改了，修改完成后提交 commit 的流程均与平时一致：

```
git commit -m "fix: something"


```

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928173259.webp)

确定修改已经完成并提交了 commit 之后，推送到对方的仓库即可。注意这里的推送目标分支，git 可能会提示分支名不一致，按提示推送即可：

```
git push Hyask HEAD:master


```

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928173359.webp)

推送完成之后，应该就已经能在 GitHub 对应 pull request 的页面上看到刚刚做出的修改了。

![](https://blog.dsrkafuu.net/post/2020/github-add-commit-to-pull-request/20200928173437.webp)

可能出现的问题
-------

如果在推送至对方仓库时，提示 remote rejected，原因是 permission denied，则可能是因为对方并没有在他的 pull request 页面上勾选 “Allow edits from maintainers” 的选项。在这种情况下就只能提出修改意见，或是提出能否开启该选项的请求了。