package TankWar.TankWar_v_3;

import java.io.Serializable;

public class NetworkMessage implements Serializable {
    final MessageType type;
    final Object data;

    public NetworkMessage(MessageType type, Object data) {
        this.type = type;
        this.data = data;
    }
    private static final long serialVersionUID = 1L;

}
