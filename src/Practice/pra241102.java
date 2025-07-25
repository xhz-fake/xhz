package Practice;

import java.io.IOException;

public class pra241102 {
    public static void main(String[] args) {
        // 指定WPS Office的可执行文件路径，这里假设WPS安装在默认路径
        String wpsPath = "C:\\Users\\Administrator\\AppData\\Local\\kingsoft\\WPS Office\\ksolaunch.exe";
        // 指定要打开的文档的路径
        String docPath = "d:\\桌面\\邢浩哲\\大学学习\\作业\\2022级专业导论作业1-答题纸.docx";

        // 构造打开WPS文档的命令
        String command = wpsPath + " " + docPath;

        try {
            // 执行命令
            Runtime.getRuntime().exec(command);
            System.out.println("WPS文档已成功打开！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开WPS文档失败，请检查路径和WPS安装情况。");
        }
    }
}

