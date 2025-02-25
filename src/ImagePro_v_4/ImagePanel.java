package ImagePro_v_4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {//本类主要负责将列表中最后一次处理的图像展示在ImagePanel上

    ImageUtils imgUtils;//声明一个ImageUtils对象imgUtils

    public void passImageUtils(ImageUtils imgUtils) {//////////////////////////////////////////
        this.imgUtils = imgUtils;
    }

    public void paintComponent(Graphics gra) {
        super.paintComponent(gra);//会调用当前组件父类的 paint 方法，确保父类的绘制逻辑（如背景绘制、子组件绘制等）能够正常执行。
        //判断图片列表是否为空
        if (ImageListener.imgList.isEmpty()) {
            return;
        }
        //获取图片列表的最后一张图片的下标
        BufferedImage img = ImageListener.imgList.getLast();//获取最后一张图片

        //获取图片的宽高和面板的宽高
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        //计算图片缩放比例
        double widthScale = (double) panelWidth / imgWidth;
        double heightScale = (double) panelHeight / imgHeight;
        double scale = Math.min(widthScale, heightScale);

        int scaledWidth = (int) (imgWidth * scale);
        int scaledHeight = (int) (imgHeight * scale);

        //计算居中位置(偏移量)
        int realX = (panelWidth - scaledWidth) / 2;
        int realY = (panelHeight - scaledHeight) / 2;

        //绘制最后一张图片
        gra.drawImage(img, realX, realY, scaledWidth, scaledHeight, null);//这里的方法drawImage是从父类Graphics中继承过来的
        System.out.println("当前绘制的图片信息为: "+"x = "+realX+" y = "+realY+
                " width = "+scaledWidth+" height = "+scaledHeight);

        //绘制截取矩形
        Rectangle selectionRect = imgUtils.getSelectionRect();
        if (selectionRect != null) {
            Graphics2D g2d = (Graphics2D) gra.create();
            g2d.setColor(new Color(50, 189, 7));
            g2d.setStroke(new BasicStroke(2));//用于设置绘制图形时的笔触样式

            // 将图片坐标转换为面板坐标
            int x = (int) (selectionRect.x * scale) + realX;
            int y = (int) (selectionRect.y * scale) + realY;
            int width = (int) (selectionRect.width * scale);
            int height = (int) (selectionRect.height * scale);
            g2d.drawRect(x,y,width,height);
            g2d.dispose();
        }
    }

    //将面板坐标转换为准确的图片坐标(面板->图片)
    public Point panelToImageCoordinates(Point panelPoint) {
        //获取图片列表的最后一张图片的下标
        BufferedImage img = ImageListener.imgList.getLast();//获取最后一张图片

        //获取图片的宽高和面板的宽高
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        //计算图片缩放比例
        double widthScale = (double) panelWidth / imgWidth;
        double heightScale = (double) panelHeight / imgHeight;
        double scale = Math.min(widthScale, heightScale);

        int scaledWidth = (int) (imgWidth * scale);
        int scaledHeight = (int) (imgHeight * scale);

        //计算偏移量
        int offsetX = (panelWidth - scaledWidth) / 2;
        int offsetY = (panelHeight - scaledHeight) / 2;

        //将面板上的点坐标映射到图片上
        int imgX = (int) ((panelPoint.x - offsetX) / scale);
        int imgY = (int) ((panelPoint.y - offsetY) / scale);

        return new Point(imgX, imgY);
    }

    //将图片的边界转换到面板坐标(图片->面板)
    public Point imageToPanelCoordinates(Point imgPoint) {
        BufferedImage img = ImageListener.imgList.getLast();
        //获取图片的宽高和面板的宽高
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        //计算缩放比例
        double scale = Math.min(panelWidth / imgWidth, panelHeight / imgHeight);
        int scaledWidth = (int) (imgWidth * scale);
        int scaledHeight = (int) (imgHeight * scale);

        //计算居中偏移
        int offsetX = (panelWidth - scaledWidth) / 2;
        int offsetY = (panelHeight - scaledHeight) / 2;

        //将图片坐标映射到ImagePanel上
        int panelX = (int) (imgPoint.x * scale) + offsetX;///////////////////////////
        int panelY = (int) (imgPoint.y * scale) + offsetY;//////////////////////////

        return new Point(panelX, panelY);
    }

}