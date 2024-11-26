package calculate;
import java.util.Scanner;
public class sandwich {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < t; i++) {
            int b = scan.nextInt();
            int c = scan.nextInt();
            int h = scan.nextInt();
            int x = lay(b, c, h);
            System.out.println(x);
        }
        scan.close();
    }

    public static int lay(int b, int c, int h) {
        int x;
        int m = c + h;
        if (b > m) {
            x = 2 * m + 1;
        } else {
            x = 2 * b - 1;
        }
        return  x;
    }
}
