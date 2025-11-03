package Practice;

import java.util.Scanner;

class Circle {
    private double area;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        area = 3.1415926 * radius * radius;
        return area;
    }

}

class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double c, double b, double a) {
        this.c = c;
        this.b = b;
        this.a = a;
    }

    public double getArea() {
        double s = (a + b + c) / 2;
        double ans = Math.pow(s * (s - a) * (s - b) * (s - c), 0.5);
        return ans;
    }

}

class Cylinder<E> {
    private double height;
    private E botton;

    public Cylinder(double height, E botton) {
        this.height = height;
        this.botton = botton;
    }

    public double getHeight() {
        return height;
    }

    public E getBottom() {
        return botton;
    }
}

public class pra251020_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n-- > 0) {
            String s = in.next();
            if (s.equals("Circle")) {
                double r = 0;
                r = in.nextDouble();
                Circle circle = new Circle(r);
                Cylinder<Circle> c = new Cylinder<>(10, circle);
                double area = c.getBottom().getArea();
                double v = c.getHeight() * area;
                System.out.println("The area of Circle is " +String.format("%.2f",  area));
                System.out.println("The volume of Circle is " + String.format("%.2f",  v));
            } else {
                double a = in.nextDouble();
                double b = in.nextDouble();
                double c = in.nextDouble();

                Triangle triangle = new Triangle(a, b, c);
                double area= triangle.getArea();
                double v = area * 10;
                System.out.println("The area of Triangle is " +String.format("%.2f",  area));
                System.out.println("The volume of Triangle is " + String.format("%.2f",  v));

            }

        }
    }
}
