public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person();
        p1.name = "milan";
        p1.age = 14;
        
        MyTools tools = new MyTools();
        Person p2 = tools.copyPerson(p1);
        
        // 输入p的属性
        System.out.println("p1的属性name =" + p1.name + "p1.age=" + p1.age);
        System.out.println("p2的属性name =" + p2.name + "p2.age=" + p2.age);
        //判断是否相等
        System.out.println(p1 == p2);
        //修改p2属性

    }
}

class Person {
    String name;
    int age;
}

class MyTools{
    public Person copyPerson(Person p1) {
        //1. 注意函数的返回类型和传入类型 -- 是Person
        //2. 方法的名字
        //3. 方法的形参 --- 可以赋值Person：形参就是Person
        //4. 方法体 -- 创建一个新对象，并且赋值属性即可
        Person p2 = new Person();
        p2.name = p1.name;  //原来对象名字赋值给
        p2.age = p1.age;
    	return p2;  
    }
}

