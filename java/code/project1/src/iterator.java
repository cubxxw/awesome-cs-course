/*
 * @Description: 
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-11 15:01:15
 * @LastEditTime: 2022-09-11 15:01:50
 * @FilePath: \code\project1\src\iterator.java
 * @blog: https://nsddd.top
 */
import java.util.ArrayList;
import java.util.Iterator;
public class iterator {
   public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(12);
    numbers.add(8);
    numbers.add(2);
    numbers.add(23);
    Iterator<Integer> it = numbers.iterator();
    while(it.hasNext()) {  //12,8,2,23
      Integer i = it.next();
      if(i < 10) {
        it.remove();  //移除小于10
      }
    }
    System.out.println(numbers);
   } 
}
