package practice;

public class pra241111 {
    public static void main(String[] args) {
        int N=20230610;
        long m = 20230610;
        long a[] = new long[N];
        long b[] = new long[N];
        long c[] = new long[N];
        long num=0;
        for (int i = 0; i <100000; i++) {
            a[i] = i;
        }
        for (int i = 1; i < 100000; i++) {
            b[i] += a[i] + b[i - 1];
        }
        for (int i = 1; i < 100000; i++) {
            c[i]+=b[i]+c[i-1];
            if(c[i]<=m){
                num++;
            }
        }
        System.out.println(num);
    }
}
