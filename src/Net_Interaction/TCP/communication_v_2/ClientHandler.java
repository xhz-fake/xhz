package Net_Interaction.TCP.communication_v_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final String clientInfo;
    private final Consumer<String> messageConsumer;
    private Runnable onDisconnect;
    private volatile boolean running = true;

    public ClientHandler(Socket socket,
                         Consumer<String> messageConsumer,
                         Runnable onDisconnect) throws IOException {
        this.socket = socket;
        this.messageConsumer = messageConsumer;
        this.onDisconnect = onDisconnect;
        this.clientInfo = socket.getInetAddress() + ":" + socket.getPort();
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setOnDisconnectCallback(Runnable callback) {
        this.onDisconnect = callback;
    }

    @Override
    public void run() {
        try {
            String message;
            while (running && (message = in.readLine()) != null) {
                messageConsumer.accept(message);
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("客户端连接异常: " + clientInfo + " - " + e.getMessage());
            }
        } finally {
            close();
            onDisconnect.run();
        }
    }

    public void sendMessage(String message) {
        if (running) {
            out.println(message);
        }
    }

    public void close() {
        running = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("关闭客户端连接时出错: " + e.getMessage());
        }
    }
}