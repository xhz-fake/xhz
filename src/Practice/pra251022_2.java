package Practice;

import java.util.Scanner;

/**
 * KMP算法Java实现
 * 用于解决字符串匹配问题
 */
public class pra251022_2 {

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

    /**
     * KMP字符串匹配算法
     *
     * @param text    主串
     * @param pattern 模式串
     * @return 匹配位置（从1开始），未找到返回0
     * 算法优势：
     * - 主串指针不回溯，提高匹配效率
     * - 时间复杂度O(n+m)，其中n为主串长度，m为模式串长度
     */
    public static int kmpSearch(String text, String pattern) {
        // 边界情况处理
        if (pattern.isEmpty()) return 1;  // 空模式串默认匹配位置1
        if (pattern.length() > text.length()) return 0;  // 模式串比主串长

        int n = text.length();
        int m = pattern.length();
        int[] next = buildNext(pattern);

        int i = 0;  // 主串指针，永不回溯
        int j = 0;  // 模式串指针

        // 匹配过程
        while (i < n && j < m) {
            // j == -1：模式串需要从头开始匹配
            // 当前字符匹配成功：继续比较下一个字符
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                // 不匹配时，模式串指针根据next数组智能跳转
                j = next[j];
            }
        }

        // 判断匹配结果
        if (j == m) {
            // 匹配成功，返回位置（从1开始计算）
            return i - j + 1;
        } else {
            // 匹配失败
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取测试用例数量
        int t = scanner.nextInt();
        scanner.nextLine();  // 消耗换行符

        // 处理每个测试用例
        while (t-- > 0) {
            // 读取主串和模式串
            String text = scanner.nextLine();
            String pattern = scanner.nextLine();
            String chargeText = scanner.nextLine();
            System.out.println(text);

            // 构建next数组
            int[] next = buildNext(pattern);

//            // 输出next数组
//            for (int j = 0; j < next.length; j++) {
//                System.out.print(next[j]+ " ");
//            }
//            System.out.println();


            // 执行KMP匹配
            int pos = kmpSearch(text, pattern);
            int patternLen = pattern.length();

            char[] textc = text.toCharArray();

            if (pos != 0) {
                StringBuilder s1 = new StringBuilder();
                for (int i = 0; i < pos - 1; i++) {
                    s1.append(textc[i]);
                }

                StringBuilder s3 = new StringBuilder();
                for (int i = pos + patternLen - 1; i < textc.length; i++) {
                    s3.append(textc[i]);
                }

                StringBuilder s2 = new StringBuilder(chargeText);

                StringBuilder sAns = s1.append(s2).append(s3);
                System.out.println(sAns);
            }else{
                System.out.println(text);
            }

        }

        scanner.close();
    }
}