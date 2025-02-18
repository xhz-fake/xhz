package ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageUtils {
    int[][] imgArr;
    int w;
    int h;
    Graphics gra;//从绘制面板上获取画笔

    //加载(打开)图片
    public void loadingImage(String path){
        File file=new File(path);
        try {
            BufferedImage image = ImageIO.read(file);//从图片文件中读取图片
            w=image.getWidth();
            h=image.getHeight();
            //创建一个空的二维数组用来存储图片的像素
            imgArr=new int[w][h];
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    imgArr[i][j]=image.getRGB(i,j);
                }
            }
            System.out.println("加载图片完成");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //打开原图
    public BufferedImage drawImage(){
        BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra=image.getGraphics();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
               int pixNum=imgArr[i][j];
               Color color=new Color(pixNum);
               imgGra.setColor(color);
               imgGra.fillRect(i,j,1,1);
            }
        }
        return image;
    }
    //灰度
    public BufferedImage drawGray(){
        BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra=image.getGraphics();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixNum=imgArr[i][j];
                Color color=new Color(pixNum);
                int r=color.getRed();
                int g=color.getGreen();
                int b=color.getBlue();
                int gray=(r+g+b)/3;
                Color grayColor=new Color(gray,gray,gray);
                imgGra.setColor(grayColor);
                imgGra.fillRect(i,j,1,1);
            }
        }
        return image;
    }



}
