package Practice;

import java.util.Scanner;

public class pra250922_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String m = in.next();
        char ac = m.charAt(0);
        char bc = m.charAt(1);

        int x = (int) ac;
        int y = (int) bc;

        if (x > y) {
            int count = 0;
            for (int i = y; i <= x; i++) {
                count++;
                System.out.print((char) i);
                System.out.print(" ");
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        } else {
            int count = 0;
            for (int i = x; i <= y; i++) {
                count++;
                System.out.print((char) i);
                System.out.print(" ");
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }

    }
}
