package poloy;

public class PloyParameter {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Manager m = new Manager("张三", 30, 5000); // 创建经理对象
        m.setBonus(5000);
        Worker w = new Worker("李四", 20, 3000);  // 创建工人对象

        //程序员工资
        System.out.println(m);  // 打印经理对象
        System.out.println(w);  // 打印工人对象
        System.out.println("经理的年工资是"+m.getSalary()); // 打印经理的年工资
        System.out.println("工人的年工资是"+w.getSalary()); // 打印工人的年工资
        System.out.println("经理的年工资是"+getSalary(m));  // 打印经理的年工资
        System.out.println("工人的年工资是"+getSalary(w)); // 打印工人的年工资
    }

    //实现获取任何员工的年工资，不管是经理还是工人，并在主方法中测试
    public static double getSalary(Employee e) {
        return e.getSalary(); // 调用Employee类的getSalary方法
    }

    //添加一个方法，testWork方法，可以测试任何员工的工作
    //向下转型
    public static void testWork(Employee e) {
        if(e instanceof Manager) {
            Manager m = (Manager) e;
            m.work();
            //也可以写成
            //((Manager) e).work();
        } else if(e instanceof Worker) {
            Worker w = (Worker) e;
            w.work();
        } else {
            System.out.println("输入的员工类型不正确");
        }
    }

}
