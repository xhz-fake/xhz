package FileControl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class filecheckout_v4_0_UI {
    static JFrame frameLog = new JFrame("登录");
    static JFrame frameRes = new JFrame("注册");
    static JFrame frameEnsure1 = new JFrame();
    static JFrame frameEnsure2 = new JFrame();
    static ArrayList<User> usernames = new ArrayList<>();

    static {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        usernames.add(user);
    }

    public static void setLogUI() {
        frameLog.setSize(500, 400);
        frameLog.setLocationRelativeTo(null);
        frameLog.setLayout(new FlowLayout());
        frameRes.setDefaultCloseOperation(3);
        JLabel lName = new JLabel("账号");
        JTextField tName = new JTextField(50);
        JLabel lPassword = new JLabel("密码");
        JTextField tPassword = new JTextField(50);
        frameLog.add(lName);
        frameLog.add(tName);
        frameLog.add(lPassword);
        frameLog.add(tPassword);
        filecheckout_v4_0_Listener listner =
                new filecheckout_v4_0_Listener(tName, tPassword, usernames);
        JButton log = new JButton("登录");
        log.addActionListener(listner);
        JButton res = new JButton("注册");
        res.addActionListener(listner);
        frameLog.add(log);
        frameLog.add(res);
    }

    public static void showLogUI() {
        frameLog.setVisible(true);
    }

    public static void hideLogUI() {
        frameLog.setVisible(false);
    }

    public static void setResUI() {
        frameRes.setSize(500, 400);
        frameRes.setLocationRelativeTo(null);
        frameRes.setLayout(new FlowLayout());
        frameRes.setDefaultCloseOperation(3);
        JLabel lName = new JLabel("新账号");
        JTextField tName = new JTextField(50);
        JLabel lPassword = new JLabel("密码");
        JTextField tPassword = new JPasswordField(50);
        frameRes.add(lName);
        frameRes.add(tName);
        frameRes.add(lPassword);
        frameRes.add(tPassword);
        filecheckout_v4_0_Listener listner = new filecheckout_v4_0_Listener(tName, tPassword, usernames);
        JButton res = new JButton("确认注册");
        res.addActionListener(listner);
        frameRes.add(res);
    }

    public static void showResUI() {
        frameRes.setVisible(true);
    }

    public static void hideResUI() {
        frameRes.setVisible(false);
    }

    public static void setFrameEnsure() {
        JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        JButton yse = new JButton("返回登录");
        yse.addActionListener(e -> showLogUI());
        JOptionPane optionPane = new JOptionPane(new Object[]{"注册成功", yse}, JOptionPane.PLAIN_MESSAGE);
        optionPane.createDialog(null, "提示").setVisible(true);
    }


    public static void setFrameEnsure2() {
        JOptionPane.showMessageDialog(null, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        JButton yse = new JButton("确定");
        JOptionPane optionPane = new JOptionPane(new Object[]{"登录成功", yse},
                JOptionPane.PLAIN_MESSAGE);
        optionPane.createDialog(null, "提示").setVisible(true);
    }

    public static void showEnsureUI() {
        setFrameEnsure();
    }
    public static void showEnsure2UI() {
        setFrameEnsure2();
    }

    public static void main(String[] args) {
        filecheckout_v4_0_UI UI = new filecheckout_v4_0_UI();
        UI.setLogUI();
        UI.showLogUI();
    }
}