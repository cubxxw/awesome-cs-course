package ppoly;

class Text {
    /*测试类*/
    public static void main(String[] args) {
        Main zhangsan = new Main("张三");  //创建主任类
        System.out.println("主任给小狗喂事物");
        Dog dog = new Dog("大黄");    //创建狗类
        Bone bone = new Bone("大棒骨\n");
        zhangsan.feed(dog,bone);

        System.out.println("主人给小猫喂事物");
        /*创建小猫*/
        Cat cat = new Cat("小花猫");
        Fish fish = new Fish("武昌鱼\n");
        zhangsan.feed(cat,fish);

        Father son = new Son("张三");
        son.Married("李四");
     //   System.out.println(super.);


        System.out.println("给小猪喂米饭");
        Pig pig = new Pig("小花猪");
        Rice rice = new Rice("主人吃剩下的米饭");
        zhangsan.feed(pig,rice);
    }
}