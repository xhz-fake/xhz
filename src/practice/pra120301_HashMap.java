package practice;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pra120301_HashMap {

    public static int[] solution(int max, int[] array) {

        Arrays.sort(array);//此处排序是为了后面方便的去重

        ArrayList<Integer> nonZeroElements = new ArrayList<>();
        // 遍历数组，将非零元素添加到 ArrayList
        // ArrayList的特性：   动态数组：ArrayList内部使用数组来存储元素，当添加元素超过当前容量时，会自动扩容。
        //                  非同步：ArrayList不是线程安全的，如果你需要在多线程环境中使用，应该使用Collections.synchronizedList方法来包装它。
        //                  有序：ArrayList中的元素保持插入顺序。
        //                  允许重复：可以存储重复的元素。

        for (int num : array) {
            if (num!= 0) {
                nonZeroElements.add(num);
                //add（elements）将元素直接放到末尾
                //add（index，elements）将元素插入到指定位置
            }
        }

        // 将 ArrayList 转换回数组
        int[] resultArray = new int[nonZeroElements.size()];
        for (int i = 0; i < nonZeroElements.size(); i++) {
            resultArray[i] = nonZeroElements.get(i);
        }

        HashMap<Integer, Integer> elementCountMap = new HashMap<>();
        for (int num2 : resultArray) {
            elementCountMap.put(num2, elementCountMap.getOrDefault(num2, 0) + 1);
        }

        //这个循环遍历resultArray中的每个元素。对于每个元素num2，它检查elementCountMap中
        // 是否已经有了num2作为键。如果存在，就获取当前的值并加1；
        // 如果不存在，就使用getOrDefault方法返回0，然后加1。这样，
        // 每个元素的出现次数就被累加到elementCountMap中。


        // 将 HashMap 中的键值对转换为二维数组
        int[][] result = new int[elementCountMap.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : elementCountMap.entrySet()) {
            result[index][0] = entry.getKey();
            result[index][1] = entry.getValue();
            index++;
        }
        //1,elementCountMap.entrySet()返回一个包含HashMap中所有键值对（Entry）的Set。
        //  每个Entry对象包含一个键（key）和一个值（value）。
        //2,entry是一个代表映射（Map）中键值对（key-value pair）的对象。当你使用HashMap、
        //  Hashtable或其他实现了Map接口的类时，entry这个词通常用来指代这些映射中的单个元素。

        int[] ans = new int[]{0, 0};
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i][1] >= 3 && result[i][0] == 1) {
                for (int j = result.length - 1; j >= 0; j--) {
                    if (i != j && result[j][1] >= 2) { // 找到两张相同牌面值的组合
                        int sum = 3 + result[j][0] * 2;
                        if (sum <= max) {
                            ans[0] = 1;
                            ans[1] = result[j][0];
                            return ans;
                        }
                    }
                }
            }
        }
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i][1] == 2 && result[i][0] == 1) {
                for (int j = result.length - 1; j >= 0; j--) {
                    if (i != j && result[j][1] >= 3) { // 找到三张相同牌面值的组合
                        int sum = result[j][0] * 3 + 2;
                        if (sum <= max) {
                            ans[1] = 1;
                            ans[0] = result[j][0];
                            return ans;
                        }
                    }
                }
            }
        }
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i][1] >= 3 && result[i][0] != 1) { // 找到三张相同牌面值的组合
                for (int j = result.length - 1; j >= 0; j--) {
                    if (i != j && result[j][1] >= 2) { // 找到两张相同牌面值的组合
                        int sum = result[i][0] * 3 + result[j][0] * 2;
                        if (sum <= max) {
                            ans[0] = result[i][0];
                            ans[1] = result[j][0];
                            return ans;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int max = in.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int finans[] = solution(max, arr);
        System.out.println(finans[0] + "," + finans[1]);
    }

}