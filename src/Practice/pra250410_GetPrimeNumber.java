package Practice;

import java.util.ArrayList;
import java.util.List;

public class pra250410_GetPrimeNumber {
    static int N = 10000000;
    static boolean[] isPrime = new boolean[N + 1];
    static List<Integer> prime = new ArrayList<>();

    public static void main(String[] args) {
        get_prime(N);
        System.out.println(prime.get(100001));
        show (91);
    }

    public static void get_prime(int N) {//获取N个数中的素数
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                prime.add(i);
                if ((long) i * (long) i > N) {
                    continue;
                }
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void show(long x) {//展示整数X被素数分解的表达式
        for (int i = 0; i < prime.size(); i++) {
            if (x == 1) {
                break;
            }
            long rcnt = 0;
            while (x % prime.get(i) == 0) {
                rcnt++;
                x /= prime.get(i);
            }
            if (rcnt > 0) {
                System.out.print(prime.get(i) + "^" + rcnt + " ");
            }
        }
        if(x!=1) {
            System.out.println("PRIME:"+x+"^"+1);
        }

    }

}
