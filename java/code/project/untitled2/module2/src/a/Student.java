package a;

public class Student {
    public String name = "王思聪";
    int age;
    String major;

    public Student(){

    }

    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    public void setName(String name) {
        System.out.println("吃饭");
    }
    public void setAge(int age) {
        System.out.println("学习");
    }
    void study(){
        System.out.println("学习");
    }
}
