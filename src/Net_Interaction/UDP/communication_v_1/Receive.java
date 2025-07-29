package Net_Interaction.UDP.communication_v_1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receive {
    public static void main(String[] args) throws Exception {
        //1.创建DatagramSocket 接苹果的人,需要知道扔苹果的人叫啥
        DatagramSocket socket = new DatagramSocket(6666);

        //2.接收数据包
        byte[] bytes = new byte[1024 * 64];//用于储存传来的苹果
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);//创建接苹果的框子,需要设置接收什么,接收的东西有多大
        socket.receive(dp);

        //3. 解析数据包
        byte[] data = dp.getData();//接收的数据
        int len = dp.getLength();//从数据包中获取多少个数据
        InetAddress address = dp.getAddress();//获取发送数据的主机
        int port = dp.getPort();//获取发送端的端口号

        System.out.println(new String(data, 0, len));
        System.out.println(address + "..." + port);

        //4.释放资源
        socket.close();
    }
}
