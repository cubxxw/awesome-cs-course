> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/liyongbing1122/article/details/79204479)

Linux 创建用户，并赋予管理指定目录的权限  
一、创建用户  
1. 创建用户：  
useradd 用户名  
2. 设置密码：  
passwd userName 随后会提示输入密码  
二、赋予权限  
Linux 给用户和文件赋予权限  
1、先对用户所属的的组赋权限：   
chown -R 用户名: 用户组  目录   
2、再对目录赋予权限：   
[chmod](https://so.csdn.net/so/search?q=chmod&spm=1001.2101.3001.7020) -R 775  目录  
3、查看文件夹的权限  
ls -la 目录  
创建用户时，会自动创建一个与用户名同名的用户组。  
===================================================

   创建快捷方式    ln -s  源  目的地  

**********************************************

添加组 groupadd groupName

查看当前登录用户 whoami

查看当前用户所在组 groups

查看指定用户所在组 groups userName

将用户添加到组 usermod -a -G groupName userName

从组中删除用户  gpasswd groupName -d userName

查看所有用户 cat /etc/passwd

查看指定用户 cat /etc/passwd|grep userName

查看所有组 cat /etc/group

查看指定组 cat /etc/group|grep groupName

删除组：groupdel groupName

删除用户 userdel userName

彻底删除用户：userdel -rf userName

```
权限  　数字     计算
---     0   　0 + 0 + 0
r--     4   　4 + 0 + 0
-w-     2   　0 + 2 + 0
--x     1   　0 + 0 + 1
rw-     6   　4 + 2 + 0
-wx     3   　0 + 2 + 1
r-x     5   　4 + 0 + 1
rwx     7   　4 + 2 + 1
 
r：读  w：写  x：执行
```

三组访问权限：所有者的权限，群组用户的权限，其他用户的权限

例如：640 分别表示：

文件的所有者有读和写的权限。  
文件所在群组的其他用户具有读的权限。  
除此之外的其他用户没有任何权限。

**rwx 权限对文件的作用**

<table><caption>rwx 权限对文件的作用</caption><tbody><tr><th>rwx 权限</th><th>对文件的作用</th></tr><tr><td>&nbsp; 读权限（r）[4]</td><td>表示可读取此文件中的实际内容，例如，可以对文件执行 cat、more、less、head、tail 等文件查看命令。</td></tr><tr><td>&nbsp; 写权限（w）[2]</td><td>表示可以编辑、新增或者修改文件中的内容，例如，可以对文件执行 vim、echo 等修改文件数据的命令。注意，无权限不赋予用户删除文件的权利，除非用户对文件的上级目录拥有写权限才可以。</td></tr><tr><td>执行权限（x）[1]</td><td>表示该文件具有被系统执行的权限。Window 系统中查看一个文件是否为可执行文件，是通过扩展名（.exe、.bat 等），但在&nbsp;<a href="http://c.biancheng.net/linux_tutorial/">Linux</a>&nbsp;系统中，文件是否能被执行，是通过看此文件是否具有 x 权限来决定的。也就是说，只要文件拥有 x 权限，则此文件就是可执行文件。但是，文件到底能够正确运行，还要看文件中的代码是否正确。</td></tr></tbody></table>

  
对于文件来说，执行权限是最高权限。给用户或群组设定权限时，是否赋予执行权限需要慎重考虑，否则会对系统安装造成严重影响。

**rwx 权限对目录的作用**  
 

<table><caption>rwx 权限对目录的作用</caption><tbody><tr><th>rwx 权限</th><th>对目录的作用</th></tr><tr><td><p>&nbsp; 读权限</p><p>（r）[4]</p></td><td>表示具有读取目录结构列表的权限，也就是说，可以看到目录中有哪些文件和子目录。一旦对目录拥有 r 权限，就可以在此目录下执行 ls 命令，查看目录中的内容。</td></tr><tr><td>&nbsp; 写权限（w）[2]</td><td>对于目录来说，w 权限是最高权限。对目录拥有 w 权限，表示可以对目录做以下操作：<ul><li>在此目录中建立新的文件或子目录；</li><li>删除已存在的文件和目录（无论子文件或子目录的权限是怎样的）；</li><li>对已存在的文件或目录做更名操作；</li><li>移动此目录下的文件和目录的位置。</li></ul>一旦对目录拥有 w 权限，就可以在目录下执行 touch、rm、cp、mv 等命令。</td></tr><tr><td>执行权限（x）[1]</td><td>目录是不能直接运行的，对目录赋予 x 权限，代表用户可以进入目录，也就是说，赋予 x 权限的用户或群组可以使用 cd 命令。</td></tr></tbody></table>

  
对目录来说，如果只赋予 r 权限，则此目录是无法使用的。很简单，只有 r 权限的目录，用户只能查看目录结构，根本无法进入目录（需要用 x 权限），更不用说使用了。  
因此，对于目录来说，常用来设定目录的权限其实只有 0（---）、5（r-x）、7（rwx）这 3 种。