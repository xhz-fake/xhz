package Practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class pra250915_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<BigInteger> list = new ArrayList<>();
        while (n-- > 0) {
            BigInteger num = in.nextBigInteger();
            list.add(num);
        }
        int m = in.nextInt();
        while (m-- > 0) {
            String s = in.next();
            BigInteger ans;
            int a;
            int b;
            if (s.equals("PLUS")) {
                a = in.nextInt();
                b = in.nextInt();
                ans = list.get(a).add(list.get(b));
            } else if (s.equals("MINUS")) {
                a = in.nextInt();
                b = in.nextInt();
                ans = list.get(a).subtract(list.get(b));
            } else {
                a = in.nextInt();
                b = in.nextInt();
                ans = list.get(a).multiply(list.get(b));
            }
            System.out.println(ans);
        }
    }
}
