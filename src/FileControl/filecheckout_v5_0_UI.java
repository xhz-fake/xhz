package FileControl;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class filecheckout_v5_0_UI {
   static JFrame frameLog = new JFrame("登录页面");
   static JFrame frameRes = new JFrame("注册页面");

    static ArrayList<User> usernames = new ArrayList<>();

    static {
        User user= new User();//无参User
        user.setUsername("admin");
        user.setPassword("123");
        usernames.add(user);
    }

    public static void setLogUI() {
        frameLog.setSize(500, 400);
        frameLog.setLocationRelativeTo(null);
        frameLog.setLayout(new FlowLayout());
        frameLog.setDefaultCloseOperation(3);

        JLabel logName=new JLabel("账号：");
        JLabel logPassword=new JLabel("密码：");

        JTextField tName=new JTextField(50);
        JPasswordField tpassword =new JPasswordField(50);
        JButton blog=new JButton("登录");
        JButton bres=new JButton("注册");

        frameLog.add(logName);
        frameLog.add(tName);
        frameLog.add(logPassword);
        frameLog.add(tpassword);
        frameLog.add(blog);
        frameLog.add(bres);

        filecheckout_v5_0_Listener listener=new filecheckout_v5_0_Listener(tName,tpassword,usernames);///////////////////////
        blog.addActionListener(listener);
        bres.addActionListener(listener);

    }
    public static void setResUI(){

        frameRes.setSize(500, 400);
        frameRes.setLayout(new FlowLayout());
        frameRes.setLocationRelativeTo(null);
        frameRes.setDefaultCloseOperation(3);

        JLabel resName=new JLabel(" 账号(new) ：");
        JLabel resPassword=new JLabel(" 密码(new)：");
        JButton bEnsure=new JButton("确认");
        JTextField tName=new JTextField(50);
        JPasswordField tPassword =new JPasswordField(50);

        frameRes.add(resName);
        frameRes.add(tName);
        frameRes.add(resPassword);
        frameRes.add(tPassword);
        frameRes.add(bEnsure);

        filecheckout_v5_0_Listener listener=new filecheckout_v5_0_Listener(tName,tPassword,usernames);/////
        bEnsure.addActionListener(listener);

    }

    public static void showLogUI(){
        frameLog.setVisible(true);
    }
    public static void showResUI(){
        frameRes.setVisible(true);
    }
    public static void hideLogUI(){
        frameLog.setVisible(false);
    }
    public static void hideResUI(){
        frameRes.setVisible(false);
    }

    public static void setFrameEnsure() {
        JOptionPane.showMessageDialog(null, "注册成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
        JButton ret = new JButton("返回登录");
        ret.addActionListener(e -> showLogUI());
        ret.addActionListener(e -> hideResUI());
        JOptionPane optionPane1 = new JOptionPane(new Object[]{ret}, JOptionPane.PLAIN_MESSAGE);
        optionPane1.createDialog(null, "提示").setVisible(true);
    }

    public static void setFrameEnsure2(){
        JOptionPane.showMessageDialog(null,"登录成功",
                "提示",JOptionPane.INFORMATION_MESSAGE);
        JOptionPane optionPane2=new JOptionPane(new Object[]{"登录成功"},JOptionPane.PLAIN_MESSAGE);
        optionPane2.createDialog(null,"提示");
    }

    public static void setFrameEnsure3(){
        JOptionPane.showMessageDialog(null,"用户名已存在",
                "提示",JOptionPane.INFORMATION_MESSAGE);
        JOptionPane optionPane3=new JOptionPane(new Object[]{"用户名已存在"},JOptionPane.PLAIN_MESSAGE);
        optionPane3.createDialog(null,"提示").setVisible(true);
    }

    public static void setFrameEnsure4(){
        JOptionPane.showMessageDialog(null,"用户已登录",
                "提示",JOptionPane.INFORMATION_MESSAGE);
        JOptionPane optionPane4=new JOptionPane(new Object[]{"用户已登录"},JOptionPane.PLAIN_MESSAGE);
        optionPane4.createDialog(null,"提示").setVisible(true);
    }

    public static void main(String[] args) {
        filecheckout_v5_0_UI UI=new filecheckout_v5_0_UI();
        UI.setLogUI();
        UI.showLogUI();
    }
}
