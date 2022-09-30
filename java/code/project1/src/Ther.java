/*
 * @Description: 运行线程
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-11 15:45:47
 * @LastEditTime: 2022-09-11 15:57:55
 * @FilePath: \code\project1\src\Ther.java
 * @blog: https://nsddd.top
 */
public class Ther extends Thread {
    public static void main(String[] args) {
      Ther thread = new Ther();
      thread.start();	//运行线程
      for (int i = 0; i < 10; i++) {
        System.out.println("This code is outside of the thread");
        sleep(1);
      }
    }
    public void run() {
     for (int i = 0; i < 10; i++) {
        System.out.println("This code is running in a thread");
      }
    }
  }