/*
 * @Description: file 
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-11 17:00:59
 * @LastEditTime: 2022-09-11 17:07:33
 * @FilePath: \code\nsddd\Main.java
 * @blog: https://nsddd.top
 */
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.IOException;  // Import the IOException class to handle errors

public class Main {
  public static void main(String[] args) {
    try {
      File myObj = new File("filename.txt");  //创建一个文件
      if (myObj.createNewFile()) {  //如果创建成功
        System.out.println("File created: " + myObj.getName());  //获取文件名称
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {   //创建失败
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    //写入文件
    try {
        //FileWriter myObj = new FileWriter("filename.txt");
        myObj.write("Files in Java might be tricky, but it is fun enough!");
        myObj.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }
}