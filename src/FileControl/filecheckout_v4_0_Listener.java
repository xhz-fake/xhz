package FileControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class filecheckout_v4_0_Listener implements ActionListener {
    int flagRes=0,flagE1=0,flagE2=0;//添加按钮的点击次数防止界面重复生成
    ArrayList<User> usernames=new ArrayList<>();
    JTextField tName,tPassword;

    public filecheckout_v4_0_Listener(JTextField tName,JTextField tPassword,ArrayList<User> usernames) {
        this.tName = tName;
        this.tPassword = tPassword;
        this.usernames = usernames;
    }
    User nowUser;

    public void actionPerformed(ActionEvent e){
        String com=e.getActionCommand();
        if(com.equals("登录")){
            String username=tName.getText();
            for (int i = 0; i < usernames.size(); i++) {
                if(usernames.get(i).getUsername().equals(username)){
                    String password=tPassword.getText();
                    if(password.equals(usernames.get(i).getPassword())){
                        if(flagE2==0){
                            flagE2++;
                            nowUser=usernames.get(i);
                            filecheckout_v4_0_FileManage fm = new filecheckout_v4_0_FileManage();
                            fm.listener=this;
                            fm.showUI(usernames.get(i));

                        } else if (flagE2!=0) {

                        }
                    }else {
                        System.out.println("密码错误");
                    }
                } else if (i==usernames.size()-1) {
                    System.out.println("账户不存在");
                }
            }
        }
        if (com.equals("注册")) {
            if(flagRes==0){
                flagRes++;
                filecheckout_v4_0_UI.setResUI();
                filecheckout_v4_0_UI.showResUI();
            } else if (flagRes!=0) {
                filecheckout_v4_0_UI.showResUI();
            }
        }
        if (com.equals("确认注册")) {
            String username=tName.getText();
            String password=tPassword.getText();
            for (int i = 0; i < usernames.size(); i++) {
                if(usernames.get(i).getUsername().equals(username)){
                    System.out.println("账户已存在");
                } else if (i==usernames.size()-1) {
                    User user=new User(username,password);
                    usernames.add(user);
                    if(flagE1==0){
                        flagRes++;
                        filecheckout_v4_0_UI.setFrameEnsure();
                    } else if (flagE1!=0) {

                    }
                }
            }
        }
        if (com.equals("返回登录")) {
            filecheckout_v4_0_UI.hideResUI();
        }
        if(com.equals("上传")){
            JFileChooser chooser=new JFileChooser();
//            FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                    "JPG &amp; GIF Images", "jpg", "gif");
//            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getPath());
                String userPath=filecheckout_v4_0_FileManage.rootPath+"/"+nowUser.filePath;
                System.out.println("目标位置："+userPath);
            }
        }
    }
}
