package ImagePro.ImagePro_v_5;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ImageUtils {//本类主要负责图片的加载和绘制
    int[][] imgArr;
    int w;
    int h;
    private ArrayList<BufferedImage> imgList = new ArrayList<>();////////////////////
    private Rectangle selectionRect;

    public void setSelectionRect(Rectangle selectionRect){//传入截图区域
        this.selectionRect=selectionRect;
    }

    public Rectangle getSelectionRect(){//获取截图区域
        return selectionRect;
    }

    ImagePanel imagePanel;

    public void passImagePanel(ImagePanel imgPanel) {
        this.imagePanel = imgPanel;
    }

    //获取按钮上的图片
    public BufferedImage getBtnImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);//从图片文件中读取图片

            //获取按钮和的宽高
            int btnW = ImageProUI.imgBtnFrameW;
            int btnH = ImageProUI.imgBtnFrameH;
            int imgW = image.getWidth();
            int imgH = image.getHeight();

            //计算图片的宽高比
            double imgRatio = (double) imgW / imgH;
            double btnRatio = (double) btnW / btnH;

            int scaledWidth, scaledHeight;
            if (imgRatio > btnRatio) {//图片相对较宽，宽高比大于一，以宽度为基准进行缩放
                scaledWidth = btnW;
                scaledHeight = (int) (btnW / imgRatio);
            } else {//图片相对较窄,宽高比小于一，以高度为基准进行缩放
                scaledWidth = (int) (btnH * imgRatio);
                scaledHeight = btnH;
            }

            //创建缩放后的图片(看不懂)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, 2);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);//设置抗锯齿
            g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            //创建最终图片（按钮大小），并将缩放后的图片居中显示
            BufferedImage minImg = new BufferedImage(btnW, btnH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D minG = minImg.createGraphics();//用来设置背景的2d画笔
            minG.setColor(new Color(30, 30, 30));//填充背景色
            minG.fillRect(0, 0, btnW, btnH);//填充背景

            //计算居中位置
            int realX = (btnW - scaledWidth) / 2;
            int realY = (btnH - scaledHeight) / 2;
            minG.drawImage(scaledImage, realX, realY, null);
            minG.dispose();

            return minImg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //初始化按钮面板
    public void addImage(BufferedImage img) {
        imgList.add(img);
    }

    //获取最后一张图片
    public BufferedImage getLastimage() {
        if (imgList.isEmpty()) {
            return null;
        }
        return imgList.getLast();
    }

    //获取第一张图片
    public BufferedImage getFirstImage() {
        if (imgList.isEmpty()) {
            return null;
        }
        return imgList.getFirst();

    }

    //加载(打开)图片
    public void loadImage(String path) {
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

    //保存图片
    public void saveImage(BufferedImage img) {
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
                JOptionPane.showMessageDialog(null, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                System.out.println("保存失败!");
                JOptionPane.showMessageDialog(null, "保存失败！", "提示", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    //旋转操作
    public BufferedImage rotateImage(BufferedImage img, double angle) {
        int w = img.getWidth();
        int h = img.getHeight();

        double radians = Math.toRadians(angle);//将角度转换为弧度便于计算
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(w * cos + h * sin);//将一个浮点数四舍五入到最接近的整数值
        int newHeight = (int) Math.round(w * sin + h * cos);//将一个浮点数四舍五入到最接近的整数值

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, 2);
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);//设置抗锯齿

        int centerX=newWidth/2;
        int centerY=newHeight/2;

        //平移坐标系，使旋转中心点位于图片中心
        //为了在图像的中心点旋转，需要先将旋转中心平移到图像的中心，
        // 然后执行旋转操作，最后再将旋转中心平移回原始位置。
        g2d.translate(centerX,centerY);//translats是移动的意思，即把将坐标系的原点从左上角平移到图像的中心点
        g2d.rotate(radians);
        g2d.translate(-w/2,-h/2);//把坐标系原点归位到左上角；

        g2d.drawImage(img,0,0,null);
        g2d.dispose();//释放资源

        return rotatedImage;
    }

    //绘制灰度
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

    //绘制马赛克
    public BufferedImage drawMosaic() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        //获取这个图片的对象的图像渲染器-画笔
        Graphics imgGra = image.getGraphics();
        for (int i = 0; i < w; i += 25) {
            for (int j = 0; j < h; j += 25) {
                int rgbNum = imgArr[i][j];
                Color color = new Color(rgbNum);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                Color Mosaiccolor = new Color(r, g, b);
                imgGra.setColor(Mosaiccolor);
                imgGra.fillRect(i, j, 30, 30);
            }
        }
        return image;
    }

    //绘制油画
    public BufferedImage drawOilImage(){
        Random random=new Random();
        BufferedImage image=new BufferedImage(w,h,2);
        Graphics2D imgGra=image.createGraphics();
        for (int i = 0; i < w; i+=5) {
            for (int j = 0; j < h; j+=3) {
                int rgb=imgArr[i][j];
                Color color=new Color(rgb);
                imgGra.setColor(color);
                int ow= random.nextInt(10)+3;
                int oh=random.nextInt(8)+8;
                imgGra.fillOval(i,j,ow,oh);
            }
        }
        return image;
    }

    //绘制轮廓
    public BufferedImage drawBorderImage(){
        BufferedImage image=new BufferedImage(w,h,2);
        Graphics2D imgGra=image.createGraphics();
        for (int i = 0; i < w - 2; i++) {

            for (int j = 0; j < h - 2; j++) {
                int rgb1 =imgArr[i][j];
                Color color1 = new Color(rgb1);
                int red1 = color1.getRed();
                int green1 = color1.getGreen();
                int blue1 = color1.getBlue();
                int gray1 = (red1 + green1 + blue1) / 3;

                int rgb2 = imgArr[i+2][j+2];
                Color color2 = new Color(rgb2);
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();
                int gray2 = (red2 + green2 + blue2) / 3;
                if (Math.abs(gray1 - gray2) >= 18) {
                    imgGra.setColor(Color.BLACK);
                } else {
                    imgGra.setColor(Color.WHITE);
                }
               imgGra.fillRect(i , j , 1, 1);
            }
        }
        return image;
    }




}
