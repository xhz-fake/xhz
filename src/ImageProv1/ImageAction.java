package ImageProv1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageAction implements ActionListener {
    Graphics g2;
    BufferedImage img;

    {
        System.out.println("加载图片");
        getImagePixels();//加载图片的像素矩阵
    }

    public void actionPerformed(ActionEvent e) {
        String btnText = e.getActionCommand();
        System.out.println("点击了" + btnText);
        if (btnText.equals("原图")) {
            drawImage();
        } else if (btnText.equals("马赛克")) {
            drawMosaicImage();
        }
    }

    public void getImagePixels() {
        String path = "d:\\桌面\\5915fd0d1142ef6528be32df076f398.png";
        File file = new File(path);

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void drawImage() {//绘制原图
        g2.drawImage(img, 0, 0, null);
    }

    public void drawMosaicImage() {//绘制马赛克
        for (int i = 0; i < img.getWidth(); i += 10) {
            for (int j = 0; j < img.getHeight(); j += 10) {
                int num = img.getRGB(i, j);
                Color acolor = new Color(num);
                int red = acolor.getRed();
                int green = acolor.getGreen();
                int blue = acolor.getBlue();
                Color bcolor = new Color(red, red, red);

                g2.setColor(bcolor);
                g2.fillRect(i, j, 10, 10);

            }
        }


    }

}
