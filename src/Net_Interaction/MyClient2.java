package Net_Interaction;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient2 {
    public static void main(String[] args) {
        System.out.println("正在连接服务器……");
        Socket socket = null;
        final InputStream inc;
        final OutputStream outc;
        try {
            //169
            socket = new Socket("192.168.50.26",6666);
            inc = socket.getInputStream();
            outc = socket.getOutputStream();
            System.out.println("服务器连接成功！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int maxLen = 128;

        Runnable writeR=new Runnable() {
            public void run() {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("：");
                    String command = scanner.nextLine();
                    try {
                        outc.write(command.length());
                        outc.write(command.getBytes());
                        outc.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        };

        Runnable readR=new Runnable() {
            public void run() {
                while (true) {
                    byte[] buffer= new byte[maxLen];
                    try {
                        inc.read(buffer);
                        if(buffer.length!=0){
                            System.out.println("服务端响应内容：");
                            String msg = new String(buffer);
                            System.out.println(msg);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        };

        new Thread(writeR).start();
        new Thread(readR).start();
    }
}
