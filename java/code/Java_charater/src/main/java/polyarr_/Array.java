package polyarr_;

public class Array {
    public static void main(String[] args) {
        Person[] persons = new Person[5];
        persons[0] = new Person("张三", 18);
        persons[1] = new Teacher("李四", 28, 12.132);  //老师
        persons[2] = new Student("王五", 38, 123.2);  //学生
        persons[3] = new Teacher("赵六", 48,132.41);
        persons[4] = new Student("田七", 58,421.232);

        for (int i = 0; i < persons.length; i++) {
            //persons[i]的编译类型是Person，运行类型是Person、Student或Teacher,根据实际情况Java虚拟机会调用相应的方法
            System.out.println(persons[i]); //这里会有一个动态绑定机制
            if (persons[i] instanceof Teacher) {
                //向下转型: 等同于Teacher t = (Teacher)persons[i]; t.teach();
                ((Teacher) persons[i]).teach();
            } else if (persons[i] instanceof Student) {
                //向下转型: 等同于Student s = (Student)persons[i]; s.study();
                ((Student) persons[i]).study();
            } else if (persons[i] instanceof Person) {
                System.out.println("这是一个普通人");
             }else {
                System.out.println("不是老师也不是学生，也不是人,你的类型有问题");
            }
        }
    }
}
