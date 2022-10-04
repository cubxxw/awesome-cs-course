package Hashcode;

public class HashCode {
    public static void main(String[] args) {
        A2 a = new A2();
        A2 a2 = new A2();   // a2和a是不同的对象
        System.out.println(a.hashCode());  // 重写hashCode()方法后，输出的是对象的地址
        System.out.println(a2.hashCode()); // 重写hashCode()方法后，输出的是对象的地址

        A2 a3 = new A2();
        A2 a4 = a3;  // a3和a4是同一个对象
        System.out.println(a3.hashCode());  // 重写hashCode()方法后，输出的是对象的地址
        System.out.println(a4.hashCode());  // 重写hashCode()方法后，输出的是对象的地址
    }
}
class  A {

}