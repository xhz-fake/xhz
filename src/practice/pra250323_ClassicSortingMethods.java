package practice;

import java.util.Scanner;

public class pra250323_ClassicSortingMethods {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=in.nextInt();
        }
        insertSort(arr,n);
        System.out.println();

        bubbleSort(arr,n);
        System.out.println();

        selectSort(arr,n);
        System.out.println();
        in.close();
    }
    static void bubbleSort(int[] arr,int n){
        boolean swap;
        for (int i = 0; i < n; i++) {
            swap=false;
            for(int j=1;j<n-i;j++){
                if(arr[i]>arr[j]){
                    int tem=arr[i];
                    arr[i]=arr[j];
                    arr[j]=tem;
                    swap=true;
                }
            }
            if(!swap){
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    static void insertSort(int[] arr1,int n){
        for (int i = 1; i < n; i++) {
            int key=arr1[i];
            int j=i-1;
            while(j>=0 && arr1[j]>key){
                arr1[1+j]=arr1[j];
                --j;
            }
            arr1[1+j]=key;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr1[i]+" ");
        }
    }

    static void selectSort(int[] arr, int n){
        for (int i = 0; i < n; i++) {
            int p=i;
            for (int j = i; j < n; j++) {
                if(arr[j]<arr[p]){
                    p=j;
                }
            }
            int tem=arr[p];
            arr[p]=arr[i];
            arr[i]=tem;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }
    }


}