package ImageProcessing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    ImageListener imgl;//传入监听器对象

    public void addImageL(ImageListener l) {
        imgl = l;
    }

    public void paint(Graphics gra) {
        super.paint(gra);
        //判断图片列表是否为空
        if (imgl.imgList.isEmpty()) {
            return;
        }
        //获取图片列表的最后一张图片的下标
        int lastImgIndex = imgl.imgList.size() - 1;
        BufferedImage img = imgl.imgList.get(lastImgIndex);
        int w = img.getWidth();
        int h = img.getHeight();
        int x=this.getWidth()/2-w/2;
        int y=this.getHeight()/2-h/2;
        //计算居中位置
        gra.drawImage(img, x, y, w, h, null);

    }

}