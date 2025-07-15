package practice;

import java.math.BigInteger;
import java.util.Scanner;

public class pra250313_BigIntegerCalculate {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        BigInteger big = BigInteger.valueOf(1_000_000_000); // 1e9
        BigInteger bigbb = BigInteger.valueOf(1000000000); // 1e9
        BigInteger nine = BigInteger.valueOf(9);
        BigInteger eight = BigInteger.valueOf(8);
        BigInteger seven = in.nextBigInteger();/////////////////////////////////////////

        BigInteger mod = big.add(BigInteger.valueOf(7)); // big + 7
        BigInteger ans = nine.pow(10000).subtract(eight.pow(10000).multiply(BigInteger.valueOf(2)))
                .add(seven.pow(10000));
        BigInteger ans1 = ans.mod(mod);

        System.out.println(ans1);

        BigInteger nine2=BigInteger.valueOf(9);
        BigInteger eight2=BigInteger.valueOf(8);
        BigInteger seven2=BigInteger.valueOf(7);
        BigInteger big2=BigInteger.valueOf(1_000_000_000);

        BigInteger mod2=big2.add(seven2);

        BigInteger ans2=nine2.pow(10000).subtract(eight2.pow(10000).add(eight2.pow(10000))).add(seven2.pow(10000));
        BigInteger ans3=ans2.mod(mod2);
        System.out.println(ans3);


        //add : + //subtract :-// multiple: *// divide:/  // mod:%(一定为正数,与括号内部的数的符号一致)  //reminder:%(不一定为正)
        }
}
