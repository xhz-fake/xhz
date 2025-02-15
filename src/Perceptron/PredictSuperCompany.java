package Perceptron;

import java.util.Scanner;

public class PredictSuperCompany {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入四个参数：\n 1. 代码行数(0~30)(万)\n 2. 学校成绩(0~4.5)\n" +
                " 3. 大厂朋辈：(0~20)\n 4. 院校级别：(普本：-0.7；重点：-0.5；顶尖：-0.3)\n");
        System.out.println("注：平均占比分别为\n 1. (55%)  2. (30%)  3. (15%)  ");

        int line = in.nextInt();
        double score = in.nextDouble();
        int friends = in.nextInt();
        double level = in.nextDouble();
        int ans;

        double[] input = {line, score, friends};
        double[] w = {0.02,0.05,0.015};
        double sum = 0;
        for(int i = 0; i < 3; i++){
            sum += input[i] * w[i];
        }
        double b=0.915*level;
        sum+=b;
        ans=sum>=0 ? 1:0;
        switch (ans){
            case 1:
                System.out.println("恭喜你进入大厂人行列！");
                break;
            case 0:
                System.out.println("谢谢你！下去沉淀沉淀吧！");
                break;
        }
    }
}
