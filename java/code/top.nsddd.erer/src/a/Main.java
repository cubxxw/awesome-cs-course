package a;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ManKind a = new ManKind();
        ManKind b = new ManKind(21,123);
        System.out.println(b.toString());
        System.out.println(a.toString());

        System.out.println("调试程序");
        int i = 0;
        int j = 0;
        i = i + 1;
        j = j + 1;
        j = j + 1;
        i = i + j;
        int x = max(i, j);
    }
}