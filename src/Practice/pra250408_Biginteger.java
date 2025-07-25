package Practice;

import java.math.BigInteger;
import java.util.Scanner;

public class pra250408_Biginteger {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BigInteger day = BigInteger.valueOf(86400);
        BigInteger hour = BigInteger.valueOf(3600);
        BigInteger minute = BigInteger.valueOf(60);
        BigInteger time = in.nextBigInteger();
        BigInteger thousand = BigInteger.valueOf(1000);
        BigInteger t = time.divide(thousand);

        BigInteger dayt = t.mod(day);///////////////////////==== dayt  = t%day
        BigInteger dayhour = dayt.divide(hour);


        BigInteger dayMinuteAndSecond = dayt.mod(hour);
        BigInteger dayminute = dayMinuteAndSecond.divide(minute);

        BigInteger daysecond = dayMinuteAndSecond.mod(minute);

        int h = dayhour.intValue();//////////////////
        int m = dayminute.intValue();////////////////
        int s = daysecond.intValue();////////////////

        String sh = h + "";
        String sm = m + "";
        String ss = s + "";
        if (h < 10) {
            sh = "0" + h;
        }
        if (m < 10) {
            sm = "0" + m;
        }
        if (s < 10) {
            ss = "0" + s;
        }
        System.out.println(sh + ":" + sm + ":" + ss);

        in.close();
    }

}
