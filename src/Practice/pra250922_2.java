package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class pra250922_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        s = s.toUpperCase();
        in.nextLine();

        String p = in.nextLine();
        p = p.toUpperCase();

        ArrayList<String> list = new ArrayList<>(Arrays.asList(p.trim().split("\\W+")));
        // 如果想过滤掉可能出现的空串，可再补一行：
        list.removeIf(String::isEmpty);

        int firstPos = -1;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i);
            if (word.equals(s)) {
                count++;
            }
            if (word.equals(s) && firstPos == -1) {
                firstPos = i;
            }
        }

        if (count == 0) {
            System.out.println(firstPos);
        } else {
            System.out.print(count + " " + firstPos);
        }

    }
}
