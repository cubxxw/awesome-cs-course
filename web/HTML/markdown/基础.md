[TOC]

😶‍🌫️参考网站：[http://zt.nsddd.top](http://zt.nsddd.top)

>   智慧停车源码学习教程

😶‍🌫️源码地址：github: [https://github.com/3293172751/nsssds/tree/master/web](https://github.com/3293172751/nsssds/tree/master/web)



 # HTML5基础




<h3> HTML标记
</h3>

<ul>
    <li>单标记</li>
    	但标记也称之为空标记，是指用一个标记符号即可完整地表述某个功能地标记
    <li>双标记</li>
    	双标记也称之为体标记，是指由开始和结束两个标记符组成地标记，其语法为：
    	<标记名>内容</标记名></ul>



<h3>HTML语法
</h3>

```
<!DOCTYPE>标记
```

位于文档最前面，只有在开头使用了<!DOCTYPE>声明，浏览器才能作为有效文档识别



```
<html></html>标记
```

位于<!DOCTYPE>之后，称之为根标记，标志着文档的开始



```
<head>
	<meta charset = "utf-8">  //或者改为gbk
	<title>云课堂</titile>
</head>
```

称之为头标记，主要是用来封装其他位于文档头部的标记



```
</hr>
```

水平线标记

</hr>



```
<p>
```

**段落标记**

<p>这是一个段落
</p>



```
<style></style>标记
```

**用来标记HTML文档定义样式信息**

在HTML中使用style标记时，常常定义其属性为type，相对应的属性为text/css，表示内嵌式地CSS样式

```html
<style type = "text/css">
	h2 {color:red;}
	p {color:blue;}
</style>
```



<h3>
    HTML常用元素
</h3>

>   html提供了6个等级的标题，从<h1>到<h6>递减



```
<pre>元素
</pre>
```

pre元素可以对空格和空行进行保留控制，和p元素基本相同，唯一区别是，该元素中的文字内容将保留空格和换行符，并且元素中的英文字符都将统一使用等宽字体





```
<dev>可以嵌套绝大多数的HTML，甚至是dev
```





**无序列表**

```
<ul type = "符号类型">
	<li>列表项1</li>
	<li>列表项2</li>
	<li>列表项3</li>
</ul>
```

<li type = “disc">可以为disc（实心圆点）默认的原点</li>

<li type = “disc">可以为circle（定心圆点）默认的原点</li>

<li type = “disc">可以为square（方块）默认的原点</li>



**有序列表**

```
<ol type="符号类型">
	<li>列表项1</li>
	<li>列表项2</li>
	<li>列表项3</li>
</ol>
```

<ol type="符号类型">
	<li>列表项1</li>
	<li type = "a">列表项2</li>
	<li>列表项3</li>
</ol>



**图像标记**

```
<img src="图像URL" />
```

> 1.alt 图片添加alt属性时，如果添加的图片能够正常显示，则不会显示出效果，但是如果添加的图片因为某种原因而无法显示，则会将无法显示的图片替换成文字
>2.title 鼠标箭头悬停在图片上时，将会显示出来的文字
>3.width 调整图片的宽度属性
>4.height 调整图片的高度属性
>需要注意一点，在进行图片修改的时候，我们其实只需要修改图片的width或者height一项即可，因为修改其任意一个，高度和宽度都会等比例缩放。
>5.border 设置图像的边框颜色，在css中使用较多，HTML中使用较少。
>6.img 是个单标签

<hr>

![20190326133019183](https://s2.loli.net/2022/03/02/3phOSNLaskQJfYo.png)

<br>

<img src =“https://s2.loli.net/2022/03/02/3phOSNLaskQJfYo.png"/>

</br>



**创建超链接**

1.   **href:用于指定目标的url地址，但<a>标记应用到href属性时，他就具备了超链接功能**

2.   **target:用于指定链接页面的打开方式，取值有_self和/ _ blank两种，其中self为默认值，在原窗口打开**

```
<li><a href=https://s2.loli.net/2022/03/02/3phOSNLaskQJfYo.png"target="_blank">图片网站链接</a></li>
```

<li><a href=https://s2.loli.net/2022/03/02/3phOSNLaskQJfYo.png"target="_blank”>图片网站链接</a></li>



```
<p>&nbsp;</p>
```

**空行**



```
<a href="#">
<a href="#one">   //标记


<h3 id="one">跳转到这里</h3>
```

**空连接**

> 补充关于makedown跳转链接

```
[点击跳转到标题一](# HTML5基础)
```

[点击跳转到标题一](# HTML5基础)







