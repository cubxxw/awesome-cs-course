package java_object_theory;

/**
 * 讲解继承的本质
 */
public class ExtendsTheory {
    public static void main(String[] args) {
        //我们创建一个儿子类，内存发生了什么？？？
        Son son = new Son();
        System.out.println("son-name = "+son.name);
        System.out.println("son-age = "+son.age);
        // 最先加载的是最父类object --> grandpa --> father --> son

    }
}

/**
 * 老祖类
 */
class GrandPa {
    String name = "张三";
    int age = 48;
}

/**
 * 父亲类
 */
class Father extends GrandPa {
    String name = "xiongxinwei";
    int age = 20;
}

/**
 * 孙子类  -- 注意孙子类和爷爷名字一样
 */
class Son extends Father  {
    String name = "张三";
    int age = 2;
}
