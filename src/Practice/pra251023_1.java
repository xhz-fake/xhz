package Practice;

import java.util.Scanner;

public class pra251023_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine().trim());

        while (t-- > 0) {
            String str = scanner.nextLine().trim();
            int num = Integer.parseInt(scanner.nextLine().trim());
            ;
            while (num-- > 0) {
                String pattern = scanner.nextLine().trim();
                int count = hasRepeat(str, pattern);
                System.out.println(pattern + ":" + count);
            }
        }

        scanner.close();
    }

    private static int hasRepeat(String s1, String pattern) {
        // 存储子串和其结束位置+1（这样便于判断是否重叠）

        String s = s1;
        int len = pattern.length();
        int count = 0;

        for (int i = 0; i <= s.length() - len; i++) {
            String substr = s.substring(i, i + len);
            if (pattern.equals(substr)) {
                count++;
            }
        }

        return count;
    }
}