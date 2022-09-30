/*
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-03 11:12:12
 * @LastEditors: xiongxinwei 3293172751nss@gmail.com
 * @LastEditTime: 2022-09-03 11:24:43
 * @FilePath: \code\packages\Bb.java
 * @Description: 
 * 
 * 
 */
package packages;

public class Bb {
        public static int add(int a, int b) {
            System.out.println("package B 包");
            Math.pow(a, b);
            System.out.println("求幂= "+Math.pow(a, b));
            return a + b;
        }
}
