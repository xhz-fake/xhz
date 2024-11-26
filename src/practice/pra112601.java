package practice;

import java.util.*;
import java.io.*;

public class pra112601 {
    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n = in.nextInt(), m = in.nextInt();
        for (int k = 0; k < m; k++) {
            int s = in.nextInt();

            if (s == 1) {
                int x = in.nextInt();
                n = (n >> x) % 2;
                out.println(n);

            } else if (s == 2) {
                int l = in.nextInt(), r = in.nextInt();
                int a = (1 << (r + 1)) - 1;
                int b = (1 << l) - 1;
                n = (n ^ (a - b));
                out.println(n);


            } else if (s == 3) {
                int l = in.nextInt(), r = in.nextInt();
                for (int j = l; j <= r; j++) {
                    n |= (1 << j);
                }
                out.println(n);
            } else if (s == 4) {
                int l = in.nextInt(), r = in.nextInt();
                for (int i = l; i <= r; i++) {
                    n &= ~(1 << i);
                }
                out.println(n);

            } else if (s == 5) {
                out.println(n & -n);
            }

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