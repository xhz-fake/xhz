package practice;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pra122401 {

    public static String solution(int n, String template, String[] titles) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("\\{.*?\\}");
        Matcher matcher = pattern.matcher(template);

        // 找到所有通配符的位置和长度
        int[] placeholders = new int[template.length()];
        int count = 0;
        while (matcher.find()) {
            placeholders[count++] = matcher.start();
        }

        // 检查每个标题是否可以通过替换通配符生成
        for (String title : titles) {
            if (canGenerate(template, title, placeholders, count)) {
                result.append("True");
            } else {
                result.append("False");
            }
            result.append(",");
        }
        // 移除最后一个多余的逗号
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    private static boolean canGenerate(String template, String title, int[] placeholders, int count) {
        if (template.length() != title.length() + count) {
            return false;
        }

        for (int i = 0, j = 0; i < template.length(); i++) {
            if (j < count && i == placeholders[j]) {
                j++;
                continue;
            }
            if (template.charAt(i) != title.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of titles (n):");
        int n = scanner.nextInt();
        System.out.println("Enter the template:");
        String template = scanner.next();
        System.out.println("Enter the titles:");
        String[] titles = new String[n];
        for (int i = 0; i < n; i++) {
            titles[i] = scanner.next();
        }

        String result = solution(n, template, titles);
        System.out.println(result);
    }
}
