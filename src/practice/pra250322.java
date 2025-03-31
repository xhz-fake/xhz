package practice;

import java.util.ArrayList;
import java.util.Scanner;

public class pra250322 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        ArrayList<Integer> listl=new ArrayList<>();
        listl.add(8);
        listl.add(4);
        listl.add(2);
        
        String s = in.next();
        long countL = 0;
        long countLA = 0;
        long countLAN = 0;
        for (char c : s.toCharArray()) {
            if (c == 'l') {
                countL++;
            } else if (c == 'a') {
                countLA += countL;
            } else if (c == 'n') {
                countLAN += countLA;
            }
        }
        System.out.println(countLAN);
    }
}
