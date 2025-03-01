package gameThread;

import java.awt.*;

public class GameThread extends Thread {
    public Graphics g;
    public int x,y;
    public int pa,pb;
    public GameThread(Graphics g,int x,int y,int pa,int pb){
        this.g=g;
        this.x=x;
        this.y=y;
        this.pa=pa;
        this.pb=pb;
    }
    public void run(){
        while (true){//无限运动
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            g.setColor(Color.white);
            g.fillOval(x++,y++,11,11);
            g.setColor(Color.BLACK);
            g.fillOval(x,y,10,10);
        }
    }

}
