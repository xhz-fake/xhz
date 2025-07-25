package Practice;

import java.util.Arrays;
import java.util.Scanner;

public class pra241003 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr =new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=scanner.nextInt();
        }
        dealone(arr);
        System.out.println(Arrays.toString(arr));
        scanner.close();
    }
    public static void dealone(int[] arr){
        int n=arr.length;
        int count=0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1){
                arr[i]=0;
                count++;
            }
        }
        int index=0;
        for (int i = 0; i < n; i++) {
            if(arr[i]!=0){
                arr[index++]=arr[i];
            }
        }
        while (index<n){
            arr[index++]=0;
        }
    }

}