package Perceptron;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViPerceptron_v_2_0 extends JFrame {
    private static final int R = 4;
    private final int WIDTH = 800, HEIGHT = 600;
    private final List<double[]> trainingData = new ArrayList<>();
    private final NeuralNetwork neuralNetwork;
    private int trainCount = 0;
    private final JLabel statusLabel;  //新增状态标签

    public ViPerceptron_v_2_0() { //GUI界面实现
        setTitle("神经网络可视化训练 v2.1");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        initNonLinearData();// 初始化数据
        neuralNetwork = new NeuralNetwork(2, 8, 1, 0.1);

        // 状态标签初始化
        statusLabel = new JLabel("状态: 等待开始");
        statusLabel.setForeground(new Color(0, 0, 0));
        add(statusLabel);

        JButton trainButton = new JButton("开始训练");
        trainButton.addActionListener(e -> {
            statusLabel.setText("状态: 训练中...");
            long startTime = System.currentTimeMillis();

            trainNetwork();// 训练神经网络
            repaint();// 触发界面重绘
            trainCount++;


            long duration = System.currentTimeMillis() - startTime;
            trainButton.setText("训练" + trainCount + "次");
            showTrainingComplete(duration);  // 显示完成提示
        });
        add(trainButton);

        setVisible(true);
    }

    // 训练完成提示
    private void showTrainingComplete(long duration) {
        double accuracy = calculateAccuracy();
        String status = String.format("状态: 第%d次训练 | 准确率: %.2f%% | 耗时: %dms",
                trainCount, accuracy, duration);
        statusLabel.setText(status);

        if (accuracy >=99.9) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this,
                        "训练报告\n\n" +
                                "训练总次数: " + trainCount + "\n" +
                                "当前准确率: " + String.format("%.2f", accuracy) + "%\n" +
                                "当前耗时: " + duration + "ms",
                        "训练完成",
                        JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    // 准确率计算方法
    private double calculateAccuracy() {
        int correct = 0;
        for (double[] data : trainingData) {
            double[] inputs = {data[0] / WIDTH, data[1] / HEIGHT};
            double prediction = neuralNetwork.predict(inputs)[0];
            int predictedLabel = prediction > 0.5 ? 1 : -1;
            if (predictedLabel == (int) data[2]) {
                correct++;
            }
        }
        return (correct * 100.0) / trainingData.size();
    }

    // 初始化环形分布数据（点阵）
    private void initNonLinearData() {
        Random random = new Random();
        int centerX = WIDTH / 2, centerY = HEIGHT / 2, radius = 150;

        for (int i = 0; i < 200; i++) {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            double dist = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
            int label = dist < radius ? 1 : -1;
            trainingData.add(new double[]{x, y, label});
        }
    }

    // 正确绘图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawDecisionBoundary(g);
        drawDataPoints(g);
    }

    // 绘制数据点
    private void drawDataPoints(Graphics g) {
        for (double[] data : trainingData) {
            int x = (int) data[0];
            int y = (int) data[1];
            int label = (int) data[2];
            g.setColor(label == 1 ? new Color(255, 50, 50) : new Color(50, 50, 200));
            g.fillOval(x - R, y - R, 2 * R, 2 * R);
        }
    }

    // 绘制决策边界（网格背景）
    private void drawDecisionBoundary(Graphics g) {
        int gridSize = 4;
        for (int x = 0; x < WIDTH; x += gridSize) {
            for (int y = 0; y < HEIGHT; y += gridSize) {
                double[] inputs = {(double) x / WIDTH, (double) y / HEIGHT};// 归一化:将像素坐标转换为0-1范围
                //神经网络在训练时使用的输入数据是归一化的，预测时也必须保持相同的输入范围，否则会导致预测结果偏差。
                double output = neuralNetwork.predict(inputs)[0];//predict必须要返回一个数组，所以此处需要添加[0]
                Color areaColor = output > 0.5 ?//输入归一化后的坐标，返回预测值 output（经过Sigmoid后的概率值，范围 [0, 1]）
                        new Color(255, 150, 150, 150) :
                        new Color(150, 150, 255, 150);
                g.setColor(areaColor);
                g.fillRect(x, y, gridSize, gridSize);
            }
        }
    }

    // 训练神经网络
    private void trainNetwork() {
        for (int epoch = 0; epoch < 400; epoch++) {// 每点击一次按钮训练400次
            for (double[] data : trainingData) {
                double[] inputs = {data[0] / WIDTH, data[1] / HEIGHT};// 归一化输入将像素坐标映射到神经网络输入范围 [0, 1]
                double[] targets = {(data[2] + 1) / 2};// 映射到0-1 的范围
                neuralNetwork.train(inputs, targets);
            }
        }
    }
    static class NeuralNetwork {
        private final int inputSize;// 输入层神经元数（2）（对应x,y坐标）
        private final int hiddenSize;// 隐藏层神经元数（8）
        private final int outputSize;// 输出层神经元数（1）（二分类问题）
        private final double[][] w1, w2;// 权重矩阵（输入到隐藏，隐藏到输出）
        //权重矩阵用于定义每一层之间神经元之间的连接强度。具体来说，w1 和 w2 分别对应于
        // 输入层到隐藏层 和 隐藏层到输出层 之间的连接。它们的作用是将输入信息传递到下一层，并进行加权求和。
        private final double[] b1, b2;// 偏置向量
        private final double learningRate;

        public NeuralNetwork(int inputSize, int hiddenSize, int outputSize, double lr) {
            this.inputSize = inputSize;
            this.hiddenSize = hiddenSize;
            this.outputSize = outputSize;
            this.learningRate = lr;//将传递给构造函数的参数赋值给类中的成员变量

            // 权重初始化（Xavier初始化）
            w1 = new double[inputSize][hiddenSize];
            w2 = new double[hiddenSize][outputSize];
            b1 = new double[hiddenSize];
            b2 = new double[outputSize];

            Random rand = new Random();
            // Xavier初始化（保持方差一致）
            //保持权重的方差在一个合适的范围内，以避免梯度消失或梯度爆炸问题。
            double scale = Math.sqrt(2.0 / (inputSize + hiddenSize));
            for (int i = 0; i < inputSize; i++) {
                for (int j = 0; j < hiddenSize; j++) {
                    w1[i][j] = rand.nextGaussian() * scale;
                    //对于每个连接，使用 rand.nextGaussian()
                    // 生成一个服从标准正态分布（均值为0，方差为1）的随机数，并乘以缩放因子 scale，然后将结果赋值
                    // 给 w1 矩阵的相应元素。这样每个权重就从一个标准差为 scale 的正态分布中随机采样。这样确保了
                    // 权重的初始化具有适当的方差，从而使得神经网络的训练更加稳定。
                }
            }
            scale = Math.sqrt(2.0 / (hiddenSize + outputSize));
            for (int i = 0; i < hiddenSize; i++) {
                for (int j = 0; j < outputSize; j++) {
                    w2[i][j] = rand.nextGaussian() * scale;
                }
            }
        }

        private double sigmoid(double x) {
            return 1 / (1 + Math.exp(-x));
        }

        private double sigmoidDerivative(double sigmoid) {
            return sigmoid * (1 - sigmoid);//在反向传播过程中，需要计算每个神经元的输出相对于其输入的梯度，
            // 以便更新权重和偏置。Sigmoid 函数的导数提供了这个梯度信息。
            // 具体来说，对于每个神经元，我们需要计算其输出相对于其输入的变化率。Sigmoid 函数的导数就是这个变化率。
        }

        public double[] predict(double[] inputs) {
            // 前向传播
            double[] hidden = new double[hiddenSize];
            for (int j = 0; j < hiddenSize; j++) {
                for (int i = 0; i < inputSize; i++) {
                    hidden[j] += inputs[i] * w1[i][j];
                }
                hidden[j] = sigmoid(hidden[j] + b1[j]);
            }

            double[] output = new double[outputSize];
            for (int k = 0; k < outputSize; k++) {
                for (int j = 0; j < hiddenSize; j++) {
                    output[k] += hidden[j] * w2[j][k];
                }
                output[k] = sigmoid(output[k] + b2[k]);
            }
            return output;
        }

        public void train(double[] inputs, double[] targets) {
            // 前向传播
            double[] hidden = new double[hiddenSize];
            for (int j = 0; j < hiddenSize; j++) {
                for (int i = 0; i < inputSize; i++)
                    hidden[j] += inputs[i] * w1[i][j];
                hidden[j] = sigmoid(hidden[j] + b1[j]);
            }

            double[] output = new double[outputSize];
            for (int k = 0; k < outputSize; k++) {
                for (int j = 0; j < hiddenSize; j++)
                    output[k] += hidden[j] * w2[j][k];
                output[k] = sigmoid(output[k] + b2[k]);
            }

            // 反向传播
            //输出层误差
            double[] outputErrors = new double[outputSize];
            for (int k = 0; k < outputSize; k++)
                outputErrors[k] = (targets[k] - output[k]) * sigmoidDerivative(output[k]);
            //损失函数的导数乘以激活函数的导数，得到输出层的误差。
            //隐藏层误差
            double[] hiddenErrors = new double[hiddenSize];
            for (int j = 0; j < hiddenSize; j++) {
                double error = 0;
                for (int k = 0; k < outputSize; k++)
                    error += outputErrors[k] * w2[j][k];
                hiddenErrors[j] = error * sigmoidDerivative(hidden[j]);//其损失函数来自上一层的误差与权重的乘积
            }

            // 更新权重和偏置
            for (int j = 0; j < hiddenSize; j++) {
                for (int k = 0; k < outputSize; k++) {
                    w2[j][k] += learningRate * outputErrors[k] * hidden[j];
                }
            }

            for (int i = 0; i < inputSize; i++) {
                for (int j = 0; j < hiddenSize; j++) {
                    w1[i][j] += learningRate * hiddenErrors[j] * inputs[i];//梯度下降的核心，通过调整权重来最小化损失函数
                }
            }

            // 更新偏置
            for (int k = 0; k < outputSize; k++)
                b2[k] += learningRate * outputErrors[k];

            for (int j = 0; j < hiddenSize; j++)
                b1[j] += learningRate * hiddenErrors[j];
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViPerceptron_v_2_0::new);//将 GUI 初始化任务提交到 Swing
        // 的事件调度线程（Event Dispatch Thread, EDT）
        //Swing 的组件（如窗口、按钮等）是非线程安全的,所有 GUI 操作必须在 EDT 中执行，否则可能导致：
        //1,界面卡顿  2,组件渲染异常  3,线程竞争导致程序崩溃
    }
}

