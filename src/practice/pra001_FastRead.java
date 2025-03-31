package practice;

import java.util.*;
import java.io.*;

public class pra001_FastRead {

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    public static void solve() {
        int n=in.nextInt();
        int a[]=new int[n+1];

        for(int i=1;i<=n;i++) {
            a[i]=in.nextInt();
        }

        TreeMap<Integer,int[]>map=new TreeMap<>();
        for(int i=1;i<=n;i++) {
            int b[]=new int[n+1];
            for(int j=1;j<=n;j++) {
                b[0]=0;
                b[j]=b[j-1]+i;
                map.put(i, b);
            }
        }
        int compare[]=new int[n+1];
        int ans=0;
        for(int i=1;i<=n;i++) {
            compare=map.get(i);
            for(int j=1;j<=n;j++) {
                if(compare[j]%a[j]==0) {
                    ans++;
                }
            }

        }
        System.out.println(ans);
    }

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static class FastReader {
        static BufferedReader br;
        static StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            String str = "";
            while (st == null || !st.hasMoreElements()) {
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                st = new StringTokenizer(str);
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}



