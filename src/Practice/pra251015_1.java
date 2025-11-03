package Practice;

import java.util.*;

public class pra251015_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            String n = scanner.next();//处理的数
            int k = scanner.nextInt();//目标进制

            String result = convertNumber(n, k);
            System.out.println(result);
        }

        scanner.close();
    }

    public static String convertNumber(String n, int k) {
        // 分离整数部分和小数部分
        String[] parts = n.split("\\.");//实际的正则表达式是 \.，表示匹配字面意义的小数点如果不转义，单个 . 在正则中表示"匹配任意字符"
        int integerPart = Integer.parseInt(parts[0]);
        double fractionalPart = 0.0;
        if (parts.length > 1) {
            fractionalPart = Double.parseDouble("0." + parts[1]);
        }

        // 转换整数部分（使用堆栈）
        Stack<Integer> stack = new Stack<>();
        int tempInteger = integerPart;

        if (tempInteger == 0) {
            stack.push(0);
        } else {
            while (tempInteger > 0) {
                int remainder = tempInteger % k;
                stack.push(remainder);
                tempInteger = tempInteger / k;
            }
        }

        StringBuilder integerResult = new StringBuilder();
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            integerResult.append(convertDigit(digit));
        }

        // 转换小数部分（使用队列）
        Queue<Integer> queue = new LinkedList<>();
        double tempFractional = fractionalPart;
        int precision = 0;

        while (tempFractional > 0 && precision < 3) {
            tempFractional *= k;
            int integerDigit = (int) tempFractional;
            queue.offer(integerDigit);
            tempFractional -= integerDigit;
            precision++;
        }

        // 如果小数部分不足3位，补0
        while (precision < 3) {
            queue.offer(0);
            precision++;
        }

        StringBuilder fractionalResult = new StringBuilder(".");
        while (!queue.isEmpty()) {
            int digit = queue.poll();
            fractionalResult.append(convertDigit(digit));
        }

        // 组合结果
        if (fractionalPart == 0) {
            return integerResult.toString();
        } else {
            return integerResult.toString() + fractionalResult.toString();
        }
    }

    // 将数字转换为对应的字符（处理10进制以上）
    public static char convertDigit(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        } else {
            return (char) ('A' + digit - 10);
        }
    }
}