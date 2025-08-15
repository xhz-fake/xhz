package FileControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class filecheckout_v5_0_FileManage {

    filecheckout_v5_0_Listener listener;

    public void showUI(User user) {//静态方法只能访问类的其他静态成员，包括静态变量和静态方法。
        JFrame jf = new JFrame("文件管理");//二阶界面
        jf.setLocation(500,250);
        jf.setSize(600, 600);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(showUserInfo(user), BorderLayout.NORTH);//##############
        jf.add(showEncDecUI(), BorderLayout.SOUTH);
        jf.setVisible(true);
    }

    public JPanel showEncDecUI() {
        JPanel jp1 = new JPanel();
        jp1.setPreferredSize(new Dimension(400, 300));
        jp1.setBackground(Color.WHITE);
        FlowLayout fl = new FlowLayout();
        jp1.setLayout(fl);
        JLabel jlSrc=new JLabel("                            源文件:                            ");
        JLabel jlDest=new JLabel("                            目标文件:                            ");

        JTextField jtSrc = new JTextField(30);
        JTextField jtDest = new JTextField(30);

        JButton jbSrc = new JButton("srcFile");
///////////////////////////////////////////////////////////////////////
        jbSrc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(jp1);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    jtSrc.setText(selectedFile.getAbsolutePath());//////
                }
            }
        });

        JButton jbDest = new JButton("destFile");

        jbDest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(jp1);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    jtDest.setText(selectedFile.getAbsolutePath());
                }
            }
        });
//////////////////////////////////////////////////////////////////////////
        JButton jbEnc = new JButton("加密");
        JButton jbDec = new JButton("解密");

        jp1.add(jlSrc);
        jp1.add(jtSrc);
        jp1.add(jbSrc);
        jp1.add(jlDest);
        jp1.add(jtDest);
        jp1.add(jbDest);
        jp1.add(jbEnc);
        jp1.add(jbDec);

        jbSrc.addActionListener(listener);//// 初始化 listener 之前确保 jtSrc 和 jtDest 不为 null
        jbDest.addActionListener(listener);///// 初始化 listener 之前确保 jtSrc 和 jtDest 不为 null
        jbEnc.addActionListener(listener);
        jbDec.addActionListener(listener);
        listener.jtSrc = jtSrc;
        listener.jtDest = jtDest;

        return jp1;
    }

    public JPanel showUserInfo(User user) {//它表示有一个名为 showUserInfo 的公共方法，
        // 这个方法接收一个 User 类型的对象作为参数，并返回一个 jfanel 对象。这个方法的目的可
        // 能是为了展示用户信息，比如用户名、密码等，并将这些信息放置 在一个 jfanel 上。
        JPanel jp = new JPanel();
        jp.setBackground(Color.LIGHT_GRAY);
        jp.setPreferredSize(new Dimension(300, 200));
        jp.setLayout(new FlowLayout());
        JLabel namejla = new JLabel("账号：");
        JTextField namejtf = new JTextField(user.getUsername(), 30);
        namejtf.setText(user.username);
        namejtf.setEditable(false);
        JLabel passwordjla = new JLabel("密码：");
        JTextField passwordjtf = new JTextField(user.getPassword(), 30);
        passwordjtf.setText(user.password);
        passwordjtf.setEditable(false);

        jp.add(namejla);
        jp.add(namejtf);
        jp.add(passwordjla);
        jp.add(passwordjtf);

        return jp;
    }

}



















