package Practice;

import java.util.ArrayList;
import java.util.Scanner;

public class pra250917_1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(1000);
        list.add(0);
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int num = in.nextInt();
            list.add(num);
        }
        //输出初始
        System.out.print(n);
        for (int i = 1; i <= n; i++) {
            System.out.print(" " + list.get(i));
        }
        System.out.println();


        int insertPos = in.nextInt();
        int insertNum = in.nextInt();
        list.add(insertPos, insertNum);
        //输出第一次插入
        System.out.print(list.size()-1);
        for (int i = 1; i < list.size(); i++) {
            System.out.print(" " + list.get(i));
        }
        System.out.println();


        insertPos = in.nextInt();
        insertNum = in.nextInt();
        list.add(insertPos, insertNum);
        //输出第二次插入
        System.out.print(list.size()-1);
        for (int i = 1; i < list.size(); i++) {
            System.out.print(" " + list.get(i));
        }
        System.out.println();


        int delPos = in.nextInt();
        if (delPos >= list.size()) {
            System.out.print("error");
        } else {
            list.remove(delPos);
            //输出第三次删除
            System.out.print(list.size()-1);
            for (int i = 1; i < list.size(); i++) {
                System.out.print(" " + list.get(i));
            }
        }
        System.out.println();


        delPos = in.nextInt();
        if (delPos >= list.size()) {
            System.out.print("error");
        } else {
            list.remove(delPos);
            //输出第四次删除
            System.out.print(list.size()-1);
            for (int i = 1; i < list.size(); i++) {
                System.out.print(" " + list.get(i));
            }
        }
        System.out.println();

        //第五次查询
        int checkPos = in.nextInt();
        if (checkPos >= list.size() || checkPos == 0) {
            System.out.print("error");
        } else {
            System.out.print(list.get(checkPos));
        }
        System.out.println();


        //第六次查询
        checkPos = in.nextInt();
        if (checkPos >= list.size() || checkPos == 0) {
            System.out.print("error");
        } else {
            System.out.print(list.get(checkPos));
        }


    }
}
