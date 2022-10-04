package Hashcode;

import polyarr_.Array;

import java.util.Arrays;

public class Debug {
    //生成调试案例
    public static void main(String[] args) {
        int[] arr = {1,4,2,9,5,7,3,6,8};
        //设置断点方法
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
