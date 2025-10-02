package Practice;

import java.text.DecimalFormat;
import java.util.Scanner;

interface Geometry {
    double getArea();

}

class Circle implements Geometry {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        double ans = 3.1415926 * r * r;
        return Math.round(ans * 100) / 100.0;
    }
}

class Lader implements Geometry {
    private double up;
    private double down;
    private double h;

    public Lader(double up, double down, double h) {
        this.h = h;
        this.down = down;
        this.up = up;
    }

    @Override
    public double getArea() {
        double ans = (up + down) * h / 2;
        return ans;
    }
}

class Rect implements Geometry {
    private double c;
    private double r;

    public Rect(double c, double r) {
        this.r = r;
        this.c = c;
    }

    @Override
    public double getArea() {
        double ans = c * r;
        return ans;
    }
}

public class pra250929_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            String type = in.next();
            if (type.equals("Circle")) {
                double r = in.nextDouble();
                Circle circle = new Circle(r);
                double ans = circle.getArea();
                System.out.println("Circle area: " + ans + "");

            } else if (type.equals("Lader")) {
                String s = in.next();
                String[] parts = s.split(",");
                double up = Double.parseDouble(parts[0]);
                double down = Double.parseDouble(parts[1]);
                double h = Double.parseDouble(parts[2]);
                Lader lader = new Lader(up, down, h);
                double ans = lader.getArea();
                DecimalFormat df = new DecimalFormat("#.00");

                System.out.println("Lader area: " + df.format(ans) + "");
            } else {
                String s = in.next();
                String[] parts = s.split(",");
                double c = Double.parseDouble(parts[0]);
                double r = Double.parseDouble(parts[1]);
                Rect rect = new Rect(c, r);
                DecimalFormat df = new DecimalFormat("#.00");
                double ans = rect.getArea();
                System.out.println("Rect area: " + df.format(ans) + "");
            }
        }
    }
}