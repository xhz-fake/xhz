package Signaturepad;

import javax.swing.*;
import java.awt.*;

public class DrawPad extends JFrame {

    public void initUI() {
        this.setTitle("3D签名-v1.0");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300,50);
        this.setVisible(true);
        Graphics g = this.getGraphics();
        DrawLis dl = new DrawLis(g);
        this.addMouseMotionListener(dl);
    }

    public static void main(String[] args) {
        DrawPad dp= new DrawPad();
        dp.initUI();
    }
}