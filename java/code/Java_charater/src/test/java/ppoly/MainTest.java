package ppoly;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static void main(String[] args) {
        Main zhangsan = new Main("张三");  //创建主任类
        Dog dog = new Dog("大黄");    //创建狗类
        Bone bone = new Bone("大棒骨");
        zhangsan.feed(dog,bone);
    }
}