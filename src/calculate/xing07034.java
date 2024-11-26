package calculate;

public class xing07034 {
    public static void main(String[] args) {
     int sum=0;

            for (int m = 1; m < 101; m++) {

                if (m % 3 == 0 && m % 5 == 0 || m % 5 == 0 && m % 7 == 0)
                    sum += m;
            }

                System.out.println("一百内能被3和5整除的数或能被5和7整除的数的和:"+sum);



    }
}
