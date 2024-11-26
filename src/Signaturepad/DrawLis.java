package Signaturepad;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class DrawLis implements java.awt.event.MouseMotionListener {
    Graphics g;

    public DrawLis(Graphics g){
        this.g = g;
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        int y = e.getY();
        Random ran = new Random();
   //     int c1 = ran.nextInt(255);
        //    int c2 = ran.nextInt(255);
        for (int i = 0; i < 255; i++) {
            int c1 = ran.nextInt(255);
            int c2 = ran.nextInt(255);
            int c3 = ran.nextInt(255);
            Color c = new Color(c1,c2,c3 );
            g.setColor(c);
            g.fillRect(x+i/7,y+i/8,15-i/5,15-i/6);
        }

    }
public void mouseMoved(MouseEvent event){

}

}