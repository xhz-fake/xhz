package Perceptron;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViPerceptron extends JFrame {
    private static final int R = 4;//画点的半径
    private final int WIDTH = 800, HEIGHT = 600;//界面宽和高
    // 训练数据，每个元素包含 [x, y, label]
    private List<double[]> trainingData;
    private final Perceptron perceptron; // 感知机实例
    private Graphics g;//界面上的画布
    private int count = 0;//记录手动训练的次数
    private JButton trainButton;//点此按钮即训练一次

    public ViPerceptron() {//负责GUI窗口、数据生成、训练按钮交互、绘图逻辑
        setTitle("感知机可视化训练过程展示 v1.0");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        initTrainingData(); // 生成50个训练点
//创建感知机对象
        perceptron = new Perceptron(2, 0.1);
        trainButton = new JButton("开始训练");
        trainButton.addActionListener(e -> {
            g.clearRect(0, 0, WIDTH, HEIGHT); // 清空画布
            trainPerceptron();                // 执行训练
            drawDataPoints(g);                // 绘制数据点
            drawDecisionBoundary(g);          // 绘制决策线
            count++;                          // 更新训练次数
            trainButton.setText("训练" + count + "次");
        });
        add(trainButton);
        setVisible(true);
        g = getGraphics();//取得界面画布
    }

    //初始化，模拟生成平面上的点数据，存入队列，做训练样本
    private void initTrainingData() {
        trainingData = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            int threshold = 600;
            int label = (x + y > threshold) ? 1 : -1;
            // 标签规则：x + y > 700 → label=1，否则label=-1
            trainingData.add(new double[]{x, y, label});
        }
    }

    //将生成的点，在界面上画出来
    private void drawDataPoints(Graphics g) {
        for (double[] data : trainingData) {
            int x = (int) data[0];
            int y = (int) data[1];
            int label = (int) data[2];
            g.setColor(label == 1 ? new Color(220, 31, 31) : new Color(25, 116, 191));
            g.fillOval(x - R, y - R, 2 * R, 2 * R);
        }
    }

    //画出每次训练后，显示分类的线
    private void drawDecisionBoundary(Graphics g) {
        double[] weights = perceptron.getWeights();
        double bias = perceptron.getBias();
        if (weights != null && weights.length == 2) {
            if (weights[1] == 0) return; // 避免除以零
            int x1 = 0;
            int y1 = (int) (-(weights[0] * x1 + bias) / weights[1]);
            int x2 = WIDTH;
            int y2 = (int) (-(weights[0] * x2 + bias) / weights[1]);
            //决策边界公式：y = -(w1 * x + b) / w2
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    //训练方法
    private void trainPerceptron() {
        boolean converged = false;
        for (int epoch = 0; epoch < 300; epoch++) {
            boolean epochConverged = true; // 用来判断本轮迭代是否收敛
            for (double[] data : trainingData) {
                double[] inputs = {data[0], data[1]};//输入训练时的随机生成的x，y
                int label = (int) data[2];
                int prediction = perceptron.predict(inputs);
                if (prediction != label) {
                    epochConverged = false;
                }
                perceptron.train(inputs, label);
            }
            if (epochConverged) {
              converged = true;
              break;
            }
        }
        if (converged) {
            JOptionPane.showMessageDialog(this, "训练完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //显示界面，构造器中己可显
        ViPerceptron vp = new ViPerceptron();
    }
}

//定义感知机类，可以生成感知机对象
  class Perceptron {//实现感知机算法（初始化、预测、训练）
    private double[] weights;
    private double bias;
    private double learningRate;

    public Perceptron(int inputSize, double learningRate) {
        weights = new double[inputSize];
        this.learningRate = learningRate;
        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random() - 0.5;//初始化权重
        }
        bias = Math.random() - 0.5;//初始化偏置
    }

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }

    //激活函数
    private int activationFunction(double weightedSum) {
        return weightedSum >= 0 ? 1 : -1;
    }

    //预测函数,计算加权和并通过激活函数（阶跃函数）输出预测值（1或-1）
    public int predict(double[] inputs) {
        double weightedSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += weights[i] * inputs[i];
        }
        weightedSum += bias;//w1*x+w2*y+b=wightedSum
        return activationFunction(weightedSum);
    }

    //对随机生成的每一个点（已知标签）进行训练
    public void train(double[] inputs, int label) {//根据预测值与真实标签的误差调整权重和偏置
        int prediction = predict(inputs);
        int error = label - prediction;
        if (prediction != label) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * error * inputs[i];
            }
            bias += learningRate * error;
        }
    }
}

/*
用户->>ViPerceptron: 启动程序
ViPerceptron->>ViPerceptron: 初始化窗口、数据、模型
ViPerceptron->>用户: 显示界面和初始数据点
loop 用户点击训练按钮
用户->>ViPerceptron: 点击“开始训练”按钮
ViPerceptron->>ViPerceptron: 清空画布
ViPerceptron->>Perceptron: 执行100次epoch训练
Perceptron->>Perceptron: 遍历数据点更新权重
ViPerceptron->>ViPerceptron: 重新绘制数据点和决策线
ViPerceptron->>用户: 更新界面显示
        end
*/