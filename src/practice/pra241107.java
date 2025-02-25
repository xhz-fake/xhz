package practice;

import java.io.*;
import java.util.*;

public class pra241107 {
    static int N =(int)(1e5+10);
    static int n,m;
    static int[] a = new int[N];
    static long[][] s = new long[N][6];
    static long MOD = (long)(1e9+7);

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        n = in.nextInt();
        m = in.nextInt();
        for(int i=1;i<=n;i++) {
            a[i] = in.nextInt();
            for(int j=1;j<=5;j++){
                s[i][j] = s[i-1][j] +(long)Math.pow(a[i],j);
            }
        }
        while(m-->0) {
            int l = in.nextInt(),r = in.nextInt(),k = in.nextInt();
            out.println((s[r][k]-s[l-1][k])%MOD);
        }
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
            while(st==null||!st.hasMoreElements()){
                try {
                    str = br.readLine();
                }catch(IOException e){
                    throw new RuntimeException(e);
                }
                st = new StringTokenizer(str);
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }
    }
}