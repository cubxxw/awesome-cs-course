package poloy;

public class Manager extends Employee {
    // 经理的工作
    private double bonus;   //奖金

    public Manager(String name, int age, double salary) {
        super(name, age, salary);
    }

    public void work() {
        System.out.println("经理"+getName()+"要管理,年龄是"+getAge() +"薪资是"+getSalary());
    }

    public double getSalary() {
        //经理的工资是每月的工资*14，但是经理有奖金，所以要加上奖金
        return super.getSalary() + bonus * 14 ;  // 14个月
    }

    // 奖金的get和set方法
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String toString() {
        return "Manager [name=" + getName() + ", age=" + getAge() + ", salary=" + salary + ", bonus=" + bonus + "]";
    }
}
