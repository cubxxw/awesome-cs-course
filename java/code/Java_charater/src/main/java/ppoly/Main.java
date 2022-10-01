package ppoly;

/**
 * Main是master主人类
 */
public class Main {
    private  String name;

    public Main(String name) {
        this.name  =  name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /*使用多态机制，统一管理主人喂食物问题*/
    public void feed(Animal animal,Food food) {
        //直接用父类来管理
        System.out.println("主人 " + name + "给" + animal.getName()+"吃"+food.getName());
    }

    //主任给小狗喂食物 bone
    public void feed(Dog dog,Bone bone) {
        System.out.println("主人 " + name + "给" + dog.getName()+"吃"+bone.getName());
    }

    /*主任给小猫喂黄花鱼*/
    public void feed(Cat cat,Fish fish) {
        /*构成方法的重载*/
        System.out.println("主人 " + name + "给" + cat.getName()+"吃"+fish.getName());
    }
 }
