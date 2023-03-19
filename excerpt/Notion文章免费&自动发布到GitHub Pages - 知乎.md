> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [zhuanlan.zhihu.com](https://zhuanlan.zhihu.com/p/469320294)

> 摘要本方案需要依赖：1. Notion - One workspace. Every team2. https://github.com/dragonman225/notablog3. https://docs.github.com/cn/actions 最终可以实现：1. 有一个自己的 免费网页 Github Pages 2. 在Not…

摘要
--

### 本方案需要依赖：

1. [Notion - One workspace. Every team](https://link.zhihu.com/?target=https%3A//www.notion.so/)

2. [https://github.com/dragonman225/notablog](https://link.zhihu.com/?target=https%3A//github.com/dragonman225/notablog)

3. [https://docs.github.com/cn/actions](https://link.zhihu.com/?target=https%3A//docs.github.com/cn/actions)

### 最终可以实现：

1. 有一个自己的**免费**网页 `Github Pages`

2. 在 Notion 中写完任何文章后的一天内**免费 & 自动**发布到 `Github Pages` 中，

3. 成品请参考我的网页：

[FizzerYu](https://link.zhihu.com/?target=https%3A//fizzeryu.github.io/)

步骤
--

1. 首先你要有个 Github 账号和 Notion 账号，如果你是学生的话可以免费领取 [GitHub Student Developer Pack](https://link.zhihu.com/?target=https%3A//education.github.com/pack) 和 [Notion for Education](https://link.zhihu.com/?target=https%3A//www.notion.so/product/notion-for-education)

2. 创建一个新仓库，需要给这个仓库一个比较特殊的名字，比如说 [userName.github.io](https://link.zhihu.com/?target=http%3A//username.github.io/)，注意这里的 userName 可以是任意你喜欢的名字，但是**强烈推荐使用自己的 github 用户名**

3. 把这个 [Notion 表格模板](https://link.zhihu.com/?target=https%3A//www.notion.so/b6fcf809ca5047b89f423948dce013a0) 复制到自己的 Notion 账号中【右上角的 `Duplicate` 按钮】，把这个表格设置为公开【 点击右上角的 `Share` 按钮→ `Share to web` 打开 】，然后点击 `Copy` 按钮复制网址

3.1 比如说我复制出来的网址是 `https://www.notion.so/2c4dc4affe68463da4f61f8fa93406e0`，打开这个网址检查一下是不是对应的表格模板

4. fork 仓库 [https://github.com/dragonman225/notablog-starter](https://link.zhihu.com/?target=https%3A//github.com/dragonman225/notablog-starter) ，修改 `config.json` 文件中的 `url`

1. 原始的 url 为：

```
"url": "https://www.notion.so/b6fcf809ca5047b89f423948dce013a0?v=03ddc4d6130a47f8b68e74c9d0061de2",

```

2. 将 b6fc...de2 这段字符修改为 `步骤3.1` 中的网址后缀 (也就是 2c4dc...b594)

3. 对应到本文中也就是修改为：

```
"url": "https://www.notion.so/2c4dc4affe68463da4f61f8fa93406e0?v=9ad1da13a555443598a33b3bfc3db594"

```

5. 创建 `Personal access token`

5.1. 打开 [Github.com](https://link.zhihu.com/?target=http%3A//github.com/)，依次点击 右上角头像 → Settings → Developer settings → Personal Access Tokens → 右上角 Generate new token → 按照示例修改下面

![](https://pic2.zhimg.com/v2-28f429a80e39d264a47e857caf0992d9_r.jpg)

5.2. 最后点击最下方的 Generate token ，就会生成一个字符串，复制这个字符串备用【**注意千万别泄露这个字符串，有安全风险**】

![](https://pic2.zhimg.com/v2-a523bd1705c26e9dafd3b960742aab7d_r.jpg)

6. 回到你在 `步骤4` 中 fork 的名为 `notablog-starter` 仓库，依次点击 `settings` → `secrets` → `actions` → `New repository secret`， Name 填为 `ACCESS_TOKEN` ，Value 就是上一步 ( `步骤5.2` ) 生成的字符串，具体如图所示：

![](https://pic1.zhimg.com/v2-9c72bebbb68fb3c2f12d2974b9fbac38_r.jpg)

7. 回到你在 `步骤4` 中 fork 的名为 `notablog-starter` 仓库，依次点击 `Actions` → `New workflow` → `set up a workflow yourself`

![](https://pic3.zhimg.com/v2-01ea9fb6b090983b97c991e9dc7add46_r.jpg)![](https://pic2.zhimg.com/v2-3fcee75381f98c44c085ae7a4f485b9d_r.jpg)

7.1. 使用下方代码覆盖原始文件，注意代码需要修改两处位置：

```
    # This is a basic workflow to help you get started with Actions
    
    name: Github Pages
    
    # Controls when the workflow will run
    on:
      schedule:
    		# 下一行是说在每天的国际标准时间23点（北京时间早上7点）触发该任务
    		# 你可以随意修改
        - cron: '0 23 * * *'    
      # Allows you to run this workflow manually from the Actions tab
      workflow_dispatch:
    
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "build"
      build:
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
    
        # Steps represent a sequence of tasks that will be executed as part of the job
        steps:
          # 拉取代码
          - name: Checkout
            uses: actions/checkout@v2
          # 1、生成静态文件
          - name: Build
            run: npm i -g notablog && notablog generate .
          # 2、部署到 GitHub Pages
          - name: Deploy
            uses: JamesIves/github-pages-deploy-action@v4.2.5
            with:
              token: ${{ secrets.ACCESS_TOKEN }}
              repository-name: FizzerYu/FizzerYu.github.io # 修改这里
              BRANCH: main  # 如果你的仓库默认分支是 master 记得修改这里                    
              FOLDER: public

```

7.2. 上方代码需要修改 `repository-name` ，具体就是步骤 2 中创建的仓库的 `git clone` 地址，比如说：

```
# git clone 地址为：
git@github.com:FizzerYu/FizzerYu.github.io.git
# 因此 repository-name 为：
FizzerYu/FizzerYu.github.io

```

7.3. 注意 `步骤2` 中创建的仓库默认分支名是否与 `BRANCH` 参数一致

7.4. 按照要求修改完成后保存：

![](https://pic4.zhimg.com/v2-d1b0322d507957c57d8608f2fd369353_r.jpg)

8. 按照下图要求依次点击：

![](https://pic3.zhimg.com/v2-2b3734e11f795bd60fecf2f3ef9b4472_r.jpg)

9. 稍等片刻 (大概 10min 内)，就可以打开你的 github pages 查看啦！比如说我的网址 `[http://FizzerYu.github.io](https://link.zhihu.com/?target=http%3A//FizzerYu.github.io)`

测试
--

1. 在 notion 中找到复制出来的模板，复制一行数据

![](https://pic2.zhimg.com/v2-5f33cea59c901b872119e2368c75640d_r.jpg)

2. 然后随便写点东西，再重复步骤 8、9，或者等待一天，就可以看到新的文章啦！