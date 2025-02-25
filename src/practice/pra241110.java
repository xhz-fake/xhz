package practice;

import java.util.*;
import java.io.*;

public class pra241110 {

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static int N = (int) (1e5 + 10);
    static int n ;
    static int m ;
    static int a[] = new int[N];
    static int b[] = new int[N];
    static int c[] = new int[N];

    static void solve() {
        n=in.nextInt();
        m=in.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = a[i] - a[i - 1];
        }

        for (int i = 0; i < m; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int d = in.nextInt();
            b[l] += d;
            b[r + 1] -= d;
        }
        for (int i = 1; i <= n; i++) {
            c[i] = c[i - 1] + b[i];
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(c[i] + " ");
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
            while (st == null || !st.hasMoreElements()) {
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                st=new StringTokenizer(str);
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }

    }

}
