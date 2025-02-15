package practice;

import java.util.Arrays;
import java.util.Scanner;

public class pra1004 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = {1, 4, 5, 2, 7, 8, 4, 6, 9, 6, 2, 4, 3, 7, 8,17,13,26,1,4,12,14,11};
        System.out.println(Arrays.toString(arr));
        booblesort(arr);
        System.out.println(Arrays.toString(arr));
        findsecondbig(arr);
        findkthbig(arr);
    }
    public static void booblesort(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }    public static void findkthbig(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        int k = 4;
        for (int q = 0; q < k-2; q++) {
            int m = arr[arr.length - 1];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == m) {
                    arr[i] = 0;
                }
            }
            for (int j = 0; j < arr.length; j++) {
                for (int i = 0; i < arr.length - 1; i++) {
                    if (arr[i] > arr[i + 1]) {
                        int temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                    }
                }
            }
        }
        int ktnnum=arr[arr.length-1];
        System.out.println("第4大的数字是："+ktnnum);
    }

    public static void findsecondbig(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        int n = arr[arr.length - 1];
        arr[arr.length - 1] = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                arr[i] = 0;
            }
        }
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        int secondbignum = arr[arr.length - 1];
        System.out.println("本数组中第二大的数是：" + secondbignum);
    }
}
