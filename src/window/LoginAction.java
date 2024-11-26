package window;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginAction implements ActionListener{//校验用户输入的监听器类
    private JTextField nameIn9;//只能被本身修改和访问(类加对象变量)，缺少则无法识别“nameIn9”，这里指向“nameIn9”输入框
    public LoginAction(JTextField nameIn9){
        this.nameIn9=nameIn9;//this.nameIn9指当前对象具有的变量“nameIn9”，右侧nameIn9指参数传递过来的数值。
    }//创建时，输入界面类中的输入框
    public void actionPerformed(ActionEvent e) {//接口监听器里定义的抽象方法，所有实现这个接口的类都要调用这个方法
        String btText=e.getActionCommand();//通过e.getActionCommand来获得所触发按钮的内容
        System.out.println("已点击的是："+btText+"按钮");
        String name=nameIn9.getText();
        System.out.println("输入的是:"+name);
        if (name.equals("邢浩哲")){
        if (btText.equals("登录")) {
            JFrame frame = new JFrame("登录成功!");
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);}
        }
        if (btText.equals("确认")){
            JFrame frame = new JFrame("填报成功！");
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
    }
}
