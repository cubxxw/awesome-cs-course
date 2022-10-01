package ppoly.object;

public class PolyObject {
    public static void main(String[] args) {
        /*多态的特点：*/
        Animal animal = new Dog();  //编译类型Animal，运行类型是Dog
        animal.cry();   //因为运行类型是Dog，所以运行到此时，cay是Dog的cry -- Dog cty() 小狗再叫

        //运行类型变化一下,此时运行类型变化成为Cat
        animal = new Cat();
        animal.cry();   //运行类型变了 --- Cat cry() 方法 小猫叫

        //再发生变化
        animal = new Animal();
        animal.cry();   //运行类型又变了 --- Animal 里面的 动物在叫
    }
}
