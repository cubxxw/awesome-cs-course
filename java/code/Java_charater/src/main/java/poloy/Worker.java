package poloy;

public class Worker extends Employee {
    // 工人的工作
    public Worker(String name, int age, double salary) {
        super(name, age, salary);
    }

    public void work() {
        System.out.println("工人"+getName()+"要干活,年龄是"+getAge() +"薪资是"+getSalary());
    }


    public double getSalary() {
        //工人的工资是每月的工资*13，因为没有奖金，可以直接调用父类的方法
        return 13 * salary;  // 13个月
    }

    public String toString() {
        return "Worker [name=" + getName() + ", age=" + getAge() + ", salary=" + salary + "]";
    }


}
