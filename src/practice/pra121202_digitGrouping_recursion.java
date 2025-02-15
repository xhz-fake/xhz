package practice;

import java.util.Scanner;

public class pra121202_digitGrouping_recursion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupCount = scanner.nextInt(); // 用户输入数字组的数量
        int []number=new int[groupCount];
        for (int i = 0; i < groupCount; i++) {
            number[i]=scanner.nextInt();
        }

        int count = solution(number);
        System.out.println(count);
    }

    static int solution(int[] number){//这个方法是用来应付题解的，
        // 将整型数组转换为字符型数组并传给下面的 countEvenSumCombinations 方法

        String[] numbers = new String[number.length]; // 创建一个数组来存储数字组

        for (int i = 0; i < number.length; i++) {
            numbers[i] = Integer.toString(number[i]); // 将每个整型元素转换为字符串
        }

        return countEvenSumCombinations(numbers);
    }


    public static int countEvenSumCombinations(String[] numbers) {
        int[] count = {0}; // 使用数组来存储计数，以便在递归中修改
        backtrack(new int[numbers.length], 0, 0, count, numbers);
        return count[0]; // 返回计数
    }

    private static void backtrack(int[] choice, int start, int sum, int[] count,
                                                             String[] numbers) {
        if (start == numbers.length) {

            if (sum % 2 == 0) {
                count[0]++; // 如果和为偶数，增加计数
            }
            return;//return 语句用于结束当前递归调用并返回控制权到上一层递归调用
                   //该语句确保递归在处理完当前路径后不再进一步深入，而是返回到上一层递归，
                   //继续处理其他可能的数字组合
        }

        for (int i = 0; i < numbers[start].length(); i++) {
            choice[start] = i;
            int newSum = sum +  (numbers[start].charAt(i) - '0');
            backtrack(choice, start + 1, newSum, count, numbers);
        }

    }
}


















