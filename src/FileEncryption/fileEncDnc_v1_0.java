package FileEncryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class fileEncDnc_v1_0 {

    public void encFile(String src, String dest) {//加密方法
        try {
            FileInputStream fins = new FileInputStream(src);
            FileOutputStream fous = new FileOutputStream(dest);
            int t = fins.read();
            while (t != -1) {
                t += 69;
                fous.write(t);
                t = fins.read();
            }
            fins.close();
            fous.flush();
            fous.close();
            System.out.println("文件传输已完成！");
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }

    public void decFile(String dest, String dest2) {//复制方法

        try {
            FileInputStream fins = new FileInputStream(dest);
            FileOutputStream fous = new FileOutputStream(dest2);
            int t = fins.read();
            while (t != -1) {
                t -= 69;
                fous.write(t);
                t = fins.read();
            }
            fins.close();
            fous.flush();
            fous.close();
            System.out.println("文件解码成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fileEncDnc_v1_0 fe = new fileEncDnc_v1_0();
        String src = "D:\\桌面\\testjava\\prac1124.txt";
        String dest = "D:\\桌面\\testjava\\prac112402.txt";
        String dest2 = "D:\\桌面\\testjava\\prac112403.txt";
        fe.encFile(src, dest);
        fe.decFile(dest, dest2);
    }
}
