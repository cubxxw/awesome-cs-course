package ppoly.object;

public class Dog extends Animal {
    /*也是重写父类cry*/

    @Override
    public void cry() {
        System.out.println("Dog cty() 小狗再叫");
    }
}
