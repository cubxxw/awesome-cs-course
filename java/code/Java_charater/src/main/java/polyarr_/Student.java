package polyarr_;

public class Student extends Person {
    private double score;
    public Student(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    //重写父类的toString方法  -- 从父类开始查找
    public String toString() {
        return super.toString() + ", score=" + score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    //特有方法
    public void study() {
        System.out.println("姓名：" + getName() + "，年龄：" + getAge() + "，成绩：" + score + "，在学习");
    }
}
