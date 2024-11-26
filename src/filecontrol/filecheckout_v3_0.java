package filecontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class filecheckout_v3_0 {
    UIListener ul=new UIListener();
    public void showUI() {
        JFrame jf = new JFrame();
        jf.setTitle("右键菜单测试模块");
        FlowLayout flow = new FlowLayout();
        jf.setLayout(flow);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(400, 300);
        //创建一个面板
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        jf.add(panel);

        String[] btnname = {"Directory", "imgs"};
        for (int i = 0; i < btnname.length; i++) {
            JButton btn = new JButton(btnname[i]);
            btn.setBackground(Color.WHITE);
            panel.add(btn);
            btn.addMouseListener(ul);
        }
        String[] jlaname = {"a.txt", "b.txt", "c.txt"};
        for (int i = 0; i < jlaname.length; i++) {
            JLabel jla = new JLabel(jlaname[i]);
            panel.add(jla);
            jla.addMouseListener(ul);
        }
        jf.setVisible(true);

        PMActionListener pl = new PMActionListener(panel, 0);
        JPopupMenu jpm=new JPopupMenu();
        String []jmitext={"新建文件","新建文件夹","刷新"};
        for (int i = 0; i < jmitext.length; i++) {
            JMenuItem jmi=new JMenuItem(jmitext[i]);
            jmi.addActionListener(pl);
            jpm.add(jmi);
        }
        panel.setComponentPopupMenu(jpm);


    }

    public static void main(String[] args) {
        filecheckout_v3_0 filech3=new filecheckout_v3_0();
        filech3.showUI();
    }
}

class UIListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseBtn = e.getButton();
        if (mouseBtn == MouseEvent.BUTTON1) {
            System.out.println("点击了鼠标左键");
        }
        if (mouseBtn == MouseEvent.BUTTON2) {
            System.out.println("点击了鼠标中键");
        }
        if (mouseBtn == MouseEvent.BUTTON3) {
            System.out.println("点击了鼠标右键");
            //获取触发事件的事件源对象 按钮 窗体
            JComponent sourse = (JComponent) e.getSource();
            //判断这个对象是否为按钮类型
            if (sourse instanceof JButton btn) {
                System.out.println("右键菜单");
                PMActionListener pl=new PMActionListener(btn,1);
                JPopupMenu jpm1=new JPopupMenu();

                String[] Jmitext={"打开","删除","属性"};
                for (int i = 0; i < Jmitext.length; i++) {
                    JMenuItem jmi=new JMenuItem(Jmitext[i]);
                    jmi.addActionListener(pl);
                    jpm1.add(jmi);
                }
                jpm1.show(btn,e.getX(),e.getY());
                //show方法中参数1指：触发JPopupMenu（弹出式菜单）显示的组件
            } else if (sourse instanceof JLabel jla) {
                PMActionListener pl=new PMActionListener(jla,2);
                JPopupMenu jpm1=new JPopupMenu();

                String[] Jmitext={"打开方式","删除","属性"};
                for (int i = 0; i < Jmitext.length; i++) {
                    JMenuItem jmi=new JMenuItem(Jmitext[i]);
                    jmi.addActionListener(pl);
                    jpm1.add(jmi);
                }
                jpm1.show(jla,e.getX(),e.getY());

            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
}

class PMActionListener implements ActionListener {
    JComponent jComponent;
    int cpType;

    public PMActionListener(JComponent jComponent, int b) {
        this.jComponent = jComponent;
        cpType = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String miStr = e.getActionCommand();
        if (cpType == 1) {
            JButton btn = (JButton) jComponent;
            String fileName = btn.getText();
            System.out.println(miStr + "-->" + fileName);
        } else if (cpType == 2) {
            JLabel mi = (JLabel) jComponent;
            String fileName = mi.getText();
            System.out.println(miStr + "-->" + fileName);
        } else if (cpType == 0) {
            System.out.println(miStr);
        }

    }
}














