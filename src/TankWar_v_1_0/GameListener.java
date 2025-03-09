package TankWar_v_1_0;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameListener implements ActionListener, KeyListener {
    private TankA tankA;
    private TankB tankB;
    private final Graphics2D g2d;
    private GameThreadA gtA;
    private GameThreadB gtB;
    private final Random ran=new Random();

    public GameListener(Graphics2D g2d) {
        this.g2d = g2d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println("按下了" + key + "键");
        switch (key) {
            case KeyEvent.VK_SPACE:
                if (gtA == null&&gtB==null) {
                    tankA = new TankA(50 + ran.nextInt(900), 50 + ran.nextInt(700));
                    tankB = new TankB(50 + ran.nextInt(900), 50 + ran.nextInt(700));
                    gtA = new GameThreadA(tankA, g2d);
                    gtB=new GameThreadB(tankB,g2d);
                    new Thread(gtA).start();
                    new Thread(gtB).start();
                }
                break;
            case KeyEvent.VK_A:
                tankA.setDirection(0);
                tankA.setSpeedX(-5);
                tankA.move();
                break;
            case KeyEvent.VK_W:
                tankA.setDirection(1);
                tankA.setSpeedY(-5);
                tankA.move();
                break;
            case KeyEvent.VK_D:
                tankA.setDirection(2);
                tankA.setSpeedX(5);
                tankA.move();
                break;
            case KeyEvent.VK_S:
                tankA.setDirection(3);
                tankA.setSpeedY(5);
                tankA.move();
                break;
            case KeyEvent.VK_Q:

            case KeyEvent.VK_LEFT:
                tankB.setDirection(0);
                tankB.setSpeedX(-5);
                tankB.move();
                break;
            case KeyEvent.VK_UP:
                tankB.setDirection(1);
                tankB.setSpeedY(-5);
                tankB.move();
                break;
            case KeyEvent.VK_RIGHT:
                tankB.setDirection(2);
                tankB.setSpeedX(5);
                tankB.move();
                break;
            case KeyEvent.VK_DOWN:
                tankB.setDirection(3);
                tankB.setSpeedY(5);
                tankB.move();
                break;
            case KeyEvent.VK_M:
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        tankA.setSpeedX(0);
        tankA.setSpeedY(0);
        tankB.setSpeedX(0);
        tankB.setSpeedY(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
