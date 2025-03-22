package TankWar_v_2_0;

import javax.swing.*;
import java.awt.*;

public class scorePanel extends JPanel {
    private final ImageIcon[] imgPic = new ImageIcon[2];

    public void drawTankPicture(Graphics2D g2d) {
        imgPic[0] = new ImageIcon("D:\\桌面\\Xing\\Photos\\TankB.png");
        imgPic[1] = new ImageIcon("D:\\桌面\\Xing\\Photos\\TankA.png");
        g2d.drawImage(imgPic[0].getImage(), 230, 830, 100, 60, null);
        g2d.drawImage(imgPic[1].getImage(), 830, 830, 100, 60, null);
    }

}
