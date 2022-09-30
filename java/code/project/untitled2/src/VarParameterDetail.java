public class VarParameterDetail {
    public static void main(String[] args) {
        int[] arr = {1,2,4};
        T t = new T();
        t.f1(arr);
    }
}

class T {
    public  void f1(int... nums) {
        System.out.println("长度 = " + nums.length);
    }
}