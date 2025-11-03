package Practice;


import java.util.Arrays;
import java.util.Scanner;

/**
 * KMP算法Java实现
 * 用于解决字符串匹配问题
 */
public class pra251022_3 {

    /**
     * 构建KMP算法的next数组
     *
     * @param pattern 模式串
     * @return next数组
     * <p>
     * next数组原理：
     * - next[j]表示当模式串中第j个字符与主串不匹配时，
     * 应该跳转到模式串的哪个位置继续比较
     * - next[0]固定为-1，表示模式串需要从头开始匹配
     */
    public static int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = -1;  // 第一个字符的next值固定为-1

        int j = 0;      // 主指针，遍历模式串
        int k = -1;     // 前缀指针，记录最长公共前后缀长度

        // 构建next数组的核心循环
        while (j < m - 1) {
            // 情况1：k为-1，表示没有公共前后缀，从头开始
            // 情况2：当前字符匹配，找到更长的公共前后缀
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j++;
                k++;
                next[j] = k;  // 记录当前位置的next值
            } else {
                // 字符不匹配时，利用已计算的next值进行回溯
                k = next[k];
            }
        }

        return next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取测试用例数量
        int t = scanner.nextInt();
        // 处理每个测试用例
        while (t-- > 0) {
            // 读取主串和模式串
            String text = scanner.nextLine();

            // 构建next数组
            int[] next = buildNext(text);
            char[] textc = text.toCharArray();

            int len = next.length;
            Arrays.sort(next);
            int max = next[len - 1];

            if (max < 1) {
                System.out.println("empty");
            } else {
                for (int i = 0; i < max; i++) {
                    System.out.print(textc[i]);
                }
                System.out.println();
            }

        }

        scanner.close();
    }
}