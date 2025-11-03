package Practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Complex {
    private int realPart;
    private int imagePart;

    public Complex(int realPart, int imagePart) {
        this.realPart = realPart;
        this.imagePart = imagePart;
    }

    // 默认构造函数，实部和虚部都置为1
    public Complex() {
        this.realPart = 1;
        this.imagePart = 1;
    }

    // 获取实部
    public int getRealPart() {
        return realPart;
    }

    // 获取虚部
    public int getImagePart() {
        return imagePart;
    }


    public String toString() {
        String s = realPart + "+" + imagePart + "i";
        return s;
    }

}

class ComplexComparator implements Comparator<Complex> {

    @Override
    public int compare(Complex o1, Complex o2) {
        // 先比较实部
        int realComparison = Integer.compare(o1.getRealPart(), o2.getRealPart());

        // 如果实部相等，则比较虚部
        if (realComparison == 0) {
            return Integer.compare(o1.getImagePart(), o2.getImagePart());
        }

        return realComparison;
    }
}

public class pra251020_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Complex> complexes = new ArrayList<>();
        // 读取输入
        while (true) {
            String s = in.nextLine().trim();
            // 如果输入"q"，则退出循环
            if (s.equals("q")) {
                break;
            }
            String[] parts = s.split(" ");
            int realPart = Integer.parseInt(parts[0]);
            int imagePart = Integer.parseInt(parts[1]);
            Complex complex = new Complex(realPart, imagePart);
            complexes.add(complex);
        }
        // 使用ComplexComparator对复数列表进行排序
        complexes.sort(new ComplexComparator());

        // 输出排序后的结果
        for (Complex complex : complexes) {
            System.out.println(complex);
        }
    }
}