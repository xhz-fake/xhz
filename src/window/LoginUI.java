package window;

import java.awt.*;//提供图形功能
import javax.swing.*;//用于创建窗口类


public class LoginUI {
    public void showUI() {
        JFrame jf = new JFrame();
        jf.setTitle("陕西志愿登录界面 版本：v9.0 ——阳光高考网");
        jf.setSize(600, 800);
        jf.setLocation(300,100);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flowLayout = new FlowLayout();
        jf.setLayout(flowLayout);

        JLabel la9 =new JLabel("姓名");
        JButton bt1=new JButton("登录");
        JLabel la1 = new JLabel("本科(一批)(二批)：");
        JLabel la2 = new JLabel("A志愿：");
        JLabel la3 = new JLabel("专业1");
        JLabel la4 = new JLabel("专业2");
        JLabel la5 = new JLabel("专业3");
        JLabel la6 = new JLabel("专业4");
        JLabel la7 = new JLabel("专业5");
        JLabel la8 = new JLabel("专业6");
        JButton bt = new JButton("确认");

        JTextField nameIn1 = new JTextField(60);
        JTextField nameIn2= new JTextField(20);
        JTextField nameIn3 = new JTextField(60);
        JTextField nameIn4= new JTextField(60);
        JTextField nameIn5 = new JTextField(60);
        JTextField nameIn6= new JTextField(60);
        JTextField nameIn7 = new JTextField(60);
        JTextField nameIn8= new JTextField(60);
        JTextField nameIn9=new JTextField(20);

        String path = "d:\\桌面\\3f011138073ae2d06deed4591e28f0a.png";
        ImageIcon icon = new ImageIcon(path);
        JLabel iconJla = new JLabel(icon);

        jf.add(iconJla);
        jf.add(la9);
        jf.add(nameIn9);
        jf.add(bt1);
        jf.add(la1);
        jf.add(nameIn1);
        jf.add(la2);
        jf.add(nameIn2);
        jf.add(la3);
        jf.add(nameIn3);
        jf.add(la4);
        jf.add(nameIn4);
        jf.add(la5);
        jf.add(nameIn5);
        jf.add(la6);
        jf.add(nameIn6);
        jf.add(la7);
        jf.add(nameIn7);
        jf.add(la8);
        jf.add(nameIn8);
        jf.add(bt);
        jf.setVisible(true);

        LoginAction loa = new LoginAction(nameIn9);
        bt1.addActionListener(loa);
        bt.addActionListener(loa);
    }
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.showUI();
    }
}
