package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class pra241005 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {//遍历字符数组的每一个字符，并将字符串转换为字符数组
            map.put(c, map.getOrDefault(c, 0) + 1);
            //如果该字符出现过则将他出现的次数加1，如果未出现过则设置为1
        }

        int count = 0;
        char mostfrechr = ' ';
        int maxfrequency = 0;
        System.out.print("重复的字符和重复的次数是：");
        System.out.println();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxfrequency) {
                maxfrequency = entry.getValue();
                mostfrechr = entry.getKey();
            }
            int n = entry.getValue();
            char c = entry.getKey();
            if (n > 1) {//如果出现次数大于一则将它输出
                System.out.print(" ### " + c + " " + n);
                count++;
            }
        }
        System.out.println();
        System.out.println("重复最多的字符及次数：" + mostfrechr + "  " + maxfrequency);
        System.out.println("重复字符的个数：" + count);
    }
}
