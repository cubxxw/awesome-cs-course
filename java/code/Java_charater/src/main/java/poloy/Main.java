package poloy;

public class Main {
    public static void main(String[] args) {
        Integer a1 = 18;
        int a2 = 18;
        System.out.println("a1="+a1+"\na2="+a2+ "\na1 == a2 : "+(a1 == a2));
        System.out.println("a1.equals(a2) : "+a1.equals(a2));

        String s1;
        System.out.println(s1="smile");
        /*
        1. 提高具有哈希结构的容器的效率
        2. 两个引用，如果指向的是同一个对象，则哈希值肯定是一样的
        3. 两个引用，如果指向的是不同对象，则哈希值是不一样的
        4. 两个引用，如果指向的是不同对象，但是哈希值是一样的，这种情况称为哈希冲突
        5. 哈希值是通过对象的hashCode()方法来计算的，如果两个对象的哈希值一样，但是equals()方法返回false，则称为哈希冲突
         */
    }
}
