package TankWar_v_1_0;

import java.awt.*;

public class GameThreadA implements Runnable{
    private final TankA tankA;
    private final Graphics2D g2d;

    public GameThreadA(TankA tankA,Graphics2D g2d){
        this.tankA=tankA;
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
            tankA.drawTankA(g2d);
        }
    }
}
