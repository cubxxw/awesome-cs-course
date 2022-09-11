/*
 * @Description: HashMap  -- javaé€Ÿç¯‡
 * @Author: xiongxinwei 3293172751nss@gmail.com
 * @Date: 2022-09-08 19:19:12
 * @LastEditTime: 2022-09-11 14:58:59
 * @FilePath: \code\project1\src\Main.java
 * @blog: https://nsddd.top
 */
// Import the HashMap class
import java.util.HashMap;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {

    // Create a HashMap object called people
    HashMap<String, Integer> people = new HashMap<String, Integer>();


    // Add keys and values (Name, Age)
    people.put("John", 32);
    people.put("Steve", 30);
    people.put("Angie", 33);

    for (String i : people.keySet()) {
      System.out.println("key: " + i + " value: " + people.get(i));
    }
    /* µü´úÆ÷s*/
    Iterator<Integer> it = people.values();
    System.out.println(it.next());
  }
}