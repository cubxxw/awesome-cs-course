import javax.print.event.PrintJobListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.xml.stream.events.StartElement;

/*
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-03 10:56:41
 * @LastEditors: xiongxinwei 3293172751nss@gmail.com
 * @LastEditTime: 2022-09-03 11:06:38
 * @FilePath: \code\2.java
 * @Description: 
 */
class Aa {
    static int add(int a, int b) {
        Math.pow(a, b);
        System.out.println("求幂= "+Math.pow(a, b));
        return a + b;
    }
    public static void main(String[] args) {
        System.out.println("hello word 你好");
        int c = add(2,3);
        System.out.println("求和 c =  "+c);
    }
}