package a;

public class Main extends Student {
    public String name = "王一博";
    public Main() {
    }

    public Main(String name, int age, String major) {
        super(name, age, major);
    }
    public  void setName(String name){
        this.name = name;
    }

//    public static void main(String[] args) {}

}