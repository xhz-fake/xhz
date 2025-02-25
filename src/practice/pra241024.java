package practice;
import java.util.Scanner;
import java.util.Arrays;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class pra241024 {
    public static void main(String[] args) {
      Scanner scan =new Scanner(System.in);
      int n=scan.nextInt();
      scan.nextLine();
      int[]arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]= scan.nextInt();
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }
      scan.close();
    }
}
