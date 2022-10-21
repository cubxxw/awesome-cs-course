# 保持自己github的forks自动和上游仓库同步并推送到 gitee

[toc]

## ⚡前言

我们都是这个仓库未来很有潜力的客户，或者是贡献者，谢谢你选择了加入`C-UB`社区，我们将会一起合作，未来我们将会考虑以区块链为底层搭建一个全民学习平台，我们每一个人都将会是这个社区的构建着，将会推动[c-universal blockchain（链学）](https://github.com/c-ub) 社区的发展和进步~

> 或许我们会很疑惑？
>
> 在我们参与贡献的时候，如果克隆了**[🧷 cs-awesome-Block_Chain](https://github.com/3293172751/cs-awesome-Block_Chain)项目，但是每次获取上游代码很麻烦怎么办？今天将会解决这个麻烦⬇️ **



## ⚡自动拉取 fork 源上游

### 方法一：apps pull

📢：只要你不改fork 库的内容，那么是纯净同步模式，时间点都和上游仓库一致。同步更新时间 3 个小时以上。

#### 安装 github app

[🧷 访问地址：https://github.com/apps/pull](https://github.com/apps/pull)

[🧷 开源地址：https://github.com/wei/pull#readme](https://github.com/wei/pull#readme)

📜 对上面的解释：

![image-20221015194311152](http://sm.nsddd.top/smimage-20221015194311152.png?xxw@nsddd.top)

⚠️ 安装成功：

![image-20221015194613047](http://sm.nsddd.top/smimage-20221015194613047.png?xxw@nsddd.top)



#### 打开pull app

![image-20221015194905209](http://sm.nsddd.top/smimage-20221015194905209.png?xxw@nsddd.top)



#### 选择自己需要的仓库或者所有的仓库

![image-20221015195024104](http://sm.nsddd.top/smimage-20221015195024104.png?xxw@nsddd.top)

**📮 最后：**

上游仓库改变，过了几个小时后，自动同步成功



#### 高级设置（带配置）

😍 同时也支持修改同步的时间，默认是三个小时~

拉应用程序将每隔几个小时使用**硬重置**自动监视并将上游的默认（主）分支拉入您的分支。您也可以随时手动[触发](https://github.com/wei/pull#trigger-manually)它。

1. 创建一个新分支。

2. 在存储库设置 > 分支下将新分支设置为默认分支。

3. 添加`.github/pull.yml`到您的默认分支。

   **最常见的：**

   （行为与基本设置相同）

   ```
   version: "1"
   rules:
     - base: master
       upstream: wei:master    # change `wei` to the owner of upstream repo
       mergeMethod: hardreset
   ```

   **高级用法：**

   ```bash
   version: "1"
   rules:                      # Array of rules
     - base: master            # Required. Target branch
       upstream: wei:master    # Required. Must be in the same fork network.
       mergeMethod: hardreset  # Optional, one of [none, merge, squash, rebase, hardreset], Default: none.
       mergeUnstable: false    # Optional, merge pull request even when the mergeable_state is not clean. Default: false
     - base: dev
       upstream: master        # Required. Can be a branch in the same forked repo.
       assignees:              # Optional
         - wei
       reviewers:              # Optional
         - wei
       conflictReviewers:      # Optional, on merge conflict assign a reviewer
         - wei
   label: ":arrow_heading_down: pull"  # Optional
   conflictLabel: "merge-conflict"     # Optional, on merge conflict assign a custom label, Default: merge-conflict
   ```

4. 转到`https://pull.git.ci/check/${owner}/${repo}`验证您的`.github/pull.yml`（仅限公共回购）。有关验证它的另一种方法，请参见[#234](https://github.com/wei/pull/issues/234)。

5. 安装[拉取应用程序](https://github.com/apps/pull)。



### 方法二：github Actions🔥

📮 Actions是一个让我分沉迷其中的功能，大概体验过就知道它有多快乐~

+ [教程在这里](http://nsddd.top/actions)

| 列表                                                         | github 商店                                          |      |
| ------------------------------------------------------------ | ---------------------------------------------------- | ---- |
| [Fork Sync](https://github.com/tgymnich/fork-sync)           | https://github.com/marketplace/actions/fork-sync     |      |
| [Github Action: Upstream Sync](https://github.com/aormsby/Fork-Sync-With-Upstream-action) | https://github.com/marketplace/actions/upstream-sync |      |

#### Fork Sync

💡简单的一个案例如下：

![image-20221015200420934](http://sm.nsddd.top/smimage-20221015200420934.png?xxw@nsddd.top)

自用配置：

```yaml
# .github/workflows/sync.yml
name: Sync Fork

on:
  push: # push 时触发, 主要是为了测试配置有没有问题
  schedule:
    - cron: '* */3 * * *' # 每3小时触发, 对于一些更新不那么频繁的项目可以设置为每天一次, 低碳一点
jobs:
  repo-sync:
    runs-on: ubuntu-latest
    steps:
      - uses: TG908/fork-sync@v1.6.3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          owner: c-ub # fork 的上游仓库 user
          head: master # fork 的上游仓库 branch
          base: master # 本地仓库 branch
```

**参数详解：**

| name         | Optional | Default             | description            |
| ------------ | -------- | ------------------- | ---------------------- |
| owner        | ✅        | $current_repo_owner | 分叉存储库的所有者     |
| token        | ✅        | ${{ github.token }} | 访问 Github API 的令牌 |
| head         | ✅        | master              | fork 的上游仓库 branch |
| base         | ✅        | master              | 本地仓库 branch        |
| merge_method | ✅        | merge               | 合并、变基或压缩       |
| pr_title     | ✅        | Fork Sync           | 创建的拉取请求的标题   |
| pr_message   | ✅        |                     | 创建的拉取请求的消息   |
| ignore_fail  | ✅        |                     | I忽略异常              |

⚠️$current_repo_owner 是您自己的用户名！

⚠️仅提供 和 的分支`head`名称`base`。`user:branch`不管用！

⚠️ 如果`auto_approve`设置为`true`您必须在默认 github 令牌中提供个人访问令牌`token`将不起作用！





## ⚡手动拉取 fork 源上游

### 情况一：代码不冲突

#### 方法一：Fetch upstream

📢：同步是干净的，完全同步

![image-20221015203227894](http://sm.nsddd.top/smimage-20221015203227894.png?xxw@nsddd.top)



#### 方法二：Pull requests

> **该方法会导致 commit 记录比 fork 的源多，如果你要保持该仓库的干净整洁，这种 merge pull requests 的情况就不合适了。**

![image-20221015203831326](http://sm.nsddd.top/smimage-20221015203831326.png?xxw@nsddd.top)



#### 方法三：命令行同步复制

[参考官方文档：https://docs.github.com/cn/pull-requests/collaborating-with-pull-requests/working-with-forks/syncing-a-fork](https://docs.github.com/cn/pull-requests/collaborating-with-pull-requests/working-with-forks/syncing-a-fork)

指定fork 的上游仓库（upstream类似于组织名称，随便取）：

```bash
git remote add upstream https://github.com/ORIGINAL_OWNER/ORIGINAL_REPOSITORY.git
```



验证：

```bash
git remote -v
```



上游获取提交数据：

```bash
git fetch upstream
```



检出 fork 的本地默认分支，这里用 main 测试：

```bash
git checkout main
```



将上游默认分支，本例中为 upstream/main 的更改合并到本地默认分支，记得要按 q 退出编辑模式，不会就直接关闭当前命令行在进入：

```bash
git merge upstream/main
```



推送到 github：

```bash
git push origin main
```



## ⚡自动推送到 gitee

### github Actions 方法

| 推荐列表                                                     | github 商店                                                  |      |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| [Hub Mirror Action](https://github.com/Yikun/hub-mirror-action) | https://github.com/marketplace/actions/hub-mirror-action     |      |
| [repository-mirroring-action](https://github.com/marketplace/actions/mirroring-repository) | https://github.com/pixta-dev/repository-mirroring-action     |      |
| [Git Mirror Action](https://github.com/wearerequired/git-mirror-action) | https://github.com/marketplace/actions/mirror-a-repository-using-ssh |      |
| [mirror-action](https://github.com/yesolutions/mirror-action) | https://github.com/marketplace/actions/mirror-repository     |      |
| [Mirror to GitLab and trigger GitLab CI](https://github.com/SvanBoxel/gitlab-mirror-and-ci-action) | https://github.com/marketplace/actions/mirror-to-gitlab-and-run-gitlab-ci#mirror-to-gitlab-and-trigger-gitlab-ci |      |



### Hub Mirror Action

#### 参考列表

+ [Hub Mirror Action](https://github.com/Yikun/hub-mirror-action) ：github 源码库
+ [Sync GitHub to other hub](https://github.com/yi-Xu-0100/hub-mirror) ：一个用于展示如何使用这个action的模板仓库
+ [自动镜像 GitHub 仓库到 Gitee](https://github.com/ShixiangWang/sync2gitee)：一个关于如何使用这个action的介绍
+ [巧用Github Action同步代码到Gitee](https://yikun.github.io/2020/01/17/巧用Github-Action同步代码到Gitee/): Github Action第一篇软文

#### 过程

1. 一开始在 github 商店找 actions 方法，还是打算直接通过集成实现，因为 gitee 免费版不具备相关自动化的功能。

https://github.com/marketplace?type=actions&query=gitee+

然后发现，stars 数最高的一个项目，参考下，可以支持多 repo 仓库，那么可以单独拆开，也可以设置一个统一的推送仓库来配置，非常不错

![image-20221015212612986](http://sm.nsddd.top/smimage-20221015212612986.png?xxw@nsddd.top)



**代码核心流程：**

![image-20221015215703305](http://sm.nsddd.top/smimage-20221015215703305.png?xxw@nsddd.top)



要注意的地方： on.push.branches 或者 on.pull_request.branches 不管是哪个仓库，由于 github 上面旧的仓库还是 master 而不是 main，所以你要改成对应的

```yaml
name: Gitee repos mirror periodic job

on:
  push: # 有 push 代码的时候会执行
    branches: [ main ]
  pull_request: # 有 pull_request 代码的时候会执行
    branches: [ main ]
  schedule: 
    # 每3个小时跑一次
    - cron:  '0 */3 * * *'

jobs:
  build:

    runs-on: ubuntu-latest

    steps:

    - name: Mirror the Github organization repos to Gitee.
      uses: Yikun/hub-mirror-action@v1.0
      with:
        src: github/ZhaoUncle	# 必选，需要同步的Github用户（源）
        dst: gitee/qingfeng689 # 必选，需要同步到的Gitee的用户（目的）
        dst_key: ${{ secrets.GITEE_PRIVATE_KEY }} # 必选，Gitee公钥对应的私钥，https://gitee.com/profile/sshkeys
        dst_token:  ${{ secrets.GITEE_TOKEN }} # 必选，Gitee对应的用于创建仓库的token，https://gitee.com/profile/personal_access_tokens
        account_type: user # 如果是组织，指定组织即可，默认为用户user
        timeout: 600 # git 命令超时时间
        debug: true # 测试用，正常可以关掉
        force_update: true # 强制推送到 dst 仓库
        white_list: "test2,community.applications" # 白名单机制，可以用于更新某些指定库
```



#### 参考一：

https://github.com/marketplace/actions/hub-mirror-action

![202201241316027](http://sm.nsddd.top/sm202201241316027.png?xxw@nsddd.top)

#### 参考二

https://github.com/yi-Xu-0100/hub-mirror

用到的地址：

+ github ssh key 配置地址：https://github.com/settings/keys
+ gitee ssh key 配置地址：https://gitee.com/profile/sshkeys
+ github toekn 配置地址：https://github.com/settings/tokens
+ gitee token 配置地址：https://gitee.com/profile/personal_access_tokens

![img](http://sm.nsddd.top/sm202201241317019.png?xxw@nsddd.top)

### 想简单使用，可以直接使用以下方式

fork 该仓库到你本地，然后修改 相关参数就好了，截图如下

![202201241334323](http://sm.nsddd.top/sm202201241334323.png?xxw@nsddd.top)



## ⚡链接

**推荐阅读：**

[Github进行fork后如何与原仓库同步：重新fork很省事，但不如反复练习版本合并](https://github.com/selfteaching/the-craft-of-selfteaching/issues/67)



