package a;

public class person {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("John");
        System.out.println("name = " + student.name);
        student.setAge(12);
        student.study();

        // 继承
        Main main = new Main();
//        main.setName("John");
        System.out.println("name = " + main.name);
        main.setAge(12);
        main.study();
    }
}
