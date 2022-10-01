package ppoly;

public class Son extends Father {
    /*爸爸和儿子都需要娶老婆*/
    public String name = "son";
    public Son(String name){
        super(name);
        this.name = name;
    }
    /*娶老婆方法*/
    public void Married(String name) {
        System.out.println("我的名字是："+name + "娶老婆");
    }
}
