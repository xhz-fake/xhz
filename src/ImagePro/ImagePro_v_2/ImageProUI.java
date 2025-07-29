package ImagePro.ImagePro_v_2;

import javax.swing.*;
import java.awt.*;

public class ImageProUI {
    ImageListener il = new ImageListener();////////////////////////////////////////////

    public void showUI() {
        JFrame jf = new JFrame();
        jf.setSize(1000, 800);
        jf.setLocation(300, 50);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setTitle("美颜相机_v_2");
        jf.setLayout(new FlowLayout());

        JLabel pathJla = new JLabel("图片地址：");
        JTextField pathJtf = new JTextField(30);
        jf.add(pathJla);
        jf.add(pathJtf);

        String[] btnTexts = {"加载图片", "原图", "圆点马赛克", "灰度", "二值化", "暖色"
                , "油画", "轮廓化", "热成像", "图片融合"};
        for (int i = 0; i < btnTexts.length; i++) {
            JButton btn = new JButton(btnTexts[i]);
            btn.setBackground(Color.WHITE);
            jf.add(btn);
            btn.addActionListener(il);//////////////////////////////////////////////////
        }
        JLabel imgPathJla1 = new JLabel("融合图片地址：");
        JTextField imgPathJtf = new JTextField(30);
        imgPathJtf.setText("d:\\桌面\\bfe4344e02876b58fd4c179dc1f9698.png");
        jf.add(imgPathJla1);
        jf.add(imgPathJtf);

        JLabel gjla = new JLabel("灰度调节：");
        jf.add(gjla);
        JSlider grayJsl = new JSlider(0, 255, 130);
        jf.add(grayJsl);
        grayJsl.addChangeListener(il);//////////////////////////////////////////////////

        jf.setVisible(true);


        Graphics g = jf.getGraphics();
        il.g1 = g;
        il.pathJtf1 = pathJtf;
        il.imgPathJtf1 = imgPathJtf;
        il.grayJsl1 = grayJsl;
    }

    public static void main(String[] args) {
        ImageProUI IP = new ImageProUI();
        IP.showUI();
    }


}
