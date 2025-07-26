package Net_Interaction.communication_v_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Date;

public class ChatClient extends JFrame {
    private JTextArea chatArea;
    private JTextArea messageArea;
    private JButton connectButton;
    private JButton sendButton;
    private JTextField serverField;
    private JTextField portField;
    private JTextField usernameField;
    private Socket socket;
    private PrintWriter out;
    private Thread receiveThread;
    private String username;

    public ChatClient() {
        super("聊天客户端");
        initUI();
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 聊天区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBorder(BorderFactory.createTitledBorder("聊天内容"));

        // 消息发送区域
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createTitledBorder("发送消息"));
        messageArea = new JTextArea(3, 20);
        messageArea.setLineWrap(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);

        sendButton = new JButton("发送");
        sendButton.setEnabled(false);
        sendButton.addActionListener(e -> sendMessage());

        messagePanel.add(messageScrollPane, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // 连接面板
        JPanel connectPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        connectPanel.setBorder(BorderFactory.createTitledBorder("连接设置"));

        serverField = new JTextField("localhost");
        portField = new JTextField("8888");
        usernameField = new JTextField("匿名用户");

        connectButton = new JButton("连接服务器");
        connectButton.addActionListener(e -> connectToServer());

        connectPanel.add(new JLabel("服务器:"));
        connectPanel.add(serverField);
        connectPanel.add(new JLabel("端口:"));
        connectPanel.add(portField);
        connectPanel.add(new JLabel("用户名:"));
        connectPanel.add(usernameField);
        connectPanel.add(connectButton);

        // 布局
        add(connectPanel, BorderLayout.NORTH);
        add(chatScrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

        appendToChat("客户端准备就绪，请连接服务器");
    }

    private void appendToChat(String message) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append("[" + new Date() + "] " + message + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        });
    }

    private void connectToServer() {
        String server = serverField.getText();
        int port;
        try {
            port = Integer.parseInt(portField.getText());
        } catch (NumberFormatException e) {
            appendToChat("端口号无效");
            return;
        }

        username = usernameField.getText().trim();
        if (username.isEmpty()) {
            username = "匿名用户";
        }

        try {
            socket = new Socket(server, port);
            out = new PrintWriter(socket.getOutputStream(), true);

            receiveThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
                    String message;
                    while ((message = in.readLine()) != null) {
                        appendToChat(message);
                    }
                } catch (IOException e) {
                    if (!socket.isClosed()) {
                        appendToChat("连接异常: " + e.getMessage());
                    }
                } finally {
                    disconnect();
                }
            });
            receiveThread.start();

            connectButton.setEnabled(false);
            sendButton.setEnabled(true);
            appendToChat("已连接到服务器: " + server + ":" + port);
            appendToChat("您的用户名: " + username);

            out.println(username + " 加入了聊天室");
        } catch (IOException e) {
            appendToChat("连接服务器失败: " + e.getMessage());
        }
    }

    private void sendMessage() {
        String message = messageArea.getText().trim();
        if (!message.isEmpty()) {
            out.println(username + ": " + message);
            appendToChat("我: " + message);
            messageArea.setText("");
        }
    }

    private void disconnect() {
        SwingUtilities.invokeLater(() -> {
            try {
                if (out != null) out.close();
                if (socket != null) socket.close();

                connectButton.setEnabled(true);
                sendButton.setEnabled(false);
                appendToChat("已断开与服务器的连接");
            } catch (IOException e) {
                appendToChat("断开连接时出错: " + e.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}