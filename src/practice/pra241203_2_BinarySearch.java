package practice;

import java.util.*;
import java.io.*;

public class pra241203_2_BinarySearch {

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    public static void solve() {
        int N = (int) (1e5 + 10);
        int n = in.nextInt();
        int q = in.nextInt();
        int a[] = new int[N];
        int ans;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        while (q-- > 0) {
            int num = in.nextInt();
            int l = in.nextInt();
            int r = in.nextInt();
            int x = in.nextInt();
            switch (num) {
                case 1:
                    while (l < r) {
                        int mid = l + r >> 1;
                        if (x <= a[mid]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    if (a[l] != x) {
                        out.println(-1);
                        break;
                    } else {
                        out.println(l);
                    }
                    break;

                case 2:
                    while (l < r) {
                        int mid = l + r + 1 >> 1;
                        if (x < a[mid]) {
                            r = mid - 1;
                        } else {
                            l = mid;
                        }
                    }
                    if (a[l] != x) {
                        out.println(-1);
                        break;
                    } else {
                        out.println(l);
                    }
                    break;

                case 3:
                    if (x > a[r]) {
                        out.println(-1);
                        break;
                    }
                    while (l < r) {
                        int mid = l + r >> 1;
                        if (x <= a[mid]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    out.println(l);
                    break;

                case 4:
                    if (x >= a[r]) {
                        out.println(-1);
                        break;
                    }
                    while (l < r) {
                        int mid = l + r >> 1;
                        if (x < a[mid]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    out.println(l);
                    break;
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