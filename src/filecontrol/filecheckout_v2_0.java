package filecontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class filecheckout_v2_0 {
    //让“rootPath”被类的所有方法共享(在其他类中可以通过类名访问该类中的静态变量)
    static String rootPath = "邢浩哲";
    filechecklistener_v2_0 flis = new filechecklistener_v2_0();

    File file = new File(rootPath);
    File[] files = file.listFiles();

    public void showUI() {

        JFrame jf = new JFrame();
        jf.setTitle("文件管理");
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(550, 260);

        int c = 1;
        int r = files.length;
        //使用网格布局
        GridLayout g1 = new GridLayout(r, c, 5, 5);
        jf.setLayout(g1);

        //存储所有文件的文件夹名称
        ArrayList<String> dirNameList = new ArrayList<>();
        ArrayList<String> fileNameList = new ArrayList<>();
        //根据目录的路径获取根目录下的文件
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (!f.isHidden()) {
                if (f.isDirectory()) {
                    dirNameList.add(f.getName());
                } else {
                    fileNameList.add(f.getName());
                }
            }
        }
        //创建文件夹按钮和文件标签并按顺序显示
        for (int i = 0; i < dirNameList.size(); i++) {
            JButton jbt = new JButton(dirNameList.get(i));
            jbt.setBackground(Color.WHITE);
            jbt.addActionListener(flis);
            // actionCommand属性 不会显示到按钮的文本上，但是监听器可以获取
            jbt.setActionCommand(rootPath + "\\" + dirNameList.get(i));
            jf.add(jbt);
        }
        for (int i = 0; i < fileNameList.size(); i++) {
            JButton jbt = new JButton(fileNameList.get(i));
            jbt.setBackground(Color.yellow);
            jbt.setOpaque(true);//设置组件是否透明
            jf.add(jbt);
        }
        jf.setVisible(true);

        String filePath = "d:\\桌面\\邢浩哲";

        try {
            // 使用Runtime.exec()执行系统命令
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows系统
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filePath);
            } else {
                System.out.println("Unsupported OS");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        filecheckout_v2_0 fco = new filecheckout_v2_0();
        fco.showUI();
    }

    public class filechecklistener_v2_0 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dirName = e.getActionCommand();
            System.out.println("dirName" + dirName);
            //获取到文件夹按钮的绝对路径 创建文件对象
            File file = new File(dirName);
            //调用显示文件夹的方法
            showFileDir(file);
        }

        //封装打开文件夹的方法根据当前点击的文件夹按钮显示的文件夹下的文件
        public void showFileDir(File dirFile) {
            JFrame jf = new JFrame();
            jf.setTitle(dirFile.getName());
            jf.setSize(600, 400);
            jf.setLocation(500, 260);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            File[] files = dirFile.listFiles();
            int c = 1;
            int r = files.length;
            GridLayout g1 = new GridLayout(r, c, 5, 5);
            jf.setLayout(g1);

            ArrayList<String> dirNameList = new ArrayList<>();
            ArrayList<String> fileNameList = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (!f.isHidden()) {
                    if (f.isDirectory()) {
                        dirNameList.add(f.getName());
                    } else {
                        fileNameList.add(f.getName());
                    }
                }
            }
            //添加目录
            for (int i = 0; i < dirNameList.size(); i++) {
                JButton jbt = new JButton(dirNameList.get(i));
                jbt.setBackground(Color.white);
                jbt.setActionCommand(dirFile.getAbsolutePath() + "\\" + dirNameList.get(i));
                jf.add(jbt);
                jbt.addActionListener(this);
            }
            for (int i = 0; i < fileNameList.size(); i++) {
                JButton jbt = new JButton(fileNameList.get(i));
                jbt.setBackground(Color.yellow);
                jbt.setOpaque(true);
                jf.add(jbt);
            }
            jf.setVisible(true);
        }
    }


}
