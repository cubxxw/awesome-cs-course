# vim同步

[toc]

## 前言

> Vim是一个强大的文本编辑器。良好的配置更能便利对Vim的使用。有时候，我们会在几台不同的电脑上使用Vim. 例如，我们可能在自己的电脑和公司的电脑上都安装了Vim. 有时候，我们需要实现，如果我们配置好了其中一个Vim环境，就能轻松的把这些配置很容易的复制到另外一台机器，甚至于我们更新其中一台机器甚至几台机器的配置之后，可以很容易的将其同步到其他机器。好在现在有较多的云存储的选择。利用它们，加上一个版本控制软件，我们就可以很容易的做到这一点。

**我使用的一个是自己的云盘系统，你们也可以选择这个（只需要邮箱验证注册就好了）或者选择其他网盘**

+ [x] [云盘地址](http://xxw.nsddd.top)



## 开始

在每台机器上，我们需要如下的目录结构

+ vim文件夹：指安装vim的目录。

+ 云盘文件夹：指在个人云盘中指定的本机的同步目录。



如果没有配置过git, 运行git bash, 如下配置git. 同样，对于云同步盘，也要指定本地同步目录。

```bash
　　git config --global user.name "your name"   #  --global 全局
　　git config --global user.email "your email"
```



在任一台机器上，执行如下操作，创建vim配置文件的仓库

1. 运行git bash, 执行如下命令，创建云同步盘中的目录

```bash
　　　　cd 云盘文件夹  （例如，/c/YunDisk/GitRepositories/）
　　　　mkdir vim
　　　　cd vim
　　　　git init --bare
```

　2. 以管理员权限运行git bash, 执行如下命令，将vim配置文件加入版本控制

```bash
　　　　cd vim文件夹 (例如，“/c/Program Files (x86)/Vim")
　　　　git init
　　　　git add _vimrc
　　　　git commit -m "first version."
　　　　git remote add origin 云盘文件夹  （例如，/c/YunDisk/GitRepositories/）
　　　　git push -u origin master
```

至此，我们已经将本地的vim配置文件加入了版本控制，并且同步到了云同步盘中。



在其他机器上，执行如下操作，将云同步盘中的vim配置文件同步到vim文件夹中

以管理员权限运行git bash, 执行如下命令

```bash
　　　　cd vim文件夹 (例如，“/c/Program Files (x86)/Vim")
　　　　git clone 云盘文件夹  （例如，/c/YunDisk/GitRepositories/）
　　　　git remote add origin 云盘文件夹  （例如，/c/YunDisk/GitRepositories/）
　　　　git pull origin master
```

到现在为止，所有机器上的vim配置文件都加入到了版本控制中，并且都进行了同步



当修改了任意一台机器上的vim配置，进行如下操作将其同步到云同步盘

以管理员权限运行`git bash`, 执行如下命令，将vim配置文件加入版本控制

```bash
　　　　cd vim文件夹 (例如，“/c/Program Files (x86)/Vim")
　　　　git add _vimrc
　　　　git commit -m "your comments."
　　　　git pull origin master   // 先同步其他机器的更新到本机，如果有冲突，要处理冲突
　　　　git push origin master  //提交本机的更新
```



对于其他机器，执行如下操作来同步这些更新

以管理员权限运行git bash, 执行如下命令，将vim配置文件加入版本控制

```bash
　　　　cd vim文件夹 (例如，“/c/Program Files (x86)/Vim")
　　　　git pull origin master
```


至此，我们就可以在自己的所有机器上同步vim配置文件了。