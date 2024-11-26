package calculate;

import java.util.Arrays;

public class sorting240912fast{
    public static void main(String[] args){
        int[] arr={1,2,6,4,3,0,9,7,8};
        System.out.println(Arrays.toString(arr));
        System.out.println();
        //执行快速排序
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr,int start,int end){
        int pivot=arr[end];
        int x=start-1;
        for (int i =start; i <end ; i++) {//实现左右分区域
            if (arr[i]<=pivot){
                if(i-x>1){
                    int temp=arr[i];
                    arr[i]=arr[x+1];
                    arr[x+1]=temp;
                    x++;
                }else {
                    x=i;
                }
            }
        }
        if (pivot<arr[x+1]){//移动枢纽元
            arr[end]=arr[x+1];
            arr[x+1]=pivot;
        }
        if(x-start>0){//左侧快排
            quickSort(arr,start,x);
        }
        if(end-x-1>1){//右侧快排
            quickSort(arr,x+1,end);
        }
    }
}


















