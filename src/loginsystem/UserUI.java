package loginsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserUI {
    UserAction userAction = new UserAction();
    JFrame loginFrame;
    JFrame userHomeFrame;

    public void loginUI() {
        loginFrame = new JFrame("用户登录界面");
        loginFrame.setSize(400, 300);
        loginFrame.setTitle("用户登录系统");
        loginFrame.setLocationRelativeTo(null);
        FlowLayout layout = new FlowLayout();
        loginFrame.setLayout(layout);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameJla = new JLabel("账号：");
        JTextField nameJtf = new JTextField(30);
        JLabel passwardJla = new JLabel("密码：");
        JPasswordField passwardJtf = new JPasswordField(30);
        JButton loginJbt = new JButton("登录");
        JButton registerJbt = new JButton("注册");

        loginFrame.add(nameJla);
        loginFrame.add(nameJtf);
        loginFrame.add(passwardJla);
        loginFrame.add(passwardJtf);
        loginFrame.add(loginJbt);
        loginFrame.add(registerJbt);
        loginFrame.setVisible(true);
//加载监听器在登录按钮上
        loginJbt.addActionListener(userAction);
        registerJbt.addActionListener(userAction);
//使用监听对象调用它的nameJtfL，pwdJpfL初始化
        userAction.nameJtfL = nameJtf;
        userAction.pwdJpfL = passwardJtf;
        userAction.userUI = this;//将UserUI的对象传到监听器中，this表示调用loginUI方法的对象

    }

    //个人主界面
    public void userHomeUI(User user) {
        userHomeFrame = new JFrame();
        userHomeFrame.setSize(400, 300);
        userHomeFrame.setTitle("用户个人主页");
        FlowLayout flow = new FlowLayout();
        userHomeFrame.setLayout(flow);
        userHomeFrame.setLocationRelativeTo(null);

        String[] infoJlaStrs = {"账号：", "密码：", "积分：", "电话：", "邮箱："
                , "地址", "在线状态"};
        String[] infoStrs = {user.userName, user.passward, user.score + "",
                user.phoneNumber, user.email, user.address, user.isLogin ? "在线" : "离线"};
        int infoSize = infoJlaStrs.length;
        JTextField[] userInfoJtfs = new JTextField[infoSize];

        for (int i = 0; i < infoSize; i++) {
            JLabel jla = new JLabel(infoJlaStrs[i]);
            JTextField jtf = new JTextField(infoStrs[i]);
            if (i == infoSize) {
                jtf.setPreferredSize(new Dimension(260, 25));
            } else {
                jtf.setPreferredSize(new Dimension(310, 25));
            }
            userHomeFrame.add(jla);
            userHomeFrame.add(jtf);
            userInfoJtfs[i] = jtf;
            if (i == 2 || i == 0 || i == 6) {
                jtf.setEditable(false);
            }
        }

        JButton btn1 = new JButton("更新信息");
        JButton btn2 = new JButton("退出登录");
        btn1.addActionListener(userAction);
        btn2.addActionListener(userAction);
        userHomeFrame.add(btn1);
        userHomeFrame.add(btn2);

        userHomeFrame.setVisible(true);
        //将存储了输入框的数组传到监听器中
        userAction.infoJtfs = userInfoJtfs;
    }

    public void hideLoginUI() {//隐藏登录界面
        loginFrame.setVisible(false);
    }

    public void hideUserHomeUI() {//隐藏用户主页
        userHomeFrame.setVisible(false);
    }

    JFrame adminFrame;

    public void hideAdminUI() {
        adminFrame.setVisible(false);
    }

    //管理界面：
    public void showAdminUI(User[] userList) {
        adminFrame = new JFrame("管理者界面");
        adminFrame.setSize(920, 520);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flow = new FlowLayout();
        adminFrame.setLayout(flow);

        JTable table;
        UserTableModel tableModel;

        Object[] columnNames = {"账号", "密码", "电话", "地址", "积分"
                , "邮箱", " 在线状态"};
        //初始化表格模型
        tableModel = new UserTableModel(null, columnNames);
        table = new JTable(tableModel);

        //添加示例数据
        Object[][] data = new Object[userList.length][7];
        for (int i = 0; i < data.length; i++) {
            User user = userList[i];
            if (user != null) {
                data[i][0] = user.userName;
                data[i][1] = user.passward;
                data[i][2] = user.phoneNumber;
                data[i][3] = user.address;
                data[i][4] = user.score;
                data[i][5] = user.email;
                data[i][6] = user.isLogin ? "在线" : "离线";
            }
        }
        for (int i = 0; i < data.length; i++) {
            Object[] row=data[i];
            tableModel.addRow(row);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 400));
        adminFrame.add(scrollPane);

        JButton btn=new JButton("刷新");
        btn.addActionListener(userAction);
        adminFrame.add(btn);
        adminFrame.setVisible(true);
    }

    public static void main(String[] args) {
        UserUI userUI = new UserUI();
        userUI.loginUI();
        //创建一个可以存储多个用户对象的用户数组
        User [] userList=new User[10];
        int userCount=  0;
        //往数组中插入一些模拟数据
        {
            for (int i = 0; i < 8; i++) {
                User user=new User("admin"+i,"123"+i);
                userList[i]=user;
                userCount++;
            }
            System.out.println("用户数据生成模拟完成");
        }
        userUI.showAdminUI(userList);
    }
}
class UserTableModel extends DefaultTableModel{
    private static final long serivalVersionUID=1L;

    public UserTableModel(Object[][] data,Object[] columnNames){
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column==1){
            return false;
        }
        return  true;
    }
}













