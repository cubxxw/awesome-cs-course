package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

type CharCount struct {
	ChCount    int // 记录英文个数
	NumCount   int // 记录数字的个数
	SpaceCount int // 记录空格的个数
	OtherCount int // 记录其它字符的个数
}

func main() {

	var a int = 1
	for i := 1; i < 51; i++ {

		a1 := strconv.Itoa(a)
		a2 := strconv.Itoa((a + 1))
		a3 := strconv.Itoa((a - 1))
		filePath := "markdown/" + a1 + ".md"
		file, err := os.OpenFile(filePath, os.O_WRONLY|os.O_CREATE, 0666)
		//在原来的基础上追加666表示访问权限
		if err != nil {
			fmt.Println("文件打开失败", err)
		}
		//及时关闭file句柄
		defer file.Close()

		//写入文件时，使用带缓存的 *Writer
		write := bufio.NewWriter(file)
		write.WriteString("+ [author](https://github.com/3293172751)\n")
		write.WriteString("# 编译原理\n")

		//批量加入文件，

		write.WriteString("+ [回到目录](../README.md)\n")
		write.WriteString("+ [上一节](" + a3 + ".md)\n")
		write.WriteString("> ❤️💕💕我喜欢区块链,如果你也喜欢区块链,请关注博客[http://nsddd.top](http://nsddd.top/)\n")
		write.WriteString("--------------------------------\n")
		write.WriteString("[TOC]\n")
		for i := 0; i < 5; i++ {
			write.WriteString("\n")
		}
		write.WriteString("## END 链接\n")
		write.WriteString("+ [回到目录](../README.md)\n")
		write.WriteString("+ [上一节](" + a3 + ".md)\n")
		write.WriteString("+ [下一节](" + a2 + ".md)\n")
		write.WriteString("---\n")
		write.WriteString("###  **[其他理论课相关的笔记](https://github.com/3293172751/CS_COURSE)**\n")
		write.WriteString("+ [参与贡献❤️💕💕](https://github.com/3293172751/Block_Chain/blob/master/Git/git-contributor.md)")
		//Flush将缓存的文件真正写入到文件中
		write.Flush()
		a = a + 1
	}

}

// //思路: 打开一个文件, 创一个Reader
// //每读取一行，就去统计该行有多少个 英文、数字、空格和其他字符
// //然后将结果保存到一个结构体
// fileName := "1.md"
// file, err := os.Open(fileName)
// if err != nil {
// 	fmt.Printf("open file err=%v\n", err)
// 	return
// }
// defer file.Close()
// //定义个CharCount 实例
// var count CharCount
// //创建一个Reader
// reader := bufio.NewReader(file)

// //开始循环的读取fileName的内容
// for {
// 	str, err := reader.ReadString('\n')
// 	if err == io.EOF { //读到文件末尾就退出
// 		break
// 	}
// 	//遍历 str ，进行统计
// 	for _, v := range str {

// 		switch { //无项目，相当于分支结构
// 		case v >= 'a' && v <= 'z':
// 			fallthrough //穿透
// 		case v >= 'A' && v <= 'Z':
// 			count.ChCount++
// 		case v == ' ' || v == '\t':
// 			count.SpaceCount++
// 		case v >= '0' && v <= '9':
// 			count.NumCount++
// 		default:
// 			count.OtherCount++
// 		}
// 	}
// }

// //输出统计的结果看看是否正确
// fmt.Printf("字符的个数为=%v 数字的个数为=%v 空格的个数为=%v 其它字符个数=%v",
// 	count.ChCount, count.NumCount, count.SpaceCount, count.OtherCount)
