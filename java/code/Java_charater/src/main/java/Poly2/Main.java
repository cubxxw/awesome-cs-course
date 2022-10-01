package Poly2;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal obj = new Obj();
        Animal cat = new Cat();
        animal.animal01();
        obj.animal01();
        //obj.obj01();  注意不能调用，因为编译类型是Animal,定义的一瞬间确定了，所以它的方法没有后面的
        cat.animal01();
        //cat.cat01();

        Cat cat2 = new Cat();
        cat2.animal01();
        cat2.cat01();
        cat2.obj01();   //可以调用，而且cat往上找

        //能往上找多少？  -- 自己--> 往上给我趴
        cat2.show();

        Obj obj2 = new Cat();  //注意这个时候能找出来的是自己的Obj对象方法
        obj2.animal01();
        obj2.obj01();
        //obj2,cat2(); 不可以，编译类型确定后没有这个对象
        obj2.show();

        animal.animal01();  //可以
        animal.show();
        //animal.obj01(); //不可以
       // animal = new Cat();
        animal.show();

        Cat newCat = (Cat) animal;
        newCat.animal01();
        newCat.obj01();
        newCat.cat01();
        newCat.show();
        /*下转型 -- 就可以使用所有的方法了*/
    }
}

class Animal {
    String name = "animal";
    public  void animal01(){
        System.out.println("Animal的animal01方法");
    }
    public void show() {
        System.out.println("姓名是："+name+"\n");
    }
}

class Obj extends Animal {
    String name = "obj";
    public void obj01(){
        System.out.println("Obj的obj01方法");
    }
    public void show() {
        System.out.println("姓名是："+name+"\n");
    }
}

class  Cat extends Obj {
    String name = "cat";
    public void cat01(){
        System.out.println("Cat01方法");
    }
    public void show() {
        System.out.println("姓名是："+name+"\n");
    }
}