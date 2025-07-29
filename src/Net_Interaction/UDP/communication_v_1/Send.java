package Net_Interaction.UDP.communication_v_1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Send {
    public static void main(String[] args) throws Exception {
        //1.建DatagramSocket对象(扔苹果的人)
        DatagramSocket socket = new DatagramSocket();

        //2.创建DatagramPacket将数据进行打包
        byte[] bytes = "你好".getBytes();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 6666;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ip, port);//装苹果的框子

        //3.发送数据
        socket.send(dp);
        //4.释放资源
        socket.close();

    }
}
