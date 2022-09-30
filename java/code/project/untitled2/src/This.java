public class This {
    public static void main(String[] args) {
        Dog dog = new Dog("张三",20);
        //Dog输出hashCode()
        System.out.println("dog.hashCode = "+dog.hashCode());
        
    }
}

class Dog {
    String name;
    int age;
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
        //this输出的hashCode()
        System.out.println("this.hashCode = " + this.hashCode());
    }
}
