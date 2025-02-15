package FileEncryption;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileEncDecListener_test  implements ActionListener {
    JTextField jtTarget;
    JTextField jtDeposit;

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        String pathTarget = jtTarget.getText();//////////
        String pathDeposit = jtDeposit.getText();//////////
        switch (ac) {
            case "加密":
                encFile(pathTarget, pathDeposit);
                break;
            case "解密":
                decFile(pathTarget, pathDeposit);
                break;
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
            while (t != -1) { //解密n=52，c=t，d=11 -->  (ed+@(n)y=1)
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
