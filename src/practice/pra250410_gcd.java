package practice;

import java.math.BigInteger;

public class pra250410_gcd {
    public static void main(String[] args) {
        int ans=gcd(996,696);
        System.out.println(ans);


        BigInteger am=BigInteger.valueOf(996);
        BigInteger bm=BigInteger.valueOf(696);
        BigInteger res=bm.gcd(am);
        System.out.println(res);


    }

    public static int gcd(int a,int b) {
        if(b==0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
