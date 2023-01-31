# Git版本控制

*❓由一个很神奇的问题展开，git是什么，它仅仅是一个工具嘛？*

看完这一篇，你可以说你精通 git 啦！！！

> Git 是一个版本控制系统。
>
> Git 可帮助您跟踪代码更改。
>
> Git 用于在代码上进行协作。
>
> Git 思想可以带入你的工程(docker | hub | ipfs | ……所有设计版本机制)

**在这里面，可以掌握git的基本操作，基本可以解决百分之九十的开发问题，而剩下的百分之十，对应的最新高级篇可以帮你。**

**又或许，我们希望掌握它的原理，或许我们可以从头构建一个git，不是嘛🎉**

## 建立你自己的`Git`

- [**Haskell**：在 Haskell 中自下而上重新实现 “git clone”](http://stefan.saasen.me/articles/git-clone-in-haskell-from-the-bottom-up/)
- [**JavaScript** : 小程序](http://gitlet.maryrosecook.com/docs/gitlet.html)
- [**JavaScript** : 制造 GIT - 学习 GIT](https://kushagra.dev/blog/build-git-learn-git/)
- [**Python** : 一个 Git 客户端就其创建、提交并转发到 GitHub 上](https://benhoyt.com/writings/pygit/)
- [**Python**：给自己写一个 Git！](https://wyag.thb.lt/)
- [**Ruby** : 在 Ruby 中重建 Git](https://robots.thoughtbot.com/rebuilding-git-in-ruby)





## 实现git

> 为了实施`git clone`，将涵盖以下领域：
>
> - **git 协议**用于使git 客户端能够使用各种传输机制（本机 git 协议、ssh、http）从远程 git 服务器检索所需的更改集，
> - **包文件**格式，用于将最少量的数据从服务器传输到客户端（更普遍地用于有效地将打包对象存储在存储库中），
> - git 用于以 blob 形式存储提交、树、标签和实际文件内容的底层**对象存储**
> - 以及用于跟踪工作目录中文件更改的**索引格式。**

+ [ ] 如何实现`clone`





## 最新 – 进阶

> git基础篇会解决我们日常的百分之九十的问题，但是总是有那么一些问题需要我们注意的。比如说某一个，我开发的项目的某一个模块感觉这个模块没有之前的好了，那么我想切换回以前的模块。这个时候我们可以用`git log 指定项目目录` 得到以前的提交记录，然后`git switch 想要的版本hash \指定的目录` 最后就是`git commit -m "checkout ...."` 。

> 2022年9月10日 17:47:28
>
> **进阶篇存在的目的或许不仅仅是git语法层面的，就比如说我们需要删除一个分支，可能很简单的两个命令就可以解决**
>
> ```bash
> # 用两行命令删除分支
> ## 删除本地分支
> git branch -d localBranchName
> 
> ## 删除远程分支
> git push origin --delete remoteBranchName
> ```
>
> **但是我们很疑惑它们的用法**🗒️❌
>
> **1. 什么时候需要删除分支**
>
> 一个 Git 仓库常常有不同的分支，开发者可以在各个分支处理不同的特性，或者在不影响主代码库的情况下修复 bug。
>
> 仓库常常有一个 `master` 分支，表示主代码库。开发人员创建其他分支，处理不同的特性。
>
> 开发人员完成处理一个特性之后，常常会删除相应的分支。
>
> **2. 本地删除分支**
>
> 如果你还在一个分支上，那么 Git 是不允许你删除这个分支的。所以，请记得退出分支：`git checkout master`。
>
> 通过 `git branch -d <branch>`删除一个分支，比如：`git branch -d fix/authentication`。
>
> 当一个分支被推送并合并到远程分支后，`-d` 才会本地删除该分支。如果一个分支还没有被推送或者合并，那么可以使用`-D`强制删除它。
>
> 这就是本地删除分支的方法。
>
> **3. 删除远程分支**
>
> 使用这个命令可以远程删除分支：`git push <remote> --delete <branch>`。
>
> 比如： `git push origin --delete fix/authentication`，这个分支就被远程删除了。
>
> 你也可以使用这行简短的命令来远程删除分支：`git push <remote> :<branch>`，比如：`git push origin :fix/authentication`。
>
> 如果你得到以下错误消息，可能是因为其他人已经删除了这个分支。
>
> ```bash
> error: unable to push to unqualified destination: remoteBranchName The destination refspec neither matches an existing ref on the remote nor begins with refs/, and we are unable to guess a prefix based on the source ref. error: failed to push some refs to 'git@repository_name'
> ```
>
> 使用以下命令同步分支列表：
>
> ```
> git fetch -p
> ```
>
> `-p` 的意思是“精简”。这样，你的分支列表里就不会显示已远程被删除的分支了。

+ [x] [😎🎉Git高级部分](./markdown/super.md)
+ [x] [😎🎉移动提交记录 — 自由修改提交树](./markdown/move.md)
+ [x] [😎🎉Git提交技巧](./markdown/commit.md)
+ [x] [😎🎉Git — tags](./markdown/tags.md)
+ [x] [😎🎉Git — 多次rebase](./markdown/rebase.md)

+ [x] [😎🎉Git — module](./markdown/module.md)

+ [x] [😎🎉Git — Script自动推送脚本](./markdown/git-script.md)

> `git - module`不仅仅很有用，而且里面的实现也值得学习
>
> `git - Script`脚本能让我们更快的、更方便推动项目



## github教程

+ [x] [基于 Github Action 的 CI/CD 流程](https://nsddd.top/archives/actions)
+ [x] [保持自己github的forks自动和上游仓库同步并推送到 gitee](http://nsddd.top/archives/git-fork)



## 基础部分

> 有时候我们需要撤销上一次的`add`或者`commit`怎么办?
>
> + 我们使用`git reset HEAD` 这样只是撤销了上一次`add`
> + 我们使用：`git reset --soft HEAD^` 这样就成功撤销了`commit`。
> + 使用`git reset --hard HEAD^` 这样连`add`也撤销了。
>
> 即使是一个简单的推送，或许我们并不赞成强行推送，但是实际确是：我们总是需要去强行推送某一个分支，就比如用npm build搭建出网页推送上去，每一次打包都需要覆盖之前的操作：
>
> ```bash
> git push --force origin master
> ```

+ [x] 🗝️ [1. github基础命令大全](./markdown/github基础知识.md)

+ [x] 🗝️ [2. git和svn对比](./markdown/git和svn对比.md)

+ [x] 🗝️ [3. git工作原理](./markdown/git工作原理.md)

+ [x] 🗝️ [4. git分支](./markdown/git分支.md)

+ [x] 🗝️ [5. git上传](./markdown/git上传.md)

+ [x] 🗝️ [6. git（菜鸟）](./markdown/菜鸟git.md)

+ [x] 🗝️ [7. git分支与合并](./markdown/git分支与合并.md)

+ [x] 🗝️ [8. git版本回退](./markdown/git版本回退.md)

+ [x] 🗝️  [9. git比较两个分支差异](./markdown/git比较两个分支差异.md)

+ [x] 🗝️ [10. Git忽略和 .gitignore](./markdown/Git忽略和gitignore.md)

+ [x] 🗝️ [11. 提交到多个远程仓库](./markdown/git-adds.md)

+ [x] 🗝️ [12. 贡献代码](./markdown/git-contributor.md)



## 🔥 开源社区实习经历篇

> 记录下参与开源社区的一些记录

+ [x] [记录](https://nsddd.top/archives/sealos-one)

> 统一格式：
>
> ```bash
> 统一格式：git commit -m 'type(scope): 描述(#issue)'
> ```

我们在提交的时候带上邮箱信息：`-s`

```
git commit -s -m "..."
```

> `git commit`艺术：
>
> ```bash
> <类型>[可选 范围]: <描述>
> [可选 正文]
> [可选 脚注]
> ```
>
> `git commit`提交类型可以是如下：
>
> 1. `feat`：新功能（feature）
> 2. `fix`：修补bug
> 3. `docs`：文档（documentation）
> 4. `style`： 格式（不影响代码运行的变动）
> 5. `refactor`：重构（即不是新增功能，也不是修改bug的代码变动）
> 6. `test`：增加测试
> 7. `chore`：构建过程或辅助工具的变动
> 8. `perf`：性能优化
> 9. `revert`：回滚
> 10. `build`：构建
> 11. `ci`：持续集成
> 12. `update`：更新
> 13. `add`：添加
> 14. `delete`：删除
> 15. `init`：初始化
> 16. `merge`：合并
> 17. `move`：移动
> 18. `rename`：重命名
> 19. `sync`：同步
> 20. `release`：发布
> 21. `hotfix`：修复线上问题
> 22. `optimize`：优化



我们或许可以基于远程`origin`分支创建一个新的分支`bug-xiongxinwei`

```
git checkout -b bug-xiongxinwei origin 
```



如果你有多次提交

```bash
git rebase -i	<commit-id>  # 如果你的pr有多次提交
```



合并：想要把master分支合并到feature分支

```
git checkout feature
git rebase master

//这两条命令等价于git rebase master feature
```



`origin`“和”`bug-xiongxinwei`“这两个分支各自  前进  了

在这里，你可以用”`pull`“命令把”`origin`“分支上的修改拉下来并且和你的修改合并； 结果看起来就像一个新的”合并的提交”(merge commit)

但是，如果你想让”`mywork`“分支历史看起来像没有经过任何合并一样，也可以用 `git rebase`，如下所示:

```shell
git checkout mywork
git rebase origin
```

这些命令会把你的”`mywork`“分支里的每个提交(commit)取消掉，并且把它们临时 保存为补丁(patch)(这些补丁放到”`.git/rebase`“目录中)，然后把”`mywork`“分支更新 到最新的”`origin`“分支，最后把保存的这些补丁应用到”`mywork`“分支上。



## 我的工作流

在经历几年的 git 使用后，我形成了自己的 git 工作流，配合 GitHub CLI 的使用，[如果你想了解 Github CLI，这篇文章可以帮助到你](https://nsddd.top/archives/gh)

最开始使用的是 **中心式协同流**，下面两个仓库陪伴着我使用最久，也是学习仓库，或许对你们有帮助可以 star：

+ [cubxxw/CS-NativeCloud-Blockchain-awesome](https://github.com/cubxxw/CS-NativeCloud-Blockchain-awesome) - 📚 菜鸟成长手册🚀 CS系列 、云原生系列、区块链系列、web3系列🔥、Golang系列💡...... (2 days ago)
+ [cubxxw/awesome-cs-course](https://github.com/cubxxw/awesome-cs-course) - 📚awesome：Linux、csapp、os、leetcode、web、html、css、JavaScript、git、java、python、C/C++、mysql、mongodb、golang、blockchain、markdown (6 days ago)

这个过程一般是下面这个样子的：

1. 从服务器上做git pull origin master把代码同步下来
2. 改完后，git commit到本地仓库中
3. 然后git push origin master到远程仓库中，这样其他同学就可以得到你的代码了

如果在第 3 步发现 push 失败，因为别人已经提交了，那么你需要先把服务器上的代码给 pull 下来，为了避免有 merge 动作，你可以使用 git pull --rebase 。这样就可以把服务器上的提交直接合并到你的代码中：

+ 先把你本地提交的代码放到一边
+ 然后把服务器上的改动下载下来
+ 然后在本地把你之前的改动再重新一个一个地做 commit，直到全部成功

如果有冲突，那么你要先解决冲突，然后做 git rebase --continue

⚠️ 适用于小项目，后面我尝试使用 **功能分支协同流**：



### 功能分支

引入“功能分支”。这个协同工作流的开发过程如下：

1. 首先使用 git checkout -b new-feature 创建 “new-feature”分支
2. 然后共同开发这个功能的程序员就在这个分支上工作，进行 add、commit 等操作
3. 然后通过 git push -u origin new-feature 把分支代码 push 到服务器上
4. 其他程序员可以通过git pull --rebase来拿到最新的这个分支的代码
5. 最后通过 Pull Request 的方式做完 Code Review 后合并到 Master 分支上

![image-20230131112956776](http://sm.nsddd.top/sm202301311129919.png)



但是在生产环境中，这样的开发也是没有办法满足需求的，所以我们出现了一个 GitFlow 协同工作流，但是由于 git flow 太复杂，很乱，所以后面又有了 GitHub Flow 。

> 其中有个问题就是因为分支太多，所以会出现 git log 混乱的局面。具体来说，主要是 git-flow 使用git merge --no-ff来合并分支，在 git-flow 这样多个分支的环境下会让你的分支管理的 log 变得很难看。

⚠️ 因此，最终我选择的工作流方式是 GitHub Flow ，因为不了解  GitLab，所以对于 GitLab 工作流不做评价。



### GitHub Flow

所谓 GitHub Flow，其实也叫 Forking flow，也就是 GitHub 上的那个开发方式。

1. 每个开发人员都把 “官方库” 的代码 fork 到自己的代码仓库中。
2. 然后，开发人员在自己的代码仓库中做开发，想干啥干啥。
3. 因此，开发人员的代码库中，需要配两个远程仓库，一个是自己的库，一个是官方库（用户的库用于提交代码改动，官方库用于同步代码）。
4. 然后在本地建 “功能分支”，在这个分支上做代码开发。
5. 这个功能分支被 push 到开发人员自己的代码仓库中。
6. 然后，向 “官方库” 发起 pull request，并做 Code Review。

这就是 GitHub 的工作流程。

如果你有 “官方库” 的权限，那么就可以直接在 “官方库” 中建功能分支开发，然后提交 pull request。通过 Code Review 后，合并进 Master 分支，而 Master 一旦有代码被合并就可以马上 release。

这是一种非常 Geek 的玩法。这需要一个自动化的 CI/CD 工具做辅助。是的，CI/CD 应该是开发中的标配了。

[关于 CI/CD action 可以看这篇文章](https://nsddd.top/archives/actions)，当然，所有笔记在上面的仓库中都有介绍，有心人自然能找到。



### 找到适合你的

我们知道软件开发的趋势一定是下面这个样子的：

+ 以微服务或是 SOA 为架构的方式。一个大型软件会被拆分成若干个服务，那么，我们的代码应该也会跟着服务拆解成若干个代码仓库。这样一来，我们的每个代码仓库都会变小，于是我们的协同工作流程就会变简单。

  对于每个服务的代码仓库，我们的开发和迭代速度也会变得很快，开发团队也会跟服务一样被拆分成多个小团队。这样一来， GitFlow 这种协同工作流程就非常重了，而 GitHub 这种方式或是功能分支这种方式会更适合我们的开发。

+ 以 DevOps 为主的开发流程。DevOps 关注于 CI/CD，需要我们有自动化的集成测试和持续部署的工具。这样一来，我们的代码发布速度就会大大加快，每一次提交都能很快地被完整地集成测试，并很快地发布到生产线上。

我在想，是否有一种工作流，可以面对我们现实工作中的各种情况。但是，我想这个世界太复杂了，应该不存在一种一招鲜吃遍天的放之四海皆准的银弹方案。所以，我们还要根据自己的实际情况来挑选适合我们的协同工作的方式。
