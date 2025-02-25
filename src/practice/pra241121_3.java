package practice;

import java.util.*;
import java.io.*;

public class pra241121_3 {
    static int N = 2010;
    static int a[][] = new int[N][N];
    static int s[][] = new int[N][N];


    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        for (int k = 1; k <= m; k++) {
            int x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (a[i][j] == 0) {
                        a[i][j] = 1;
                    } else {
                        a[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.print(a[i][j]);
            }
            out.println();
        }
    }
    static pra241121_2.FastReader in = new pra241121_2.FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static class FastReader {
        static BufferedReader br;
        static StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            String str = "";
            while(st==null||!st.hasMoreElements()) {
                try {
                    str = br.readLine();
                }catch(IOException e) {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }

}


