package TankWar.TankWar_v_1;

import java.awt.*;

public class GameThreadB implements Runnable{
    private final TankB tankB;
    private final Graphics2D g2d;

    public GameThreadB(TankB tankB, Graphics2D g2d){
        this.tankB=tankB;
        this.g2d=g2d;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tankB.drawTankB(g2d);
        }
    }
}
