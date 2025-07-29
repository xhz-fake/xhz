package Net_Interaction.UDP.communication_v_2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receive {
    public static void main(String[] args) throws Exception {
        //1.创建一个服务端对象(接苹果的人),需要知道扔苹果的人叫啥
        DatagramSocket socket = new DatagramSocket(6666);

        //2.创建一个数据包对象(接受苹果的框)
        byte[] bytes = new byte[1024 * 64];//用于储存传来的苹果
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);//创建接苹果的框子,需要设置接收什么,接收的东西有多大

        while (true) {
            //3.使用服务端对象正式接受客户端的数据
            socket.receive(dp);

            //4. 解析数据包
            byte[] bytes2 = dp.getData();//接收的数据
            int len = dp.getLength();//从数据包中获取多少个数据
            InetAddress address = dp.getAddress();//获取发送数据的主机
            int port = dp.getPort();//获取发送端的端口号

            String rs=new String(bytes2,0,len);
            System.out.println(rs);
            System.out.println(address.getHostAddress() + "..." + port);
            System.out.println("----------------------------------------------");
        }
    }
}
