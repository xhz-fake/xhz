package practice;

import java.util.TreeMap;
import java.io.*;
import java.util.*;

public class pra250227 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int []a=new int[n+10];
        TreeMap<Integer,Integer>treemap=new TreeMap<>();
        for(int i=1;i<=n;i++){
            a[i]=in.nextInt();
            treemap.put(a[i],0);
        }
        int cnt=1;
        for(int key:treemap.keySet()){
            treemap.put(key,cnt);
            cnt=cnt+1;
        }
        for(int i=1;i<=n;i++){
            System.out.print(treemap.get(a[i])+" ");
        }
        in.close();
    }
}