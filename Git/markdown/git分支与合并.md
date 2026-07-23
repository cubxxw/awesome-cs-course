[toc]

---

[😶‍🌫️需要联系我联系我🖱️](xxw@cubxxw.com)

[😶‍🌫️我的学习笔记(Github)](https://github.com/3293172751/CS_COURSE)

❤️💕💕 **[我的博客](https://cubxxw.com)**

---

## 大致流程

> 我们在开发中，一般有新的框架，都是通过重新创建一个新的分支，然后在这个新的分支上修改文件，最后回到主分支，然后合并分支，分支的合并有两种方法

### 方法一：merge方式

![image-20220907145123296](https://sm.cubxxw.com//typora/image-20220907145123296.png?mail:3293172751@qq.com)



### 方法二：Rebase

第二种合并分支的方法是 `git rebase`。Rebase 实际上就是取出一系列的提交记录，“复制”它们，然后在另外一个地方逐个的放下去。

Rebase 的优势就是可以创造更线性的提交历史，这听上去有些难以理解。如果只允许使用 Rebase 的话，代码库的提交历史将会变得异常清晰。

1. 还是准备了两个分支；注意当前所在的分支是 bugFix（星号标识的是当前分支）

   我们想要把 bugFix 分支里的工作直接移到 main 分支上。移动以后会使得两个分支的功能看起来像是按顺序开发，但实际上它们是并行开发的。

   咱们这次用 `git rebase` 实现此目标

   <img src="https://sm.cubxxw.com//typora/image-20220907145359017.png?mail:3293172751@qq.com" alt="image-20220907145359017" style="zoom:33%;" />

   ```
   git rebase main
   ```

   

2. 现在 bugFix 分支上的工作在 main 的最顶端，同时我们也得到了一个更线性的提交序列。

   <img src="https://sm.cubxxw.com//typora/image-20220907145459343.png?mail:3293172751@qq.com" alt="image-20220907145459343" style="zoom:33%;" />

   ```
   git chekout bugFix #或者git switch bugFix
   git rebose bugFix
   ```

> 总结步骤
>
> - 新建并切换到 `bugFix` 分支
> - 提交一次
> - 切换回 main 分支再提交一次
> - 再次切换到 bugFix 分支，rebase 到 main 上



## 合并分支

**我们已经准备好紧急修复，所以让我们合并主分支和紧急修复分支。**

首先，我们需要切换到 master 分支：

```
git checkout master
Switched to branch 'master'
```

现在我们将当前分支（master）与紧急修复合并：

```
git merge emergency-fix
Updating 09f4acd..dfa79db
Fast-forward
 index.html | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
```

**由于紧急修复分支直接来自 master，并且在我们工作时没有对 master 进行其他更改，Git 将其视为 master 的延续。所以它可以“快进”，只需将 master 和紧急修复都指向同一个提交。**

由于master和emergency-fix现在基本相同，我们可以删除emergency-fix，因为它不再需要：

```
git branch -d emergency-fix
Deleted branch emergency-fix (was dfa79db).
```



## 合并冲突

现在我们可以转到 hello-world-images 并继续工作。添加另一个图像文件 (img_hello_git.jpg) 并更改 index.html，使其显示：

```
git checkout hello-world-images
Switched to branch 'hello-world-images'
```



**例如：**

```html
<!DOCTYPE html>
<html>
<head>
<title>Hello World!</title>
<link rel="stylesheet" href="bluestyle.css">
</head>
<body>

<h1>Hello world!</h1>
<div><img src="img_hello_world.jpg" alt="Hello World from Space" style="width:100%;max-width:960px"></div>
<p>This is the first file in my new Git Repo.</p>
<p>A new line in our file!</p>
<div><img src="img_hello_git.jpg" alt="Hello Git" style="width:100%;max-width:640px"></div>

</body>
</html>
```

现在，我们完成了这里的工作，可以为这个分支暂存和提交：

```
git add --all
git commit -m "added new image"
[hello-world-images 1f1584e] added new image
 2 files changed, 1 insertion(+)
 create mode 100644 img_hello_git.jpg
```

我们看到 index.html 在两个分支中都已更改。现在我们准备好将 hello-world-images 合并到 master 中。但是我们最近在 master 中所做的更改会发生什么？

```
git checkout master
git merge hello-world-images
Auto-merging index.html
CONFLICT (content): Merge conflict in index.html
Automatic merge failed; fix conflicts and then commit the result.
```



合并失败，因为 index.html 的版本之间存在冲突。让我们检查一下状态：

```
git status
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Changes to be committed:
        new file:   img_hello_git.jpg
        new file:   img_hello_world.jpg

Unmerged paths:
  (use "git add ..." to mark resolution)
        both modified:   index.html
```



这确认 index.html 中存在冲突，但图像文件已准备好并准备提交。

所以我们需要解决这个冲突。在我们的编辑器中打开文件：

```
<!DOCTYPE html>
<html>
<head>
<title>Hello World!</title>
<link rel="stylesheet" href="bluestyle.css">
</head>
<body>

<h1>Hello world!</h1>
<div><img src="img_hello_world.jpg" alt="Hello World from Space" style="width:100%;max-width:960px"></div>
<p>This is the first file in my new Git Repo.</p>
<<<<<<< HEAD
<p>This line is here to show how merging works.</p>
=======
<p>A new line in our file!</p>
<div><img src="img_hello_git.jpg" alt="Hello Git" style="width:100%;max-width:640px"></div>
>>>>>>> hello-world-images

</body>
</html>
```





## 解决冲突

1.查看远程仓库:

```xml
git remote -v
```



2.比如，在步骤一中，我们查看到远程有一个叫origin的仓库，我们可以使用如下命令从origin远程仓库获取最新版本的代码。

```xml
git fetch origin master:temp1
```

上面代码的意思是：从远程的origin仓库的master分支下载到本地，并新建一个temp分支。

3.查看temp分支与本地原有分支的不同

```xml
git diff temp1
```

4.将temp分支和本地分支合并

```xml
git merge temp1
```

现在，B的本地代码已经和远程仓库处于同一个版本了，接下来去代码中消除冲突，并提交新版本到远程代码库。

5.上面的步骤中我们创建了temp分支，如果想要删除temp分支，也是可以的，命令如下：

```xml
git branch -d temp
```





**全步骤： 首先通过从远程的origin的master主分支下载最新的版本号到本地origin/master分支上，然后比較差别，最后合并到当前分支；**

```shell
[root@~/wade/nodejs/express-routing]# git fetch git@github.com:xiongxinwei/express-routing.git master
From github.com:xiongxinwei/express-routing
 * branch            master     -> FETCH_HEAD
[root@~/wade/nodejs/express-routing]# git log 
commit c7f2ae18cd3648d32fa3dcd50060fa7f13a093b1
Author: xiongxinwei <xiongxinwei@gmail.com>
Date:   Fri Nov 21 01:41:40 2014 +0800
 
    express-routing test
 
commit dd5923229abe4e1da706ce53ad4b41154cb89ea7
Author: xiongxinwei <xiongxinwei@gmail.com>
Date:   Fri Nov 21 01:07:03 2014 +0800
 
    Merge branch 'master', remote branch 'origin'
 
commit d1aee713ca98e5cdead1978dcfcf7a55974a2851
Author: xiongxinwei <3293172751nss@gmail.com>
Date:   Fri Nov 21 01:05:12 2014 +0800
 
    Create README.md
 
commit 5aa66ffc06b95aeb8ef42627fe4b97c95cb660b9
Author: xiongxinwei <3293172751nss@gmail.com>
Date:   Fri Nov 21 01:03:05 2014 +0800
 
    Initial commit
[root@~/wade/nodejs/express-routing]# git merge origin/master
Updating c7f2ae1..8d3058b
Fast-forward
 README.md |    2 ++
 1 files changed, 2 insertions(+), 0 deletions(-)
```