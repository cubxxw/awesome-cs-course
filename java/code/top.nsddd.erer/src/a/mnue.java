/*
 * @Description: 
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-08 14:41:39
 * @LastEditTime: 2022-09-08 14:59:30
 * @FilePath: \code\top.nsddd.erer\src\a\mnue.java
 * @blog: https://nsddd.top
 */
import java.nio.channels.NetworkChannel;
import java.util.Scanner;

enum Level {
    LOW,
    MEDIUM,
    HIGH
  }
  
enum xb {
    男,
    女
}  
  public class mnue { 
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      Level myVar = Level.MEDIUM; 
                  
      switch(myVar) {
        case LOW:
          System.out.println("Low level");
          break;
        case MEDIUM:
          System.out.println("Medium level");
          break;
        case HIGH:
          System.out.println("High level");
          break;
      }

      System.out.println("请输入你的性别");
      String input = in.nextLine();
      System.out.println("x = "+x);
      xb xb1 = xb.女;
      xb xb2 = xb.男;
      switch (input) {
        case xb1:
            System.out.println("女生");
            break;
      
        case xb2:
            System.out.println("男生");
            break;
        default:
            System.out.println("输入错误");
            break;
      }

    }
  }
  
  