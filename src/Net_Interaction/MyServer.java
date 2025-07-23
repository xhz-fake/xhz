package Net_Interaction;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MyServer {
    public static void main(String[] args) {
        System.out.println("-服务端已启动-");

        try {
            //1. 创建服务器Socket
            ServerSocket serverSocket = new ServerSocket(8881);// 绑定端口
            System.out.println("等待客户端连接中...");

            //2. 等待客户端连接
            Socket clientSocket = serverSocket.accept();// 阻塞等待连接
            System.out.println("客户端已连接!可以开始聊天了...");
            System.out.println("你可以输入: bye 以结束聊天");

            //3. 创建输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //4. 创建控制台输入
            Scanner scanner = new Scanner(System.in);

            //5. 启动接受信息的线程
            new Thread(() -> {
                try {
                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        if (clientMessage.equalsIgnoreCase("bye")) {
                            System.out.println("客户端已退出聊天,聊天结束喽");
                            System.exit(0);
                        }
                        System.out.println("客户端说: " + clientMessage);
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

            //6. 主线程:发送信息
            while (true) {
                //System.out.println("我(服务端)说: ");
                String message = scanner.nextLine();
                out.println(message);

                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("聊天结束了");
                    break;
                }
            }

            //7. 关闭线程
            scanner.close();
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("服务器出错: " + e.getMessage());
        }
    }
}
