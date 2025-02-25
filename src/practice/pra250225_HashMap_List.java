package practice;

import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;

public class pra250225_HashMap_List {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] x = new long[2010];
        long[] y = new long[2010];

        HashMap<String, Integer> s = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            String t = x[i] + "," + y[i];
            s.put(t, s.getOrDefault(t, 0) + 1);
        }

        int ans=0;
        for (int i = 1; i <= n; i++) {
            HashMap<Long, ArrayList<Integer>> map = new HashMap<>();
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                long d = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                ArrayList<Integer>list=map.getOrDefault(d,new ArrayList<>());
                list.add(j);
                map.put(d,list);
            }
            for(long b:map.keySet()){
                ArrayList<Integer>list=map.get(b);
                int m=list.size();
                ans+=m*(m-1)/2;
                int c=0;
                for (int j:list) {
                    long xd=2*x[i]-x[j];
                    long yd=2*y[i]-y[j];
                    if(s.containsKey(xd+","+yd)){
                        c+=s.get(xd+","+yd);
                    }
                }
                ans-=c/2;
            }
        }
        System.out.println(ans);
        in.close();
    }
}
