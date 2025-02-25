package practice;

import java.io.*;
import java.util.*;

public class pra241121_2 {
    static int N = 1010;
    static int[][] a = new int[N][N];
    static int[][] b = new int[N][N];

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        int n=in.nextInt(),m=in.nextInt(),q=in.nextInt();
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                a[i][j] = in.nextInt();
                b[i][j] = a[i][j]-a[i-1][j]-a[i][j-1]+a[i-1][j-1];
            }
        }
        while(q-->0) {
            int x1=in.nextInt(),y1=in.nextInt(),x2=in.nextInt(),y2=in.nextInt(),d=in.nextInt();
            b[x1][y1] += d;
            b[x2+1][y1] -= d;
            b[x1][y2+1] -= d;
            b[x2+1][y2+1] += d;
        }
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                b[i][j]=b[i][j]+b[i][j-1]+b[i-1][j]-b[i-1][j-1];
                out.print(b[i][j]+" ");
            }
            out.println();
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