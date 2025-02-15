package practice;

import java.util.*;
import java.io.*;

public class pra121001_divideChocolate_doubledivision {
    static int H[] = new int[100100];
    static int W[] = new int[100100];
    static int N;
    static int K;
    static int ans;//整数乘法隐式转换为long型；
    static int l = 1;
    static int r = 100000;

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    public static void solve() {
        N = in.nextInt();
        K = in.nextInt();
        for (int i = 0; i < N; i++) {
            H[i] = in.nextInt();
            W[i] = in.nextInt();
        }
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        out.println(l);
    }

    static boolean check(int x) {
        ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (H[i] / x) * (W[i] / x);//计算长H，宽W，平均每块巧克力边长为x，
            // 可以分出的整块巧克力的个数
            if (ans >= K) {
                return true;
            }
        }
        return false;//////////////
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


