package Net_Interaction.UDP.communication_v_2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Send {
    public static void main(String[] args) throws Exception {
        //1.创建客户端对象(扔苹果的人), 不指定端口时，系统会自动分配一个临时可用端口，适合客户端使用。服务端则应显式绑定固定端口。
        DatagramSocket socket = new DatagramSocket();// 如果想要创建多个客户端实例,则使用默认端口(不显式定义)

        Scanner scanner = new Scanner(System.in);

        while (true) {
            //2.识别键入的消息并存入到bytes2中
            System.out.println("请说:");
            String msg = scanner.nextLine();
            if (msg.equals("bye")) {
                System.out.println("聊天结束啦!");
                socket.close();
                System.exit(0);
            }
            byte[] bytes = msg.getBytes();
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            int port = 6666;
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ip, port);//装苹果的框子

            //3.发送数据
            socket.send(dp);
        }

    }
}
