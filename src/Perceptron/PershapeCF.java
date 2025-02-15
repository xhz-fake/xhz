package Perceptron;

import java.util.Scanner;
import java.util.*;

public class PershapeCF {
    // 随机初始化权重（-0.5~0.5）
    //权重随机初始化避免零初始化可能的收敛停滞问题。
    private double wx = Math.random() - 0.5;
    private double wy = Math.random() - 0.5;
    private double b = Math.random() - 0.5;
    private double learningRate = 0.1;

    private int activationFunction(double sum) {
        return sum >= 0 ? 1 : 0;
    }

    public int predict(Point point) {
        double sum = 0;
        sum += wx * point.x + wy * point.y + b;
        return activationFunction(sum);
    }

    public void train(List<Point> points, int counts) {
        Random random = new Random();
        for (int count = 0; count < counts; count++) {
            List<Point> shuffedPoints = new ArrayList<>(points);
            Collections.shuffle(shuffedPoints, random);
            for (Point point : shuffedPoints) {
                int prediction = predict(point);
                int error = point.label - prediction;
                wx += learningRate * error * point.x;
                wy += learningRate * error * point.y;
                b += learningRate * error;
            }
        }
    }

    public double calculateAccuracy(List<Point> points) {
        int correct = 0;
        for (Point point : points) {
            if (predict(point) == point.label) {
                correct++;
            }
        }
        return (double) correct / points.size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            double x = random.nextDouble() * 10;
            double y = random.nextDouble() * 10;
            int label = x + y < 5 ? 0 : 1;
            points.add(new Point(x, y, label));
        }

        PershapeCF pershapeCF = new PershapeCF();
        System.out.println(points);
        pershapeCF.train(points, 200);
        // 验证训练集准确率
        double accuracy = pershapeCF.calculateAccuracy(points);
        System.out.println("训练集准确率：" + accuracy * 100 + "%");

        System.out.println("请输入测试点坐标：");
        double x = in.nextDouble();
        double y= in.nextDouble();
        int label1 = x + y < 5? 0 : 1;

        Point testpoint = new Point(x, y, label1);
        int prediction = pershapeCF.predict(testpoint);
        System.out.println("点 (" + x +","+ y + ") 预测为：" + prediction+ " ,真实标签为：" + label1);
        // 修改输出逻辑，显示真实标签和预测结果
    }

    static class Point {
        double x;
        double y;
        int label;//  0||1

        public Point(double x, double y, int label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }

        public String toString() {
            return "class:" + label + "  x:" + x + "  y:" + y + "\n";
        }
    }
}
