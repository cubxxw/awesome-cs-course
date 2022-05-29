# 数学公式

[toc]

公式块与行内公式的添加
-----------

### 1. 公式块

*   **创建独立的一块公式区域**。

![](https://s2.loli.net/2022/04/06/IZFm7ngAQrY19oM.png)

*   上部分为公式输入区
*   下部分为效果展示区

![](https://s2.loli.net/2022/04/06/XZQKywqpGx3DE2j.png)

编辑别处时展示效果图。

**方法一**：左上角点击 “段落”，再点击 “公式块”

**方法一**：在文中输入 $$，再按下回车

### 2. 行内公式

*   **将公式嵌入文字内**。

![](https://s2.loli.net/2022/04/06/c6JYlPW8KqfQuor.png)

**方法一**： 在 $$ 的中间加入需要的公式

**简便的方法一**：先按 $ ，再按 “esc”（键盘左上角）

![](https://s2.loli.net/2022/04/06/Ey6Kb5oW1zTgdZp.jpg)

（行内公式是需要先设置一下）

常用符号的代码
-------

*   上下标，正负无穷
*   加减乘，分式，根号，省略号
*   三角函数
*   矢量，累加累乘，极限
*   希腊字母

### **1. 上下标，正负无穷**

![](https://s2.loli.net/2022/04/06/BnNafmjuIAxrWLK.png)

### **2. 加减乘，分式，根号，省略号**

![](https://s2.loli.net/2022/04/06/etc4w7P2SCjBDQI.png)

### **3. 三角函数**

![](https://s2.loli.net/2022/04/06/ETVM69rQcNjiXtl.png)

### **4. 矢量，累加累乘，极限**

![](https://s2.loli.net/2022/04/06/NtiW1JPsYg24T5k.png)

### **5. 希腊字母**

![](https://s2.loli.net/2022/04/06/u9GnSiaeWNzojs2.png)

### **6. 关系运算符**

![](https://s2.loli.net/2022/04/06/yPXcE5Fh7f8LGWq.png)

**矩阵**
------

### **1. 简单矩阵**

使用`\begin{matrix}…\end{matrix}`生成， 每一行以`\\`结尾表示换行，元素间以`&`间隔，式子的表示序号`\tag{1}`（右边的序号）。

$$
\begin{matrix}
 1 & 2 & 3 \\
 4 & 5 & 6 \\
 7 & 8 & 9 
\end{matrix} \tag{1}
$$

```
$$
\begin{matrix}
 1 & 2 & 3 \\
 4 & 5 & 6 \\
 7 & 8 & 9 
\end{matrix} \tag{1}
$$
```

### **2. 带左右括号的矩阵 (大中小括号)**

**方法一**：在`\begin{}`之前和`\end{}`之后添加左右括号的代码。

大括号：

$$
\left\{
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right\} \tag{2}
$$


```
$$
 \left\{
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right\} \tag{2}
$$
```

中括号：

$$
\left[
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right] \tag{3}
$$

```
$$
 \left[
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right] \tag{3}
$$
```

小括号：

$$
\left(
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right) \tag{4}
$$

```
$$
 \left(
 \begin{matrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{matrix}
  \right) \tag{4}
$$
```

**方法二**：改变`\begin{matrix}`和`\end{matrix}`中`{matrix}`

大括号：

$$
\begin{Bmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{Bmatrix} \tag{6}
$$

```
$$
 \begin{Bmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{Bmatrix} \tag{6}
$$
```

中括号：

$$
\begin{bmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{bmatrix} \tag{6}
$$

```
$$
 \begin{bmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{bmatrix} \tag{6}
$$
```

### **3. 包含希腊字母与省略号**

行省略号`\cdots`，列省略号`\vdots`，斜向省略号（左上至右下）`\ddots`。

$$
\left\{
 \begin{matrix}
 1      & 2        & \cdots & 5        \\
 6      & 7        & \cdots & 10       \\
 \vdots & \vdots   & \ddots & \vdots   \\
 \alpha & \alpha+1 & \cdots & \alpha+4 
 \end{matrix}
 \right\}
$$

```
$$
 \left\{
 \begin{matrix}
 1      & 2        & \cdots & 5        \\
 6      & 7        & \cdots & 10       \\
 \vdots & \vdots   & \ddots & \vdots   \\
 \alpha & \alpha+1 & \cdots & \alpha+4 
 \end{matrix}
 \right\}
$$
```

公式序号
----

见 “矩阵” 小节，代码最后的一行即表示右端序号

```
......
\tag{6}
```

行列式
---

行列式相关语法与矩阵类似

$$
\begin{vmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{vmatrix}
\tag{7}
$$

```
$$
 \begin{vmatrix}
   1 & 2 & 3 \\
   4 & 5 & 6 \\
   7 & 8 & 9
  \end{vmatrix}
\tag{7}
$$
```

表格
--

### **1. 简易表格**
$$
\begin{array}{|c|c|c|}
	\hline 2&9&4\\
	\hline 7&5&3\\
	\hline 6&1&8\\
	\hline
\end{array}
$$

```
$$
\begin{array}{|c|c|c|}
	\hline 2&9&4\\
	\hline 7&5&3\\
	\hline 6&1&8\\
	\hline
\end{array}
$$
```

**开头结尾**： `\begin{array}` ， `\end{array}`

**定义式**：例：`{|c|c|c|}`，其中`c` `l` `r` 分别代表居中、左对齐及右对齐。

**分割线**：①**竖直分割线**：在定义式中插入 `|`， （`||`表示两条竖直分割线）。

②**水平分割线**：在下一行输入前插入 `\hline`，以下图真值表为例。

其他：每行元素间均须要插入 `&` ，每行元素以 `\\` 结尾。

### **2.. 真值表**

$$
\begin{array}{cc|c}
	       A&B&F\\
	\hline 0&0&0\\
	       0&1&1\\
	       1&0&1\\
	       1&1&1\\
\end{array}
$$

```
$$
\begin{array}{cc|c}
	       A&B&F\\
	\hline 0&0&0\\
	       0&1&1\\
	       1&0&1\\
	       1&1&1\\
\end{array}
$$
```

**多行等式对齐**
----------

$$
\begin{aligned}
a &= b + c \\
  &= d + e + f
\end{aligned}
$$

```
$$
\begin{aligned}
a &= b + c \\
  &= d + e + f
\end{aligned}
$$
```

**方程组、条件表达式**
-------------

方程组：

$$
\begin{cases}
3x + 5y +  z \\
7x - 2y + 4z \\
-6x + 3y + 2z
\end{cases}
$$

```
$$
\begin{cases}
3x + 5y +  z \\
7x - 2y + 4z \\
-6x + 3y + 2z
\end{cases}
$$
```

同理，条件表达式：

$$
f(n) =
\begin{cases} 
n/2,  & \text{if }n\text{ is even} \\
3n+1, & \text{if }n\text{ is odd}
\end{cases}
$$

```
$$
f(n) =
\begin{cases} 
n/2,  & \text{if }n\text{ is even} \\
3n+1, & \text{if }n\text{ is odd}
\end{cases}
$$
```

**间隔 (大小空格、紧贴)**
----------------

**紧贴 + 无空格 + 小空格 + 中空格 + 大空格 + 真空格 + 双真空格**

```
$$
a\!b + ab + a\,b + a\;b + a\ b + a\quad b + a\qquad b
$$
```

紧贴`\!`

无空格 小空格`\,` 中空格`\;` 大空格`\`

真空格`\quad` 双真空格`\qquad`

通过 Python 生成 LaTeX 表达式
----------------------

### **step1：安装 latexify-py 模块**

### **step2：编写代码**

```
import math				//引入数学模块(有些运算的函数需要)
import latexify			//引入latexify模块

@latexify.with_latex	//特定语法，表示之后定义的函数可以转化为LaTeX代码
def f(x,y,z):		    //包含的参数
    pass			   //此处填写可能需要的数学表达式
    return result		//也可以直接体现数学关系

print(f)			   //直接print(函数名)
```

### **step3：在输出区得到需要的 LaTeX 数学表达式**

**特别说明**，生成的表达式为**定义式**，即 ，如果只需要等式 ，可以把生成表达式中的`\triangleq`改成`=` ！

更多细节和实例可以浏览我新的文章：

