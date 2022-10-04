package poloy;

public class Employee {
    public String name;
    public int age;
    public double salary;   //工资薪水


    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


    // 得到年工资的方法
    public double getSalary() {
        //有的是十二薪，有的是十三薪，有的是十四薪 方法不同，但是功能是一样的
        return 12 * salary;  // 12个月
    }

    // 修改工资的方法
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Employee [name=" + name + ", age=" + age + "]";
    }

}
