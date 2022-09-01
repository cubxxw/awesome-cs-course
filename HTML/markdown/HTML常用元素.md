[toc]

😶‍🌫️参考网站：[http://zt.nsddd.top](http://zt.nsddd.top)

>   智慧停车源码学习教程

😶‍🌫️源码地址：github: [https://github.com/3293172751/nsssds/tree/master/web](https://github.com/3293172751/nsssds/tree/master/web)



# HTML5常用元素

### 1. ul元素和ol元素

**ul是无序列表**

```
disc :实心原点（默认）
circle : 空心原点
square ：方块
```

**ol是有序列表**

```
<ol type = "符号类型">
可以是 A 、a 、1
```



**水平线设置**

```
<hr size = "2" color ="red" width = "800" />
```

**长度设置为800正好符合markdown输入**

<hr size = "10" color ="red" width = "800" />



### 2. a元素

**href:设定链接目标的打开地址，具有超链接功能**

**target: 用于指定连接的页面打开方式,取值有`_self`和`_blank`两种，其中`_self`为默认值，在原窗口打开**



**图片单击放大：**

```
<a href = "../a.jpg"><img src = "a.jpg"></a>
```



##### 文字格式化

```
<b></b>和<strong></strong>  : 文字以粗体方式显示（strong强调文本)
```

<b>粗体</b>

<strong>粗体</strong>

```
<i></i>和<em></em> : 斜体
```

<i> 斜体</i>

```
<del>删除线</del>
```

<del>删除线</del>

```
<ins>下划线</ins>
```

<ins>下划线</ins>



### 3. 表格元素

**表格元素使用`<table></table>`**



**`<th>表头</th>`默认是加粗居中的**

```html
<table cellspacing = "0" border = "3" >
	<tr>
	<tr>
		<th>星期一</th>
		<th>星期二</th>
		<th>星期三</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
</table>
```

<table cellspacing = "0" border = "3" >
	<tr>
	<tr>
		<th>星期一</th>
		<th>星期二</th>
		<th>星期三</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
</table>
<br>



**表格手动设置宽高**

```
<table width=value height=value>
/*border边框默认为0 */
<table border="3" align = "center"  width=”200 height="400">
```

**表格的对齐方式`lift, center ,right`**

```html
/*对齐方式*/
align = value 

/*颜色*/
bgcolor = value

/*表格的背景图片*/
background = value

/*单元格间隙*/
cellspacing = "i"

/*单元格空白区域大小默认1*/
cellpadding = "j"
```



<table bgcolor = "red" border="3 width="20" height="40" align = "center">
 <tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
</table>

> 注意：不是字体单元格的对齐



**`tr`行设置**

```
/*某一行高度*/
<tr height = value> </tr>

/*边框颜色*/
bgcolor = value

/*背景颜色*/
bordercolor = value

/*水平位置*/
<tr align = value> </tr>
```



<style type = "text/css">
    tr{align = "center";}
    td{align = "center";}
</style>
<table cellspacing = "0" border = "3" >
	<tr>
	<tr >
		<th align = "center">星期一</th>
		<th align = "center">星期二</th>
		<th align = "center">星期三</th>
	</tr>
	<tr align = "center">
		<td align = "center">张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
	<tr align = "center">
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
</table>



**代码**

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="gbk">

  <title>HTML课程</title>

<style type = "text/css">
   td{border:1px solid green;}
table{border :3px solid green;}
   	
</style>
</head>
<body>
<h1 style="text-align:center;color:red;" >值班</h1>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table align = center cellspacing = "0" border = "1" >
	<tr>
		<td>星期一</th>
		<td>星期二</th>
		<td>星期三</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
	<tr>
		<td>张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
</body>
</html>
```

  

**单元格<td>属性**

```
/*单元格水平跨距*/
<td clospan = value> </td>

/*单元格垂直跨距*/
<td rowspan = value> </td>

/*单元格背景颜色*/
<td bgcolor = value> </td>

/*单元格背景图片*/
<td background = value> </td>
```

<table cellspacing = "0" border = "3" >
	<tr >
		<th align = "center">星期一</th>
		<th align = "center">星期二</th>
		<th align = "center">星期三</th>
	</tr>
	<tr align = "center">
		<td align = "center" bgcolor = 'red' rowspan = "2">张三</th>
		<td>王华</th>
		<td>李明</th>
	</tr>
	<tr align = "center">
		<td>王华</th>
		<td>李明</th>
	</tr>
</table>

<br>



**只有外边框**

```html
<style type = "text/css">
table{border:5px solid red;}
</style>
/*table改为td:只有内边框*/
```



**表单对齐**

```
<div align = center>
</div>
```



