package Practice;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class pra241227 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=in.nextInt();
        }
        System.out.println(solution(n, arr));
    }
    public static int solution(int n,int arr[]){
        int arr1[]=new int[n];
        Arrays.sort(arr);
        int t=0;
        int m=1;
        for (int i = 0; i <n-1 ; i++) {
            if(arr[i]==arr[i+1]){
                arr1[t]++;
            }else{
                t=i+1;
                m++;
            }
        }
        ArrayList<Integer> list=new ArrayList<>();
        for (int num:arr1){
            list.add(num);
        }
        for(int num:arr1){
            list.remove("0");
        }
        int l=list.size();
        int arr2[]=new int[l];
        for (int i = 0; i < l; i++) {
            arr2[i]=list.get(i);
        }
        Arrays.sort(arr2);
        if(arr2[l-1]+1>n/2) {
            for (int i = 0; i < n; i++) {
                if(arr1[i]==arr2[l-1]){
                    int ans=arr[i];
                    return ans;
                }
            }
        }
        return 0;
    }

}
