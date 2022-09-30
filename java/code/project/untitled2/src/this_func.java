public class this_func {
    /**
     * @param args
     */
    public static void main(String[] args) {
        T t = new T();
        // t.f1();
        //t.f2();
    }
}

class T {
    public T(){
        //访问另外一个构造器  -- 必须要是第一行
        this("name",100);
        System.out.println("T()构造器");
    }

    public T(String name,int age) {
        System.out.println("T(String name,int age)构造器");
    }
    public void f1(){
        System.out.println("f1() 方法....");
    }
    
    public void f2(){
        // 第二种方式
        this.f1();
        System.out.println("f2() 方法....");
        // 第一种方式调用
        f1();
    }
}