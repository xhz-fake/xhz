package Net_Interaction;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MyClient {
    public static void main(String[] args) {
        System.out.println("-客户端已启动-");

        try {
            //1. 创建客户端socket 并连接到服务器
            Socket socket = new Socket("localHost", 8881);// 连接服务器
            System.out.println("已连接到服务器,可以开始聊天啦!");
            System.out.println("输入:bye 以结束聊天噢");

            //2. 创建输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //3. 创建控制台输入
            Scanner scanner = new Scanner(System.in);

            //4. 启动"接受"对方信息(服务器)的线程
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        if (serverMessage.equalsIgnoreCase("bye")) {
                            System.out.println("服务器已退出聊天,聊天结束喽");
                            System.exit(0);
                        }
                        System.out.println("服务端说 : " + serverMessage);
                        //System.out.print("我(客户端)说: ");
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("连接超时...");
                } catch (ConnectException e) {
                    System.out.println("连接拒绝...");
                } catch (SocketException e) {
                    System.out.println("连接意外断开...");
                } catch (IOException e) {
                    System.out.println("I/O错误: " + e.getMessage());
                }
            }).start();

            //5. 主线程:发送信息
            while (true) {
                //System.out.println("我(客户端)说: ");
                String message = scanner.nextLine();
                out.println(message);
                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("聊天结束了");
                    break;
                }
            }

            //6. 关闭线程
            scanner.close();
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("客户端出错: " + e.getMessage());
        }
    }
}
