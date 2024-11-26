package calculate;
import java.util.Scanner;
public class soldier {
    public static void main(String[] args) {
       Scanner scan=new Scanner(System.in);
      int n= scan.nextInt();
       double v= scan.nextDouble();
       double u= scan.nextDouble();
       scan.nextLine();
        for (int i = 0; i < n; i++) {
           double[] c=new double[n];
            c[i]=scan.nextInt();
        }
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            double[] d=new double[n];
            d[i]= scan.nextDouble();
        }


    }
}
