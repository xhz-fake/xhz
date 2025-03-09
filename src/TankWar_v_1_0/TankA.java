package TankWar_v_1_0;

import javax.swing.*;
import java.awt.*;

public class TankA extends MoveObjects{
    private final ImageIcon[] imgArr=new ImageIcon[4];

    public TankA(int x, int y) {
        super(x, y);
        setWidth(55);
        setHeight(42);
        setDirection(1);

        for(int i=0;i<4;i++) {
            imgArr[i] = new ImageIcon("D:\\桌面\\Xing\\Photos\\TankA"+i+".png");
        }
    }

    public void drawTankA(Graphics2D g2d){
        switch(getDirection()){
            case(0)://左
                g2d.setColor(Color.WHITE);
                g2d.fillRect(getX()+5,getY()+5,getWidth()+4,getHeight()+4);
                g2d.drawImage(imgArr[0].getImage(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case(1)://上
                g2d.setColor(Color.WHITE);
                g2d.fillRect(getX()+5,getY()+5,getHeight()+4,getWidth()+4);
                g2d.drawImage(imgArr[1].getImage(),getX(),getY(),getHeight(),getWidth(),null);
                break;
            case(2)://右
                g2d.setColor(Color.WHITE);
                g2d.fillRect(getX()-5,getY()-5,getWidth()+4,getHeight()+4);
                g2d.drawImage(imgArr[2].getImage(),getX(),getY(),getWidth(),getHeight(),null);
                break;
            case(3)://下
                g2d.setColor(Color.WHITE);
                g2d.fillRect(getX()-5,getY()-5,getHeight()+4,getWidth()+4);
                g2d.drawImage(imgArr[3].getImage(),getX(),getY(),getHeight(),getWidth(),null);
                break;
        }
    }

}
