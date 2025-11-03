package Practice;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class pra251027_1 {
    public static void main(String[] args) {
        String ends = "HLT";
        Scanner in = new Scanner(System.in);
        String[] orders = new String[100000];
        System.out.println("虚拟机开始运行！");
        System.out.println("指令开始执行！");
        System.out.println("栈为空 ");

        int[] localVariable = new int[100000];
        int[] staticVariable = new int[100000];
        Stack<Integer> stank = new Stack<>();

        while (!Objects.equals(orders[0], ends)) {
            String s = in.nextLine();
            orders = s.split(" ");

            if (orders[0].equals("PUSH")) {
                if (orders[1].equals("constant")) {
                    int num = Integer.parseInt(orders[2]);
                    stank.push(num);
                } else if (orders[1].equals("local")) {
                    int index = Integer.parseInt(orders[2]);
                    stank.push(localVariable[index]);
                } else if (orders[1].equals("static")) {
                    int index = Integer.parseInt(orders[2]);
                    stank.push(staticVariable[index]);
                }

                System.out.println("执行指令：" + orders[0] + " " + orders[1] + " " + orders[2]);
                if (stank.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stank.peek());
                }
            } else if (orders[0].equals("POP")) {
                if (orders[1].equals("local")) {
                    int index = Integer.parseInt(orders[2]);
                    int num = stank.pop();
                    localVariable[index] = num;
                } else if (orders[1].equals("static")) {
                    int index = Integer.parseInt(orders[2]);
                    int num = stank.pop();
                    staticVariable[index] = num;
                }

                System.out.println("执行指令：" + orders[0] + " " + orders[1] + " " + orders[2]);
                if (stank.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stank.peek());
                }
            } else if (orders[0].equals("ADD")) {
                int n1 = stank.pop();
                int n2 = stank.pop();
                int ans = n1 + n2;
                stank.push(ans);

                System.out.println("执行指令：" + orders[0]);
                if (stank.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stank.peek());
                }
            } else if (orders[0].equals("SUB")) {
                int n1 = stank.pop();
                int n2 = stank.pop();
                int ans = n1 * n2;
                stank.push(ans);

                System.out.println("执行指令：" + orders[0]);
                if (stank.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stank.peek());
                }

            } else if (orders[0].equals("MUL")) {
                int n1 = stank.pop();
                int n2 = stank.pop();
                int ans = n1 - n2;
                stank.push(ans);

                System.out.println("执行指令：" + orders[0]);
                if (stank.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stank.peek());
                }
            }
        }
        System.out.println("执行指令：" + orders[0]);
        System.out.println("指令结束执行！");
        System.out.println("虚拟机结束运行！");
    }
}
