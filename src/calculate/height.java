package calculate;
import java.util.Scanner;
public class height {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
       float p = scan.nextFloat();
        float i = scan.nextFloat();
        float d = scan.nextFloat();
        scan.nextLine();
      int s =scan.nextInt(); ;
       int t = scan.nextInt();;
        int count=0;
        float m1 =t-s;
        float m0=0;
        float ut=0;
        float sum=t-s;
        for (int j = 1; j < 201; j++) {
            s += ut;
            sum += t-s;
            ut = p * (t - s + i * (sum) + d * (m1 - m0) );
            m0 = m1;
            m1 = t - s;
            count++;

            if (count % 10 == 0) {
                System.out.println(" ");
                System.out.print(s);
            }
        }
        scan.close();
    }
}
