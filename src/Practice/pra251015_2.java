package Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class pra251015_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Queue<Integer> qa = new LinkedList<>();
        Queue<Integer> qb = new LinkedList<>();
        Queue<Integer> qc = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (num % 2 == 0) {
                qb.offer(num);
            } else if (num % 2 != 0) {
                qa.offer(num);
            }
        }

        while (!qa.isEmpty() || !qb.isEmpty()) {
            int num=0;
            if (!qa.isEmpty()) {
                num = qa.poll();
                qc.offer(num);
            }
            if (!qa.isEmpty()) {
                num = qa.poll();
                qc.offer(num);
            }
            if (!qb.isEmpty()) {
                num = qb.poll();
                qc.offer(num);
            }
        }

        int l = qc.size();

        for (int i = 0; i < l; i++) {
            int num = 0;
            if (!qc.isEmpty()) {
                num = qc.poll();
            }
            if (qc.isEmpty()) {
                System.out.print(num);
            } else {
                System.out.print(num + " ");
            }
        }

    }
}
