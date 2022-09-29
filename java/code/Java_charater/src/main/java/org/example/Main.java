package org.example;

public class Main extends Person{
    public int a = 13;
    public Main() {   // 子类也写一个构造器
        super(12);
        System.out.println("调用子类构造器");
    }

    public Main(String name) {
        super(23,12,32,42);
        this.a = 12;
        System.out.println("调用之类Main(String name) 构造器");
    }
    public static void main(String[] args) {
        Main m = new Main();
        System.out.println("m.n3 = "+m.n3);
        //再创建一个构造器
        Main m2 = new Main("xiongxinwei");
        System.out.println("a = "+ m2.a);
        System.out.println("m.n3 = "+m2.n3);
        Person person = new Person(13);
        System.out.println(person.getN1());
    }
}