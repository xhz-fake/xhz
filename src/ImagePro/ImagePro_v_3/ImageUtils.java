package ImagePro.ImagePro_v_3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {//本类主要负责图片的加载和绘制
    int[][] imgArr;
    int w;
    int h;
    Graphics gra;//从绘制面板上获取画笔

    //加载(打开)图片
    public void loadingImage(String path) {
        File file = new File(path);
        try {
                BufferedImage image = ImageIO.read(file);//从图片文件中读取图片
                w = image.getWidth();
                h = image.getHeight();
                //创建一个空的二维数组用来存储图片的像素
                imgArr = new int[w][h];
                for (int i = 0; i < w; i++) {
                    for (int j = 0; j < h; j++) {
                        imgArr[i][j] = image.getRGB(i, j);
                    }
                }
                System.out.println("加载图片完成");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //打开原图
    public BufferedImage drawImage() {//这里的drawImage方法是自主创建的方法，BufferedImage返回一个图像
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra = image.getGraphics();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int rgbNum = imgArr[i][j];
                Color color = new Color(rgbNum);
                imgGra.setColor(color);//通过刚才获取的每个像素的RGB值用画笔的对象来设置颜色
                imgGra.fillRect(i, j, 1, 1);
            }
        }
        return image;
    }

    //灰度
    public BufferedImage drawGray() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra = image.getGraphics();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int rgbNum = imgArr[i][j];
                Color color = new Color(rgbNum);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int gray = (r + g + b) / 3;
                Color grayColor = new Color(gray, gray, gray);
                imgGra.setColor(grayColor);
                imgGra.fillRect(i, j, 1, 1);
            }
        }
        return image;
    }

    //马赛克
    public BufferedImage drawMosaic() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra = image.getGraphics();
        for (int i = 0; i < w; i += 10) {
            for (int j = 0; j < h; j += 10) {
                int rgbNum = imgArr[i][j];
                Color color = new Color(rgbNum);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                Color Mosaiccolor = new Color(r, g, b);
                imgGra.setColor(Mosaiccolor);
                imgGra.fillRect(i, j, 10, 10);
            }
        }
        return image;
    }
    public void saveImage(BufferedImage img){
        JFileChooser jfc = new JFileChooser();//文件选择器
        jfc.setDialogTitle("保存图片");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "png", "jpg");
        jfc.setFileFilter(filter);
        int state = jfc.showSaveDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jfc.getSelectedFile();
            String path = jfc.getSelectedFile().getAbsolutePath();//获取图片的绝对路径
            System.out.println("保存的图片地址为:" + path);
            if (!path.toLowerCase().endsWith(".png") && !path.toLowerCase().endsWith(".jpg")) {
                path += ".png"; // 默认保存为 PNG 格式
            }
            try {
                ImageIO.write(img, "png", fileToSave);
                System.out.println("保存成功!");
                JOptionPane.showMessageDialog(null, "保存成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception ex){
                System.out.println("保存失败!");
                JOptionPane.showMessageDialog(null, "保存失败！","提示",JOptionPane.ERROR_MESSAGE);
            }

        }
    }


}
