package FileControl;

import java.io.File;

public class filecheckout_v1_0 {
    public static void main(String[] args) {
        File file =new File("邢浩哲");
        if (file.exists() && file.isDirectory()){
            ListFiles(file,0);
        }else{
            System.out.println(file.getName());
            System.out.println("该父文件不是文件夹");
        }
    }

    public static void ListFiles(File dir,int level){
        File[] files=dir.listFiles();
        if(files !=null){
            for (File file:files) {
                printline(level);//添加符号，并通过数量进行区分
                System.out.println(file.getName());
                if(file.isDirectory()){
                    ListFiles(file,level+1);
                }
            }
        }
    }

    public static void printline(int level){
        for (int i = 0; i < level; i++) {
            System.out.print("——");
        }
    }
}
