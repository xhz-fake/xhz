package practice;

import java.math.BigInteger;
import java.util.Scanner;

public class pra250403_Biginteger {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int K=in.nextInt();
        BigInteger ten=BigInteger.valueOf(10);
        BigInteger pownum=ten.pow(K);
        BigInteger mul=BigInteger.valueOf(1);
        for(int i=1;i<Integer.MAX_VALUE;i++ ) {
            if(K>10000000) {
                System.out.println("-1");
                break;
            }
            BigInteger num=BigInteger.valueOf(i);
            mul=mul.multiply(num);
            BigInteger remainder=mul.remainder(pownum);
            if(remainder.equals(BigInteger.ZERO)) {
                System.out.println(i);
                break;
            }
        }
        in.close();
    }


}
