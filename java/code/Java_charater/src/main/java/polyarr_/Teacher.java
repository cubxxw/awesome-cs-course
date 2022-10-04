package polyarr_;

public class Teacher extends Person {
    private double salary;  //薪水
    public Teacher(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //重写父类的toString方法  -- 从父类开始查找
    public String toString() {
        return super.toString() + ", salary=" + salary;
    }

    //特有方法
    public void teach() {
        System.out.println("姓名：" + getName() + "，年龄：" + getAge() + "，薪水：" + salary + "，在教书");
    }

}
