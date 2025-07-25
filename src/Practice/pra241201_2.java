package Practice;

import java.util.*;
import java.io.*;

public class pra241201_2 {
    static int N=1010;
    static int result=0;
    static int cards[]=new int[N]; // 示例输入
    public static void main(String[] args) {
        int n=in.nextInt();
        for (int i = 0; i < n; i++) {
            cards[i]=in.nextInt();
        }
        solution(cards);
        out.flush();
    }

    public static int solution(int cards[]) {

        for (int card : cards) {
            result ^= card; // 对数组中的每个元素进行异或运算
        } // 最终结果就是那个只出现一次的数字
        out.print(result);
        return (result); // 输出结果
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
