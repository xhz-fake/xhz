package ImagePro_v_4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {//本类主要负责将列表中最后一次处理的图像展示在ImagePanel上

    ImageUtils imgUtils;//声明一个ImageUtils对象imgUtils

    public void passImageUtils(ImageUtils imgUtils){//////////////////////////////////////////
        this.imgUtils=imgUtils;
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

        //计算居中位置
        int realX = (panelWidth - scaledWidth) / 2;
        int realY = (panelHeight - scaledHeight) / 2;

        //绘制最后一张图片
        gra.drawImage(img, realX, realY, scaledWidth, scaledHeight, null);//这里的方法drawImage是从父类Graphics中继承过来的

        //绘制截取区域
        Rectangle selectionRect=imgUtils.getSelectionRect();
        if (selectionRect!=null){
            Graphics2D g2d=(Graphics2D) gra.create();
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));////////////////////////////////
            g2d.drawRect(selectionRect.x,selectionRect.y,selectionRect.width,selectionRect.height);
            g2d.dispose();
        }
    }

}