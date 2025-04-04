package TankWar_v_2_0;

import javax.swing.*;
import java.awt.*;

public class TankB extends MoveObjects {
    private final ImageIcon[] imgArr=new ImageIcon[4];

    public TankB(int x, int y) {
        super(x, y);
        setWidth(45);
        setHeight(35);
        setDirection(1);

        for(int i=0;i<4;i++) {
            imgArr[i] = new ImageIcon("D:\\桌面\\Xing\\Photos\\TankB"+i+".png");
        }
    }

    public void drawTankB(Graphics2D g2d){
        switch(getDirection()){
            case(0):
                g2d.drawImage(imgArr[0].getImage(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case(1):
                g2d.drawImage(imgArr[1].getImage(),getX(),getY(),getHeight(),getWidth(),null);
                break;
            case(2):
                g2d.drawImage(imgArr[2].getImage(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case(3):
                g2d.drawImage(imgArr[3].getImage(),getX(),getY(),getHeight(),getWidth(),null);
                break;
        }
    }

}
