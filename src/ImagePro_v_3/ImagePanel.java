package ImagePro_v_3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {//本类主要负责将列表中最后一次处理的图像展示在ImagePanel上

    ImageListener imgl;//传入监听器对象,仅声明，不创建对象

    public void passImageL(ImageListener l) {
        this.imgl = l;// 将传入的 ImageListener 对象赋值给 imgl
    }

    public void paint(Graphics gra) {
        super.paintComponent(gra);//会调用当前组件父类的 paint 方法，确保父类的绘制逻辑（如背景绘制、子组件绘制等）能够正常执行。
        //判断图片列表是否为空
        if (ImageListener.imgList.isEmpty()) {
            return;
        }
        //获取图片列表的最后一张图片的下标
        int lastImgIndex = ImageListener.imgList.size() - 1;
        BufferedImage img = ImageListener.imgList.get(lastImgIndex);//获取最后一张图片
        int w = img.getWidth();
        int h = img.getHeight();
        int x = this.getWidth() / 2 - w / 2;
        int y = this.getHeight() / 2 - h / 2;
        //计算居中位置
        //绘制最后一张图片
        gra.drawImage(img, x, y, w, h, null);//这里的方法drawImage是从父类Graphics中继承过来的

    }

}