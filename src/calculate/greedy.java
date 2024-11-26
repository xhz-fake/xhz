package calculate;

import java.util.Arrays;
import java.util.Scanner;

public class greedy{
    static class Factory implements Comparable<Factory> {
       int a; // 每天生产的恶魔果实数量
        int b; // 恶魔果实的单价（亿贝里）

        public Factory(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Factory other) {
            return this.b - other.b; // 升序排序
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 凯多需要的恶魔果实数量
        int m = scanner.nextInt(); // 恶魔果实工厂数量

        Factory[] factories = new Factory[m];
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            factories[i] = new Factory(a, b);
        }

        // 对工厂进行排序，按照恶魔果实的单价升序排列
        Arrays.sort(factories);

       long cost = 0; // 总花费
        for (Factory factory : factories) {
            if (n <= 0) {
                break; // 如果已经购买足够的恶魔果实，则停止
            }
            long produce = Math.min(n, factory.a); // 计算这个工厂能提供多少恶魔果实
            n -= produce; // 更新还需要的恶魔果实数量
            cost += produce * factory.b; // 更新总花费
        }

        System.out.println(cost); // 输出最少花费的金额

    }
}
