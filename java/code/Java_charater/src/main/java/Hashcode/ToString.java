package Hashcode;

public class ToString {
    public static void main(String[] args) {
        A2 a = new A2();
        System.out.println(a.toString());  // 重写toString()方法后，输出的是对象的地址
        System.out.println(a.hashCode());  // 重写hashCode()方法后，输出的是对象的地址
     }
}

class A2 {
    @Override
    public String toString() {
        return getClass().getName() + "@nsddd.top" + Integer.toHexString(hashCode());
    }
}
