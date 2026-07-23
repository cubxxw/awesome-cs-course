# 使用slidev做出ppt教程

<p align='center'>
<a href="https://www.linkedin.cn/injobs/in/xiongxinwei-xiong-7606a0227" target="_blank"><img src="https://img.shields.io/badge/linkedin-xiongxinwei-yellowgreen?logo=linkedin"></a>
<a href="https://twitter.com/xxw3293172751" target="_blank"><img src="https://img.shields.io/badge/twitter-%40xxw3293172751-informational?logo=twitter"></a>
<a href="https://www.zhihu.com/people/3293172751" target="_blank"><img src="https://img.shields.io/badge/%E7%9F%A5%E4%B9%8E-%E9%93%BE%E5%AD%A6%E8%80%85%E7%A4%BE%E5%8C%BA-blue?logo=zhihu"></a>
<a href="https://s2.loli.net/2022/07/05/sQHuozItvWg1heA.jpg" target="_blank"><img src="https://img.shields.io/badge/%E5%BE%AE%E4%BF%A1-smile-brightgreen?logo=wechat"></a>
<a href="https://space.bilibili.com/14089380" target="_blank"><img src="https://img.shields.io/badge/b%E7%AB%99-%E6%97%A0%E4%B8%8E%E4%BC%A6%E6%AF%94%E7%9A%84%E5%BE%97%E5%BE%97-red?logo=bilibili"></a>
</p>
<p align='center'>
<a href="https://weibo.com/u/6248930985" target="_blank"><img src="https://img.shields.io/badge/%E5%BE%AE%E5%8D%9A-%E6%97%A0%E4%B8%8E%E4%BC%A6%E6%AF%94%E7%9A%84%E5%BE%97%E5%BE%97-critical?style=social&logo=Sina%20Weibo"></a>
<a href="https://github.com/3293172751" target="_blank"><img src="https://img.shields.io/badge/Github-xiongxinwei-inactive?style=social&logo=github"></a>
</p>


---

[toc]

## 特征

- 📝 [**基于 Markdown**](https://sli.dev/guide/syntax.html) - 使用您喜欢的编辑器和工作流程
- 🧑‍💻 [**开发人员友好**](https://sli.dev/guide/syntax.html#code-blocks)- 内置语法突出显示、实时编码等。
- 🎨 [**Themable**](https://sli.dev/themes/gallery.html) - 主题可以与 npm 包共享和使用。
- 🌈 [通过Windi CSS](https://windicss.org/)或[UnoCSS提供的](https://github.com/unocss/unocss)[**时尚**](https://sli.dev/guide/syntax.html#embedded-styles)按需实用程序。
- 🤹 [**交互式**](https://sli.dev/custom/directory-structure.html#components)- 无缝嵌入 Vue 组件。
- 🎙 [**演示者模式**](https://sli.dev/guide/presenter-mode.html)- 使用另一个窗口，甚至您的手机来控制您的幻灯片。
- 🧮 [**LaTeX**](https://sli.dev/guide/syntax.html#latex) - 内置 LaTeX 数学方程支持。
- 📰 [**图表**](https://sli.dev/guide/syntax.html#diagrams)- 创建带有文本描述的图表
- 🌟 [**图标**](https://sli.dev/guide/syntax.html#icons)- 直接从任何图标集中访问图标。
- 💻 [**Editors**](https://sli.dev/guide/editors.html) - 集成编辑器，或[VS Code 的扩展](https://github.com/slidevjs/slidev-vscode)
- 🎥 [**录制**](https://sli.dev/guide/recording.html)- 内置录制和摄像头视图。
- 📤 [**便携**](https://sli.dev/guide/exporting.html)- 导出为 PDF、PNG，甚至是可托管的 SPA。
- ⚡️ 由[Vite提供支持的](https://vitejs.dev/)[**快速**](https://vitejs.dev/)即时重新加载。
- 🛠 [**Hackable**](https://sli.dev/custom/config-vite.html) - 使用 Vite 插件、Vue 组件或任何 npm 包。

## 安装

> Slidev需要Node.js的版本 >= 14.0.0

### 使用npm

```
npm init slidev@latest
```

### 使用yarn

```
yarn create slidev
```

**我们更倾向于将slidev全局安装**

```
npm i -g @slidev/cli
yarn global add @slidev/cli
```



我们也可以使用yarn，先安装yarn

```
npm i -g yarn
yarn global add @slidev/cli
```

> 如果安装过慢，可以更换npm源或者yarn源



## 入门

我们简单的写一个`hello.md`

```markdown
root@ubuntu:/slides# cat hello.md 
# hello word
## hello
# 区块链学习指南+笔记（最全）

##### [Facebook](https://www.facebook.com/profile.php?id=100034435372354) | [Website](https://telsacoin.io/) | [Blog](http://cubxxw.com) | [Telegram](https://t.me/smile3293172751) | [Twitter](https://twitter.com/xxw3293172751) | [Linkedin](https://www.linkedin.cn/injobs/in/xiongxinwei-xiong-7606a0227) | [Donate](https://liberapay.com/xiongxinwei/donate)

---

### 导航
root@ubuntu:/slides# cat hello.md 
# hello word
## hello
# 区块链学习指南+笔记（最全）

##### [Facebook](https://www.facebook.com/profile.php?id=100034435372354) | [Website](https://telsacoin.io/) | [Blog](http://cubxxw.com) | [Telegram](https://t.me/smile3293172751) | [Twitter](https://twitter.com/xxw3293172751) | [Linkedin](https://www.linkedin.cn/injobs/in/xiongxinwei-xiong-7606a0227) | [Donate](https://liberapay.com/xiongxinwei/donate)

---

### 导航
```

**可以看到有三页**

![image-20220728203513233](https://sm.cubxxw.com/image-20220728203513233.png?mail:3293172751@qq.com)

+ 说明`---`可以分页、、
+ 最后一页以`end`结尾



## LaTex公式

我们加入latex公式，发现也是支持的

```latex
---
$$
 {\color{Green} \begin{Vmatrix}
  &1  &2  &3  &4 \\
  &6  &1  &1  &4 \\
  &5  &4  &32  &32 \\
  &7  &345  &5  &2 \\
  &7  &8  &8  &2
\end{Vmatrix}} 
$$

$$
\left [ 0,1 \right )
$$
```

<img src="https://sm.cubxxw.com/image-20220728204120469.png?mail:3293172751@qq.com" alt="image-20220728204120469" style="zoom:50%;" />

## 主题

+ [我们可以在slidev官网中找到很多我们喜欢的主题](https://cn.sli.dev/themes/use.html)

在 Slidev 中更换主题非常简单。在 frontmatter 中添加 `theme:` 配置即可。

```
---
theme: seriph
---
```

在服务启动后，它会自动提示你是否安装该主题：

```
? The theme "@slidev/theme-seriph" was not found in your project, do you want to install it now? › (Y/n)
```

或者你也可以手动安装：

```
$ npm install @slidev/theme-seriph
```



我们随便使用一个主题看看

```
---
theme: vuestorefront
---
```

![好看](https://sm.cubxxw.com/image-20220728204844253.png?mail:3293172751@qq.com)



## 演讲者模式

> 进入演讲者模式有两种方法
>
> **一种是正常模式下直接点击，一种使用下面命令进入。**

![image-20220728220945934](https://sm.cubxxw.com/image-20220728220945934.png?mail:3293172751@qq.com)

![image-20220728221302598](https://sm.cubxxw.com/image-20220728221302598.png?mail:3293172751@qq.com)

**在这种模式下可以同步笔记痕迹，而且也可以同步鼠标。**

> 这种模式下需要写注释，此时可以vs直接注释，注意注释一般写后面

![image-20220728221820044](https://sm.cubxxw.com/image-20220728221820044.png?mail:3293172751@qq.com)



## 局域网同步

在终端提醒中有一个`remote control`,我们在下面输入

```
slidev README.md --remote
```

![image-20220728222412617](https://sm.cubxxw.com/image-20220728222412617.png?mail:3293172751@qq.com)

> 但是我好像发现有点卡





## 编辑器集成

Slidev 带有一个集成的 [CodeMirror](https://codemirror.net/) 编辑器，可以立即重新加载并保存更改到文件中。

点击🖱️按钮来打开它。

![image-20220729114104015](https://sm.cubxxw.com/image-20220729114104015.png?mail:3293172751@qq.com)



## PDF

> 导出为 PDF 或 PNG 的功能基于 [Playwright](https://playwright.dev/) 实现渲染。因此，使用此功能前需要安装 [`playwright-chromium`](https://playwright.dev/docs/installation#download-single-browser-binary)。 

安装 `playwright-chromium`：

```
npm i -D playwright-chromium
npm i -D @playwright/test
npx playwright install
```

接着，使用如下命令即可将你的幻灯片导出为 PDF：

```
$ slidev export
```

![image-20220729120552790](https://sm.cubxxw.com/image-20220729120552790.png?mail:3293172751@qq.com)



稍作等待，即可在 `./slides-export.pdf` 路径下看到你幻灯片的 PDF 文件。

```
2022/07/29  12:04         4,076,506 slides-export.pdf
```

如果你想要导出使用暗色主题的幻灯片，请使用 `--dark` 选项：

```
$ slidev export --dark
```

### 导出点击步骤

> 自 v0.21 起可用

默认情况下，Slidev 会将每张幻灯片导出为 1 页，并忽略点击动画。如果你想将多个步骤的幻灯片，分解为多个页面，请使用 `--with-clicks` 选项。

```
$ slidev export --with-clicks
```

## PNGs

当为命令传入 `--format png` 选项时，Slidev 会将每张幻灯片导出为 PNG 图片格式。

```
$ slidev export --format png
```

## 单页应用（SPA）

请参阅 [静态部署](https://cn.sli.dev/guide/hosting.html) 章节。



## 版权声明

本书所有内容遵循 [CC-BY-SA 3.0协议（署名-相同方式共享）](http://zh.wikipedia.org/wiki/Wikipedia:CC-by-sa-3.0协议文本)http://zh.wikipedia.org/wiki/Wikipedia:CC-by-sa-3.0协议文本)