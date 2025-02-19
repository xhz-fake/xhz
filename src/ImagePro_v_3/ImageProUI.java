package ImagePro_v_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;

public class ImageProUI {//本类主要负责基本框架UI界面的展示

    ImageListener imgl=new ImageListener();//创建监听器对象
    protected static int UI_WIDTH=1000;
    protected static int UI_HEIGHT=800;
    // 展示UI
    public void showUI() {
        //创建总窗体
        JFrame jf = new JFrame();
        jf.setTitle("美颜相机v2.0");
        jf.setSize(UI_WIDTH, UI_HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        //创建面板
        JPanel btnPanel = new JPanel();
        ImagePanel imgPanel = new ImagePanel();

        //设置基本属性
        btnPanel.setBackground(Color.DARK_GRAY);
        imgPanel.setBackground(Color.white);

        //设置按钮面板大小
        Dimension dim = new Dimension(0, 100);
        btnPanel.setPreferredSize(dim);

        initBtnPanel(btnPanel);//初始化按钮界面

        //设置图像和按钮的布局
        jf.add(btnPanel, BorderLayout.SOUTH);
        jf.add(imgPanel,BorderLayout.CENTER);

        jf.setVisible(true);

        imgl.imagePanel=imgPanel;//ImageListener 中的 imagePanel对象指向了 ImageProUI 中创建的 imgPanel 对象。
        imgPanel.passImageL(imgl);//将 ImageProUI 中的 imgl 对象传递给了 ImagePanel 中的 imgl 变量。

        jf.addComponentListener(imgl);
    }

    public void initBtnPanel(JPanel btnPanel){//初始化按钮面板
        String[] btnTexts={"打开","原图","保存","马赛克","灰度"};
        for (String btnText : btnTexts) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.white);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
        String[] btnTests2={"画笔","直线","矩形","截图","马赛克笔"};
        for (String btnText : btnTests2) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.LIGHT_GRAY);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
    }

    public static void main(String[] args) {
        ImageProUI imageProUI = new ImageProUI();
        imageProUI.showUI();
    }
}
