package Filecopy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class pra121501_filecopy {
    public static void main(String[] args) {
        // 指定源文件和目标文件的路径
        Path sourcePath = Paths.get("D:\\桌面\\testjava\\加密种类.pdf");
        Path destinationPath = Paths.get("D:\\桌面\\testjava\\加密种类1.pdf");

        try {
            // 使用Files.copy拷贝文件
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File copying failed.");
        }
    }
}

