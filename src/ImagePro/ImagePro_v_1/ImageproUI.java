package ImagePro.ImagePro_v_1;

import javax.swing.*;
import java.awt.*;

public class ImageproUI {
    public void showUI() {
        JFrame jf = new JFrame();
        jf.setTitle("美颜相机v1.0");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(300, 50);
        jf.setLayout(new FlowLayout());
        jf.setSize(800, 700);

        JButton btn1 = new JButton("原图");
        JButton btn2 = new JButton("马赛克");

        jf.add(btn1);
        jf.add(btn2);
        jf.setVisible(true);

        ImageAction ial = new ImageAction();
        btn1.addActionListener(ial);
        btn2.addActionListener(ial);

        Graphics g = jf.getGraphics();
        ial.g2 = g;

    }

    public static void main(String[] args) {
        ImageproUI ui = new ImageproUI();
        ui.showUI();

    }
}
