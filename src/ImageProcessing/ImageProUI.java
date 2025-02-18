package ImageProcessing;

import javax.swing.*;
import java.awt.*;

public class ImageProUI {

    ImageListener imgl=new ImageListener();//创建监听器对象
    // 展示UI
    public void showUI() {
        //创建总窗体
        JFrame jf = new JFrame();
        jf.setTitle("美颜相机v2.0");
        jf.setSize(1000, 800);
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
        imgPanel.addImageL(imgl);//将监听器添加到图像面板中
        imgl.imagePanel=imgPanel;

    }

    public void initBtnPanel(JPanel btnPanel){//初始化按钮面板
        String[] btnTexts={"打开","原图","保存","马赛克","灰度"};
        for (int i = 0; i < btnTexts.length; i++) {
            JButton btn=new JButton(btnTexts[i]);
            btn.setBackground(Color.white);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
        String[] btnTests2={"画笔","直线","矩形","截图","马赛克笔"};
        for (int i = 0; i < btnTests2.length; i++) {
            JButton btn=new JButton(btnTests2[i]);
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
