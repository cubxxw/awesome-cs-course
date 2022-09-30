# git自动推送脚本

[toc]

**建议:**

配合`vscode`处理异常冲突更香哦~



## 思路

```bash
if (检查文件是否有改动 == TRUE) {
	// 合并更新推送
	git add .
	git commit -m "用户名 update"  或  "自定义备注"
	git pull origin ${当前分支}
	git push origin ${当前分支}
} else {
	// 更新本地代码
	git pull origin ${当前分支}
}
```



## 适用

**适用于Linux和windows，Linux天然支持，在windows是配合的bash脚本**

- Linux
- Windows (git bash中使用)



## 全局安装

> 任意git仓库目录可用
>
> **可添加到环境变量中**

### Linux

- 克隆到`/home/gitsync/`, 把目录添加到环境变量
- 或者直接丢到`/usr/local/bin`



### Windows

- 复制`gitsync.sh`到`C:\windows\system32\`目录即



#### 局部安装

> 只能在项目根目录使用

- 复制`gitsync.sh`到项目仓库根目录



#### 使用

**懒人方法**

> 直接提交, 免输入提交说明. 默认为 `git用户名 update`

```bash
gitsync.sh
```



**自定义提交说明**

```bash
gitsync.sh "更新了README"
# == git commit -m "更新了README"
```



## 代码

```bash
# 本地文件是否发生了改变
is_change=$(git status -s)

# 当前分支
branch=$(git symbolic-ref --short -q HEAD)

# remark
if [ -n "$1" ]; then 
    guser=$1
else
    # git.user.name
    guser="$(git config user.name) update"
fi

if [ 0 -lt ${#is_change} ]; then
    git add .
    git commit -m "$guser"
    # pull
    result=$(git pull origin $branch)
    tmp=$(echo $result | grep "fix conflicts")
    if [ "$tmp" != "" ]
    then
        echo "(ノ=Д=)ノ┻━┻ 合并冲突, 请手动解决后提交"
    else
        # 推送
        git push origin $branch
    fi
    
else
    echo "本地没有改变, 正在从远程仓库同步代码. 请耐心等待 ╭(●｀∀´●)╯╰(●’◡’●)╮";
    result=$(git pull origin $branch)
    tmp=$(echo $result | grep "fix conflicts")
    if [[ "$tmp" != "" ]]
    then
      echo "(ノ=Д=)ノ┻━┻ 合并冲突, 请手动解决后提交"
    fi
fi
```

