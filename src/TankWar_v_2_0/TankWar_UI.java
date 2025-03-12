package TankWar_v_2_0;

import javax.swing.*;
import java.awt.*;

public class TankWar_UI {
    public void initUI(){
        JFrame jf=new JFrame();
        jf.setSize(1150,950);
        jf.setTitle("---Tank War---");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocation(320,70);

        GamePanel gamePanel =new GamePanel();
        gamePanel.setBackground(Color.WHITE);
        jf.add(gamePanel,BorderLayout.CENTER);

        jf.setVisible(true);
        gamePanel.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            TankWar_UI tankWarUi=new TankWar_UI();
            tankWarUi.initUI();
        });
    }
}
