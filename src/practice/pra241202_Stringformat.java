package practice;
import java.util.Scanner;
import java.text.DecimalFormat;
public class pra241202_Stringformat {
    public static String solution(String s) {
        System.out.println(formatNumber(s));
        return "";
    }
    public static String formatNumber(String str) {
        // 去除前面的无用0
        str = str.replaceFirst("^0+(?!$)", "");
        //：这个符号表示字符串的开始。它确保我们只在字符串的开头寻找匹配的模式。
        //0+：这个模式匹配一个或多个零。0表示数字零，+表示一个或多个前面的字符（在这个情况下是零）。
        //(?!$)：这是一个负向前瞻（negative lookahead），用于确保我们的匹配不是字符串的结尾。
        // $表示字符串的结束，?!表示“不是”。所以，(?!$)确保我们不会匹配字符串末尾的零。

        // 检查是否包含小数点
        boolean hasDecimal = str.contains(".");

        // 如果包含小数点，分割整数部分和小数部分
        String[] parts = hasDecimal ? str.split("\\.", 2) : new String[]{str, ""};
        //     \\.：这是正则表达式，用于匹配小数点（.）。在正则表达式中，.是一个特殊字符，表示匹配
        //     任意单个字符，所以需要使用反斜杠\进行转义，使其表示一个字面上的小数点。

        //      2：这是限制参数，它指定了分割后数组的最大长度。这意味着split方法将返回一个
        //      最多包含两个元素的数组。

        // 格式化整数部分
        DecimalFormat df = new DecimalFormat("###,###");
        parts[0] = df.format(Long.parseLong(parts[0]));
        //parts[0]：这是之前通过split方法得到的整数部分的字符串。
        //Long.parseLong(parts[0])：这是将字符串转换为long类型的数字。这是因为DecimalFormat的format方法需
        // 要一个Number对象作为参数，而long类型的数字可以直接被转换为Long对象。
        //df.format(...)：这是调用DecimalFormat对象的format方法，将数字格式化为指定的模式字符串。

        // 如果有小数部分，拼接回去
        if (hasDecimal) {
            return parts[0] + "." + parts[1];
        } else {
            return parts[0];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String ans=solution(str);
    }
}