package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class pra250323_2_SpecialSorting {
    public static void main(String[] args) {
        HashMap<String,Integer>map=new HashMap<>();
        TreeMap<Integer, ArrayList<Integer>>map2=new TreeMap<>();
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++) {
            a[i]=in.nextInt();
        }

        map.put("1", 0);
        map.put("2", 0);
        map.put("3", 0);
        map.put("4", 1);
        map.put("5", 0);
        map.put("6", 1);
        map.put("7", 0);
        map.put("8", 2);
        map.put("9", 1);
        map.put("0", 1);

        int Num;
        for(int i=0;i<n;i++) {
            String s=Integer.toString(a[i]);
            Num=0;
            for(int j=0;j<s.length();j++) {
                String num=s.charAt(j)+"";
                Num+=map.get(num);
            }
            if(!map2.containsKey(Num)) {
                map2.put(Num, new ArrayList<>());
                map2.get(Num).add(a[i]);
            }else {
                map2.get(Num).add(a[i]);
            }
        }
        for(int key:map2.keySet()) {
            map2.get(key).sort(null);
        }
        for(int key:map2.keySet()) {
            for(int k=0;k<map2.get(key).size();k++) {
                System.out.print(map2.get(key).get(k)+" ");
            }
        }
    }
}
