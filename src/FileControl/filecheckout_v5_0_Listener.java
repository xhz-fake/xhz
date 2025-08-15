package FileControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class filecheckout_v5_0_Listener implements ActionListener {
    int flagRes = 0, flagE1 = 0, flagE2 = 0;
    ArrayList<User> usernames ;
    JTextField tName, tPassword;
    JTextField jtSrc,jtDest;//这些成员变量的作用是将外部组件（如文本框）和数据（
                            // 如用户列表）传递到 filecheckout_v5_0_Listener
                            // 类中，以便在事件处理方法中使用这些组件和数据

    public filecheckout_v5_0_Listener(JTextField tName, JTextField tPassword, ArrayList<User> usernames) {//////////////
        this.tName = tName;
        this.tPassword = tPassword;
        this.usernames = usernames;
    }

    //这个构造函数的作用是初始化filecheckout_v5_0_Listener类的对象，
    // 将传入的参数值赋给类的成员变量，以便在类的其他方法中使用这些变量。
    //(JTextField tName, JTextField tPassword, ArrayList<User> usernames)：
    // 这是构造函数的参数列表，它定义了创建filecheckout_v4_0_Listener对象时必须提供的参数。

    User nowUser;

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        //********String src = jtSrc.getText();
        //********String dest = jtDest.getText();//错误，因为只有在执行了以下的逻辑，
                                         //让jtSrc和jtDest存在后才可以获取输入框中的内容

        if (com.equals("登录")) {//触发登录按钮的操作
            String username = tName.getText();
            for (int i = 0; i < usernames.size(); i++) {
                if (usernames.get(i).getUsername().equals(username)) {
                    String password = tPassword.getText();
                    if (password.equals(usernames.get(i).getPassword())) {
                        if (flagE1 == 0) {
                            System.out.println("登录成功！");
                            filecheckout_v5_0_UI.setFrameEnsure2();//提示
                            flagE1++;
                            nowUser = usernames.get(i);
                            filecheckout_v5_0_FileManage fm = new filecheckout_v5_0_FileManage();
                            fm.listener = this;
                            fm.showUI(usernames.get(i));
                        } else if (flagE1 != 0) {
                            System.out.println("用户已登录，无需重复登录");
                            filecheckout_v5_0_UI.setFrameEnsure4();//提示
                        }
                    } else {
                        System.out.println("密码错误!");
                    }
                } else if (i == usernames.size() - 1) {//循环结束还没有找到对应的用户名
                    System.out.println("账户不存在");
                }
            }
        } else if (com.equals("注册")) {
            if (flagRes == 0) {
                flagRes++;
                filecheckout_v5_0_UI.setResUI();
                filecheckout_v5_0_UI.showResUI();
                filecheckout_v5_0_UI.hideLogUI();
            } else if (flagRes != 0) {
                filecheckout_v5_0_UI.hideResUI();
            }
        } else if (com.equals("确认")) {
            String username = tName.getText();
            String password = tPassword.getText();
            for (int i = 0; i < usernames.size(); i++) {
                if (usernames.get(i).getUsername().equals(username)) {
                    System.out.println("用户名已存在");
                    filecheckout_v5_0_UI.setFrameEnsure3();//提示
                } else if (i == usernames.size() - 1) {
                    User user = new User(username, password);
                    usernames.add(user);
                    if (flagE2 == 0) {
                        flagE2++;
                        filecheckout_v5_0_UI.setFrameEnsure();//提示
                        System.out.println("注册成功!");
                    } else if (flagE2 != 0) {
                        System.out.println("无法二次注册");
                    }
                }
            }

        } else if (com.equals("返回登录")) {
            filecheckout_v5_0_UI.hideResUI();
        } else if (com.equals("加密")) {
            encFile(jtSrc.getText(), jtDest.getText());///易错点：此处若传入的参数为（pathsrc，pathdest）则无法获取对象，
        } else if (com.equals("解密")) {
            decFile(jtSrc.getText(),jtDest.getText());///易错点
        }
    }

    public void encFile(String src, String dest) { // 加密方法
        try {
            FileInputStream fins = new FileInputStream(src);
            FileOutputStream fous = new FileOutputStream(dest);
            int t = fins.read();
            while (t != -1) {
                t+=10;
                fous.write(t);
                t = fins.read();
            }
            fins.close();
            fous.flush();
            fous.close();
            System.out.println("文件加密成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decFile(String dest, String dest2) { //解密方法
        try {
            FileInputStream fins = new FileInputStream(dest);
            FileOutputStream fous = new FileOutputStream(dest2);
            int t = fins.read();
            while (t != -1) {
                t-=10;
                fous.write(t);
                t = fins.read();
            }
            fins.close();
            fous.flush();
            fous.close();
            System.out.println("文件解码成功!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
