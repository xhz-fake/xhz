package Net_Interaction.communication_v_2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class ChatServer extends JFrame {
    private JTextArea logArea;
    private JTextArea messageArea;
    private JButton sendButton;
    private JList<String> clientList;
    private DefaultListModel<String> clientListModel;
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private int port = 8888;

    public ChatServer() {
        super("聊天服务器");
        initUI();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 日志区域
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("服务器日志"));

        // 客户端列表
        clientListModel = new DefaultListModel<>();
        clientList = new JList<>(clientListModel);
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane clientScrollPane = new JScrollPane(clientList);
        clientScrollPane.setBorder(BorderFactory.createTitledBorder("在线客户端"));
        clientScrollPane.setPreferredSize(new Dimension(200, 0));

        // 消息发送区域
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createTitledBorder("发送消息"));
        messageArea = new JTextArea(3, 20);
        messageArea.setLineWrap(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);

        sendButton = new JButton("发送");
        sendButton.addActionListener(e -> sendMessage());

        messagePanel.add(messageScrollPane, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // 控制按钮
        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("启动服务器");
        startButton.addActionListener(e -> startServer());

        JButton stopButton = new JButton("停止服务器");
        stopButton.addActionListener(e -> stopServer());

        controlPanel.add(startButton);
        controlPanel.add(stopButton);

        // 布局
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(logScrollPane, BorderLayout.CENTER);
        leftPanel.add(messagePanel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.CENTER);
        add(clientScrollPane, BorderLayout.EAST);

        log("服务器准备就绪，点击启动服务器按钮开始");
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append("[" + new Date() + "] " + message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            log("服务器已在端口 " + port + " 启动");

            new Thread(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        Socket clientSocket = serverSocket.accept();

                        // 创建ClientHandler实例
                        ClientHandler client = new ClientHandler(
                                clientSocket,
                                this::processClientMessage,
                                null // 先设为null，后面再设置
                        );

                        // 创建最终回调
                        Runnable disconnectCallback = () -> removeClient(client);

                        // 设置回调
                        client.setOnDisconnectCallback(disconnectCallback);

                        // 添加到集合
                        clients.add(client);
                        clientListModel.addElement(client.getClientInfo());

                        // 启动线程
                        new Thread(client).start();
                        log("客户端连接: " + client.getClientInfo());

                    } catch (IOException e) {
                        if (!serverSocket.isClosed()) {
                            log("接受客户端连接时出错: " + e.getMessage());
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            log("启动服务器失败: " + e.getMessage());
        }
    }

    private void processClientMessage(String message) {
        log("收到消息: " + message);
        broadcast(message);
    }

    private void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                broadcast("服务器即将关闭");

                for (ClientHandler client : clients) {
                    client.close();
                }
                clients.clear();
                clientListModel.clear();

                serverSocket.close();
                log("服务器已停止");
            }
        } catch (IOException e) {
            log("停止服务器时出错: " + e.getMessage());
        }
    }

    private void sendMessage() {
        String message = messageArea.getText().trim();
        if (!message.isEmpty()) {
            broadcast("[服务器] " + message);
            log("发送消息: " + message);
            messageArea.setText("");
        }
    }

    private void broadcast(String message) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(message);
            }
        }
    }

    private void removeClient(ClientHandler client) {
        SwingUtilities.invokeLater(() -> {
            clients.remove(client);
            clientListModel.removeElement(client.getClientInfo());
            log("客户端断开连接: " + client.getClientInfo());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatServer::new);
    }
}