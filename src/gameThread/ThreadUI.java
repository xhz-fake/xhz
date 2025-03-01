package gameThread;

import javax.swing.*;
import java.awt.*;

public class ThreadUI {
    public void showUI(){
        JFrame jf=new JFrame();
        jf.setTitle("多线程游戏test");
        jf.setLocation(500,300);
        jf.setSize(800,600);
        jf.setDefaultCloseOperation(3);
        jf.getContentPane().setBackground(Color.white);
        jf.setVisible(true);


        ThreadListener tl=new ThreadListener(jf.getGraphics());
        jf.addMouseListener(tl);

    }
    public static void main(String[] args) {
        ThreadUI tui=new ThreadUI();
        tui.showUI();
    }
}
