package Poly2;

public class Main2 {
    public static void main(String[] args) {
        Base base = new Sub();  //上转型对象

        System.out.println("count = " + base.count);
        System.out.println("getCount = " + base.getCount());

        /*判断InstanceOf*/
        System.out.println(base instanceof Sub);
        System.out.println(base instanceof Base);

        //改变运行状态
        base = new Base();
        System.out.println(base instanceof Sub);
        System.out.println(base instanceof Base);

        //下转型
        Base base2 = (Base) base;
        System.out.println(base2 instanceof Sub);
        System.out.println(base2 instanceof Base);
    }
}

class Base {
    //父类
    int count = 10;
    public int getCount() {
        return this.count;
    }
}

class Sub extends Base {
    //子类
    int count = 20;
    public int getCount() {
        return this.count;
    }
}