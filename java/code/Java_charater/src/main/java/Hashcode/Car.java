package Hashcode;

public class Car {
    public static void main(String[] args) {
        A3 a3 = new A3("宝马");
        a3 = null;   // a3指向的对象没有引用指向，会被垃圾回收器回收
        //null和0的区别：null是一个引用，0是一个值
        System.gc();  // 手动调用垃圾回收器

        //finalize()方法是在垃圾回收器回收对象之前调用的
        //finalize()方法是Object类中的方法，所有的类都继承了Object类

        System.out.println(a3+"程序结束");
    }
}

class A3 {
    private String name;

    public A3(String name) {
        this.name = name;
    }

    //重写finalize()方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收器回收了"+name+"对象");
    }
}
