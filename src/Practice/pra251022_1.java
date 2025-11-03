package Practice;
import java.util.Scanner;

public class pra251022_1 {

    /**
     * 找到字符串的最长真前后缀
     * @param str 输入字符串
     * @return 最长的真前后缀，如果不存在则返回"empty"
     */
    public static String matched_Prefix_Postfix(String str) {
        // 如果字符串长度小于等于1，没有真前后缀
        if (str.length() <= 1) {
            return "empty";
        }

        // 构建next数组
        int[] next = buildNext(str);

        // 最长真前后缀的长度就是next数组的最后一个值
        int maxLen = next[str.length()];

        // 如果长度为0，说明没有真前后缀
        if (maxLen == 0) {
            return "empty";
        } else {
            // 返回前maxLen个字符作为结果
            return str.substring(0, maxLen);
        }
    }

    /**
     * 构建KMP算法的next数组
     * @param pattern 模式串
     * @return next数组
     */
    public static int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m+1];
        next[0] = -1;  // 第一个字符的next值固定为-1

        int j = 0;      // 主指针
        int k = -1;     // 前缀指针

        while (j < m) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j++;
                k++;
                next[j] = k;  // 记录当前位置的next值
            } else {
                // 不匹配时回溯
                k = next[k];
            }
        }

        return next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取字符串个数
        int n = scanner.nextInt();
        scanner.nextLine();  // 消耗换行符

        // 处理每个字符串
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            // 调用函数并输出结果
            String result = matched_Prefix_Postfix(str);
            System.out.println(result);
        }

        scanner.close();
    }
}