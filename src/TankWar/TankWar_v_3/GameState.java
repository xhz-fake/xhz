package TankWar.TankWar_v_3;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {//Serializable 是一个“空接口”，但它是 Java 对象能否被序列化的“通行证”。
    TankA tankA;
    TankB tankB;
    ArrayList<Bullet> bullets;

    public GameState(TankA tankA, TankB tankB, ArrayList<Bullet>bullets) {
        this.tankA = tankA;
        this.bullets = bullets;
        this.tankB = tankB;
    }

    private static final long serialVersionUID = 1L;
}
