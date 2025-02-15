package filecontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * 包含两块面板
 * 一块是用户基础信息，密码管理
 * 一块是文件列表以及文件的操作
 */

public class filecheckout_v4_0_FileManage {
    static String rootPath = "D:\\桌面\\testjava";/////////////////////////////////////
    filecheckout_v4_0_Listener listener;

    public void showUI(User user) {
        JFrame jf = new JFrame();
        jf.setTitle("文件管理");
        jf.setSize(900, 600);
        jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(showUserInfo(user), BorderLayout.WEST);
        jf.add(fileList(new File(rootPath + "/" + user.filePath)));//////////////////
        jf.setVisible(true);
    }

    public JPanel showUserInfo(User user) {
        JPanel jp = new JPanel();
        jp.setBackground(Color.LIGHT_GRAY);
        jp.setPreferredSize(new Dimension(300, 0));
        jp.setLayout(new FlowLayout());
        JLabel nameJla = new JLabel("账号：");
        JTextField nameJtf = new JTextField(20);
        nameJtf.setText(user.username);//#######################################################
        nameJtf.setEditable(false);//表示不可编辑#####################################
        JLabel pwdJla = new JLabel("密码：");
        JPasswordField pwdJtf = new JPasswordField(20);
        pwdJtf.setText(user.password);
        pwdJtf.setEditable(false);

        JButton changePwdJbt = new JButton("修改密码");
        jp.add(nameJla);
        jp.add(nameJtf);
        jp.add(pwdJla);
        jp.add(pwdJtf);
        jp.add(changePwdJbt);

        return jp;
    }

    public JPanel fileList(File filePath) {
        JPanel jp = new JPanel();
        jp.setBackground(Color.DARK_GRAY);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        File[] files = filePath.listFiles();
        DefaultListModel<JCheckBox> listModel = new DefaultListModel<>();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            JCheckBox btn = new JCheckBox(f.getName());
            listModel.add(i, btn);
        }
        JList<JCheckBox> fileList = new JList<>(listModel);
// 添加鼠标监听器以处理选中
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = fileList.locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkBox = listModel.getElementAt(index);
                    checkBox.setSelected(!checkBox.isSelected());
                    fileList.repaint(fileList.getCellBounds(index, index));
                }
            }
        });
// 创建单元格渲染器，以便在JList中显示JCheckBox
        fileList.setCellRenderer(new JCheckBoxRenderer());
        JScrollPane jsp = new JScrollPane(fileList);
        jsp.setPreferredSize(new Dimension(550, 400));
        JButton upbtn = new JButton("上传");
        upbtn.addActionListener(listener);
        JButton downbtn = new JButton("下载");
        jp.add(jsp);
        jp.add(upbtn);
        jp.add(downbtn);
        downbtn.addActionListener(e -> {
            String pwd = JOptionPane.showInputDialog("请输入解密密码：");
            if (pwd.equals("123")) {
                for (int i = 0; i < listModel.getSize(); i++) {
                    JCheckBox jCheckBox = listModel.get(i);
                    if (jCheckBox.isSelected()) {
                        System.out.println(jCheckBox.getText());
                    }
                }
                JOptionPane.showMessageDialog(jp, "下载成功！");
            } else {
                JOptionPane.showMessageDialog(jp, "密码错误！");
            }
        });
        return jp;
    }

    static class JCheckBoxRenderer extends JCheckBox implements ListCellRenderer<JCheckBox> {
        public JCheckBoxRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<? extends JCheckBox> list,
                                                      JCheckBox value, int index, boolean isSelected, boolean cellHasFocus) {
            setEnabled(list.isEnabled());
            setSelected(value.isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.getText());
            return this;
        }
    }
}