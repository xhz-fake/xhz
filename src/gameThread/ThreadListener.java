package gameThread;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ThreadListener extends MouseAdapter {
    public Graphics g;
    public Point pa = new Point(100, 100);


    public ThreadListener(Graphics g) {
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int pax = pa.x;
        int pay = pa.y;

        GameThread gt = new GameThread(g, x, y,pax,pay);
        gt.start();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
