package ConstructorDemo.StaticFactoryMethod;

import java.io.File;

public class ImageReader {
    private final File imageFile;

    private ImageReader(File imageFile) {
        this.imageFile = imageFile;
    }

    public static ImageReader create(File file) throws ImageFormatException {
        if (isValidImage(file)) {
            throw new ImageFormatException("不支持的图像格式");
        }
        return new ImageReader(file);
    }

    private static boolean isValidImage(File file) {
        //验证文件格式的逻辑...
        return true;
    }

}

//自定义异常类
class ImageFormatException extends Exception {
    public ImageFormatException(String message) {
        super(message);
    }
}
