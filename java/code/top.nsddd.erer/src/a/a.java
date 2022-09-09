/*
 * @Description: 
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-08 14:35:45
 * @LastEditTime: 2022-09-08 14:36:22
 * @FilePath: \code\top.nsddd.erer\src\a\a.java
 * @blog: https://nsddd.top
 */
interface FirstInterface {
  public void myMethod(); // interface method
}

interface SecondInterface {
  public void myOtherMethod(); // interface method
  public void a(int a); 	
}

// DemoClass "implements" FirstInterface and SecondInterface
class DemoClass implements FirstInterface, SecondInterface {
  public void myMethod() {
    System.out.println("Some text..");
  }
  public void myOtherMethod() {
    System.out.println("Some other text...");
  }
  public void a(int a){
  	 System.out.println("A = "+a);
  }  
}

class a {
  public static void main(String[] args) {
    DemoClass myObj = new DemoClass();
    myObj.myMethod();    //Some text..
    myObj.myOtherMethod();   //Some other text...
    
    //上转型
    FirstInterface my1 = new DemoClass();
    my1.myMethod();   //Some text..
    //my1.myOtherMethod();	FirstInterfacec创建的上转型不能用SecondInterface接口方法 
    
    SecondInterface my2 = new DemoClass();
    //my2.myMethod();  SecondInterface创建的上转型不能用 FirstInterfacec接口方法 
    my2.myOtherMethod();  //SecondInterface
    my2.a(1000);  //1000
  }
}