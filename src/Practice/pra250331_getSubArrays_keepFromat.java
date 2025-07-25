package Practice;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class pra250331_getSubArrays_keepFromat {
    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n=in.nextInt();
        int k=in.nextInt();
        int a[]=new int[n+1];//让a的最后一项是0，便于后续的获取子数列操作

        ArrayList<Integer>list=new ArrayList<>();
        ArrayList<int[]>list1=new ArrayList<>();
        ArrayList<int[]>list2=new ArrayList<>();

        for(int i=0;i<n;i++) {
            int num=in.nextInt();
            a[i]=num;
            list.add(num);
        }
        ArrayList<Integer>liu=new ArrayList<>(list.subList(1,8));

        int keneng=(n-k+1)*(n-k+1);
        int from=0;
        int to=k;
        for(int i=0;i<n-k+1;i++) {
            int[] subArray =Arrays.copyOfRange(a,from,to);
            list1.add(subArray);
            list2.add(subArray);
            from++;
            to++;
        }

        int P=0;
        int Q=0;
        int add=0;
        for(int []sub1:list1) {
            Arrays.sort(sub1);
            P=sub1[sub1.length-1];
            for(int []sub2:list2) {
                Arrays.sort(sub2);
                Q=sub2[0];
                add+=(P-Q);
            }
        }
        double ans=add/keneng;
        System.out.printf("%.2f",ans);

    }
    static FastReader in=new FastReader();
    static PrintWriter out=new PrintWriter(System.out);
    static class FastReader {
        static BufferedReader br;
        static StringTokenizer st;
        FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            String str="";
            while(st==null||!st.hasMoreElements()) {
                try {
                    str=br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                st=new StringTokenizer(str);
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }

    }
}

