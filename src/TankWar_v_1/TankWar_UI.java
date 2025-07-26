package TankWar_v_1;

import javax.swing.*;
import java.awt.*;

public class TankWar_UI {
    public void initUI(){
        JFrame jf=new JFrame();
        jf.setSize(1000,800);
        jf.setTitle("---Tank War---");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(400,160);

        JPanel gamePanel =new JPanel();
        gamePanel.setBackground(Color.WHITE);
        jf.add(gamePanel,BorderLayout.CENTER);

        jf.setVisible(true);

        Graphics g=gamePanel.getGraphics();
        Graphics2D g2d=(Graphics2D)g.create();
        GameListener glA=new GameListener(g2d);

        gamePanel.addKeyListener(glA);
        gamePanel.requestFocus();
    }

    public static void main(String[] args) {
        TankWar_UI tankWarUi=new TankWar_UI();
        tankWarUi.initUI();
    }
}
