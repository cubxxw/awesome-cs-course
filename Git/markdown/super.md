# git高级部分

[toc]

---

+ [auther😎](http://nsddd.top)

在接触 Git 更高级功能之前，我们有必要先学习在你项目的提交树上前后移动的几种方法。

一旦熟悉了如何在 Git 提交树上移动，你驾驭其它命令的能力也将水涨船高！



## head

我们首先看一下 “HEAD”。 HEAD 是一个对当前检出记录的符号引用

> 也就是指向你正在其基础上进行工作的提交记录。

HEAD 总是指向当前分支上最近一次提交记录。大多数修改提交树的 Git 命令都是从改变 HEAD 的指向开始的。

HEAD 通常情况下是指向分支名的（如 bugFix）。在你提交时，改变了 bugFix 的状态，这一变化通过 HEAD 变得可见。



### head变化

下面咱们通过实际操作看一下。我们将会观察提交前后 HEAD 的位置。

```
git checkout C1; 
git checkout main; 
git commit; 
git checkout C2
```

<img src="https://sm.nsddd.top//typora/image-20220910175135756.png?mail:3293172751@qq.com" alt="image-20220910175135756" style="zoom: 25%;" />

看到了吗？ HEAD 指向了 `main`，随着提交向前移动。

>  实际这些命令并不是真的在查看 HEAD 指向，看下一屏就了解了。如果想看 HEAD 指向，可以通过 `cat .git/HEAD` 查看， 如果 HEAD 指向的是一个引用，还可以用 `git symbolic-ref HEAD` 查看它的指向。但是该程序不支持这两个命令



### 分离的 HEAD

**分离的 HEAD 就是让其指向了某个具体的提交记录而不是分支名。**在命令执行之前的状态如下所示：

```
HEAD -> main -> C1
```

HEAD 指向 main， main 指向 C1

```
git checkout C1
```

<img src="https://sm.nsddd.top//typora/image-20220910175310252.png?mail:3293172751@qq.com" alt="image-20220910175310252" style="zoom:33%;" />

现在变成了

```
HEAD -> C1
```





## 相对引用

- 通过指定提交记录哈希值的方式在 Git 中移动不太方便。在实际应用时，并没有像本程序中这么漂亮的可视化提交树供你参考，所以你就不得不用 `git log` 来查查看提交记录的哈希值。
- 并且哈希值在真实的 Git 世界中也会更长（译者注：基于 SHA-1，共 40 位）。例如前一关的介绍中的提交记录的哈希值可能是 `fed2da64c0efc5293610bdd892f82a58e8cbc5d8`。舌头都快打结了吧...
- 比较令人欣慰的是，Git 对哈希的处理很智能。你只需要提供能够唯一标识提交记录的前几个字符即可。因此我可以仅输入`fed2` （前四位）而不是上面的一长串字符。



正如我前面所说，通过哈希值指定提交记录很不方便，所以 Git 引入了相对引用。这个就很厉害了!

使用相对引用的话，你就可以从一个易于记忆的地方（比如 `bugFix` 分支或 `HEAD`）开始计算。

相对引用非常给力，这里我介绍两个简单的用法：

- 使用 `^` 向上移动 1 个提交记录
- 使用 `~<num>` 向上移动多个提交记录，如 `~3`



###  Git示范

首先看看操作符 (^)。把这个符号加在引用名称的后面，表示让 Git 寻找指定提交记录的父提交。

所以 `main^` 相当于“`main` 的父节点”。

`main^^` 是 `main` 的第二个父节点

现在咱们切换到 main 的父节点

```
git checkout main^
```

<img src="https://sm.nsddd.top//typora/image-20220910183748634.png?mail:3293172751@qq.com" alt="image-20220910183748634" style="zoom:33%;" />



你也可以将 `HEAD` 作为相对引用的参照。下面咱们就用 `HEAD` 在提交树中向上移动几次。

```
git checkout C3
git checkout HEAD^
git checkout HEAD^
git checkout HEAD^
```

<img src="https://sm.nsddd.top//typora/image-20220910184002789.png?mail:3293172751@qq.com" alt="image-20220910184002789" style="zoom:33%;" />



### ~运算符

如果你想在提交树中向上移动很多步的话，敲那么多 `^` 貌似也挺烦人的，Git 当然也考虑到了这一点，于是又引入了操作符 `~`。

该操作符后面可以跟一个数字（可选，不跟数字时与 `^` 相同，向上移动一次），指定向上移动多少次。咱们还是通过实际操作看一下吧



咱们用 `~<num>` 一次后退四步。

```
git checkout HEAD~4
```

<img src="https://sm.nsddd.top//typora/image-20220910190700794.png?mail:3293172751@qq.com" alt="image-20220910190700794" style="zoom:33%;" />

### 强制修改分支位置

你现在是相对引用的专家了，现在用它来做点实际事情。

我使用相对引用最多的就是移动分支。**可以直接使用 `-f` 选项让分支指向另一个提交。**例如:

```
git branch -f main HEAD~3
```

上面的命令会将 main 分支强现在咱们来演示一下刚才的命令：

```
git branch -f main HEAD~3
```

<img src="https://sm.nsddd.top//typora/image-20220910190934335.png?mail:3293172751@qq.com" alt="image-20220910190934335" style="zoom:33%;" />

这就对了! 相对引用为我们提供了一种简洁的引用提交记录 `C1` 的方式， 而 `-f` 则容许我们将分支强制移动到那个位置。制指向 HEAD 的第 3 级父提交。



## 撤销变更

在 Git 里撤销变更的方法很多。和提交一样，撤销变更由底层部分（暂存区的独立文件或者片段）和上层部分（变更到底是通过哪种方式被撤销的）组成。我们这个应用主要关注的是后者。

主要有两种方法用来撤销变更 —— 一是 `git reset`，还有就是 `git revert`。接下来咱们逐个进行讲解。



## Git Reset

`git reset` 通过把分支记录回退几个提交记录来实现撤销改动。你可以将这想象成“改写历史”。`git reset` 向上移动分支，**原来指向的提交记录就跟从来没有提交过一样。**

让我们来看看演示：

```
git reset HEAD~1
```

<img src="https://sm.nsddd.top//typora/image-20220910192126639.png?mail:3293172751@qq.com" alt="image-20220910192126639" style="zoom:33%;" />

漂亮! Git 把 main 分支移回到 `C1`；现在我们的本地代码库根本就不知道有 `C2` 这个提交了。



## Git Revert

虽然在你的本地分支中使用 `git reset` 很方便，但是这种“改写历史”的方法对大家一起使用的远程分支是无效的哦！

为了撤销更改并**分享**给别人，我们需要使用 `git revert`。来看演示：

```
git revert HEAD
```

<img src="https://sm.nsddd.top//typora/image-20220910192333941.png?mail:3293172751@qq.com" alt="image-20220910192333941" style="zoom:33%;" />

奇怪！在我们要撤销的提交记录后面居然多了一个新提交！这是因为新提交记录 `C2'` 引入了**更改** —— 这些更改刚好是用来撤销 `C2` 这个提交的。也就是说 `C2'` 的状态与 `C1` 是相同的。

revert 之后就可以把你的更改推送到远程仓库与别人分享啦。

> ![image-20220910192635828](https://sm.nsddd.top//typora/image-20220910192635828.png?mail:3293172751@qq.com)
>
> ```
> $ git reset HEAD^
> $ git checkout pushed
> $ git revert HEAD
> ```

