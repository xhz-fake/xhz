package FileEncryption;

import java.awt.*;
import javax.swing.*;

public class fileEncDec_v2_0_test {

    FileEncDecListener el=new FileEncDecListener();

    public void showEncDecUI(){
        JFrame jf=new JFrame();
        jf.setSize(600,200);
        jf.setTitle("文件加密解密系统");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(550,260);
        FlowLayout fl=new FlowLayout();
        jf.setLayout(fl);
        JButton jbEnc=new JButton("加密");
        JButton jbDec=new JButton("解密");
        jbEnc.setBackground(Color.WHITE);
        jbDec.setBackground(Color.WHITE);
        JTextField jtTarget=new JTextField(50);
        JTextField jtDeposit=new JTextField(50);
        JLabel jlTarget=new JLabel("待处理文件名：");
        JLabel jlDeposit=new JLabel("处理结果文件：");
        jf.add(jlTarget);
        jf.add(jtTarget);

        jf.add(jlDeposit);
        jf.add(jtDeposit);

        jf.add(jbEnc);
        jf.add(jbDec);
        jbEnc.addActionListener(el);
        jbDec.addActionListener(el);
        jf.setVisible(true);

        el.jtTarget=jtTarget;
        el.jtDeposit=jtDeposit;
    }

    public static void main(String[] args) {
        fileEncDec_v2_0 fe = new fileEncDec_v2_0();
        fe.showEncDecUI();
    }
}


