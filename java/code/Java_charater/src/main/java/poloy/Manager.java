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
/*
object类的方法详解
1、`toString()`方法：返回对象的字符串表示，通常是对象的属性值，如：Person [name=张三, age=20, salary=3000.0]，如果没有重写该方法，返回的是对象的内存地址，如：poloy.Person@15db9742
2、`equals()`方法：比较两个对象是否相等，如果没有重写该方法，比较的是两个对象的内存地址是否相等，如：false,如果重写了该方法，比较的是两个对象的属性值是否相等，如：true
equals()方法面试问题：String类重写了equals()方法，比较的是两个字符串的内容是否相等，如：tru
3、`hashCode()`方法：返回对象的哈希码值，如果没有重写该方法，返回的是对象的内存地址的哈希码值，如：366712642，如果重写了该方法，返回的是对象的属性值的哈希码值，如：-2128831035
4、`getClass()`方法：返回对象的运行时类，如：class poloy.Person,如果没有重写该方法，返回的是对象的运行时类，如：class poloy.Person
5、`clone()`方法：克隆对象，如果没有重写该方法，返回的是对象的内存地址，如：poloy.Person@6d06d69c，如果重写了该方法，返回的是对象的属性值，如：Person [name=张三, age=20, salary=3000.0]
6、`finalize()`方法：垃圾回收器在回收对象之前调用该方法，如果没有重写该方法，返回的是对象的内存地址，如：poloy.Person@7852e922，如果重写了该方法，返回的是对象的属性值，如：Person [name=张三, age=20, salary=3000.0]
*/

