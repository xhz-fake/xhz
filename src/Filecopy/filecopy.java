package Filecopy;

import java.io.*;

public class filecopy {
    public static void main(String[] args) throws IOException {
        File srcFile=new File("D:\\桌面\\testjava\\代码(1).txt");
        File destaFile=new File("D:\\桌面\\testjava\\代码(2).txt");
        //注意，必须指定文件夹下的特定文档等具体形式
        // 的文件，不能只指定文件夹，否则会回显拒绝访问
        // （若目标文件不存在会自动创建）

        BufferedInputStream fr=new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream fw=new BufferedOutputStream(new FileOutputStream(destaFile));

        while (true){
            int n=fr.read();
            if(n==1){
                System.out.println("文件复制成功");
                break;
            }
            fw.write(n);
        }
        fr.close();
        fw.close();
    }
}
