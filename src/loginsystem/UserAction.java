package loginsystem;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserAction implements ActionListener {
    JTextField nameJtfL;//
    JPasswordField pwdJpfL;
    UserUI userUI;
    //可以调用userHomeUI显示
    JTextField[] infoJtfs;


    User[] userList = new User[10];
    int userCount = 0;

    {
        for (int i = 0; i < 8; i++) {
            User user = new User("admin" + i, "123" + i);
            userList[i] = user;
            userCount++;
        }
        System.out.println("用户数据生成模拟成功！");
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮被点击了");
        String ac = e.getActionCommand();//获取按钮上的动作指令文本

        String name = nameJtfL.getText();
        System.out.println("用户名是：" + name);
        String pwd = pwdJpfL.getText();
        System.out.println("密码是" + pwd);

        if (ac.equals("登录")) {//查询是否已经注册
            for (int i = 0; i < userCount; i++) {
                User user = userList[i];
                if (user.userName.equals(name)) {
                    boolean isLogin = user.login(pwd);
                    if (isLogin) {
                     userUI.hideLoginUI();
                     userUI.userHomeUI(user);
                        break;
                    }
                }
            }
        } else if (ac.equals("注册")) {
            int count = 0;
            for (int i = 0; i < userCount; i++) {
                User user = userList[i];
                if (user.userName.equals(name)) {
                    System.out.println("用户已注册");
                    count++;
                }
            }
            if (count==0){
               User user=new User(name,pwd);
               userList[userCount]=user;
               userCount+=1;
                System.out.println("注册成功！");
                System.out.println("目前用户数量："+userCount);
            }
        } else if (ac.equals("退出登录")) {
            //获取输入框中的内容
            String userName=infoJtfs[0].getText();
            //获取用户名知道是谁在登陆
            //在数组中查找用户
            for (int i = 0; i < userCount; i++) {
              User user=userList[i];
                if(user.userName.equals(userName)){
                    user.logOut();
                }
            }
            userUI.hideUserHomeUI();
            userUI.loginUI();
        } else if (ac.equals("更新信息")) {
            //获取输入框中的内容
            String userName=infoJtfs[0].getText();//获取用户名知道是谁在登录
            //获取需要修改的数据
            String passward=infoJtfs[1].getText();
            String phoneNumber=infoJtfs[3].getText();
            String address=infoJtfs[5].getText();
            String email=infoJtfs[4].getText();
            //在数组中查找用户
            for (int i = 0; i < userCount; i++) {
                User user=userList[i];
                if (user.userName.equals(userName)){
                    user.passward=passward;
                    user.phoneNumber=phoneNumber;
                    user.email=email;
                    user.address=address;
                    break;
                }
            }
            System.out.println("信息修改成功！");
        }else if(ac.equals("刷新")){
            userUI.hideAdminUI();
            userUI.showAdminUI(userList);

        }


    }
}