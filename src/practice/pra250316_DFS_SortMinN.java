package practice;
import java.util.Scanner;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class pra250316_DFS_SortMinN {

    public static int n,m;
    public static int[] arr;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        arr = new int[n+2];
        dfs(1,1);
        scan.close();
    }

    public static void dfs(int x,int start){
        if(x > m){
            for(int i = 1;i<=m;i++){
                System.out.printf(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i =start;i<=n;i++){
            arr[x] = i; //i指当前选完的数
            dfs(x+1,i+1); //从当前选完的数+1开始
            arr[i] = 0;
        }
    }

}