package practice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pra241028 {
    public static void main(String[] args) {
        int t = 1;
        for (int i = 1; i <= t; i++) {
            solve();
        }
        out.flush();
    }
    static void solve() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.print(in.nextLong() + " ");
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
        double nextDouble() {
            return Double.parseDouble(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
