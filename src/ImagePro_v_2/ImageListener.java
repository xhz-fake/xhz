package ImagePro_v_2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;//主要作用是将一幅图片加载到内存中,常用于修图
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ImageListener implements ActionListener, ChangeListener {
    private BufferedImage img;
    private BufferedImage img1;
    private int w, h;
    private final int X = 70, Y = 150;
    JTextField pathJtf1;
    JTextField imgPathJtf1;
Graphics g1;
    //滑杆
    JSlider grayJsl1;
    int grayNum = 130;

    //监听滑杆
    public void stateChanged(ChangeEvent event) {
        System.out.println("滑动了滑杆");
        grayNum = grayJsl1.getValue();
    }


    public void actionPerformed(ActionEvent event) {//////////////////////////////////
        String ac = event.getActionCommand();
        switch (ac) {
            case "加载图片":
                String path = pathJtf1.getText();
                loadImage(path);
                break;
            case "原图":
                drawImage();
                break;
            case "圆点马赛克":
                drawMosaicImage();
                break;
            case "灰度":
                drawGrayImage();
                break;
            case "二值化":
                drawBinaryImage();
                break;
            case "暖色":
                drawWarmColourImage();
                break;
            case "油画":
                drawOilImage();
                break;
            case "轮廓化":
                drawBorderImage();
                break;
            case "热成像":
                drawT7Image();
                break;
            case "图片融合":
                String path1 = imgPathJtf1.getText();
                drawDoubleImage(path1);
                break;

        }
    }

    public void drawDoubleImage(String path1) {
        img1 = img;
        loadImage(path1);
        int iw1 = img.getWidth();
        int ih1 = img.getHeight();
        int iw2 = img1.getWidth();
        int ih2 = img1.getHeight();
        int minw = Math.min(iw1, iw2);
        int minh = Math.min(ih1, ih2);
        double in = (double) minw / 100;
        for (int i = 0; i < minw; i++) {
            for (int j = 0; j < minh; j++) {
                int rgb1 = img.getRGB(i, j);
                int rgb2 = img.getRGB(i, j);

                Color c1 = new Color(rgb1);
                Color c2 = new Color(rgb2);

                double i1 = (i / in * 0.01);
                double px = (double) i / (double) minw;

                int nred = (int) ((c1.getRed() * px) + c2.getRed() * (1 - px));
                int ngreen = (int) ((c1.getGreen() * i1) + c2.getGreen() * (1 - i1));
                int nblue = (int) ((c1.getBlue() * i1) + c2.getBlue() * (1 - i1));
                Color color = new Color(nred, ngreen, nblue);
                g1.setColor(color);
                g1.fillRect(X + i, Y + j, 1, 1);

            }

        }
    }


    public void loadImage(String path) {
        System.out.println("加载图片：" + path);
        File file = new File(path);
        try {
            img = ImageIO.read(file);
            w = img.getWidth();
            h = img.getHeight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("加载图片完成~~~");
    }

    public void drawImage() {
        g1.drawImage(img, X, Y, null);
    }


    public void drawMosaicImage() {
        for (int i = 0; i < w; i += 10) {
            for (int j = 0; j < h; j += 10) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                g1.setColor(color);
                g1.fillOval(i + X, j + Y, 10, 10);
            }
        }
    }

    public void drawGrayImage() {
        for (int i = 0; i < w; i += 1) {
            for (int j = 0; j < h; j += 1) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red + green + blue) / 3;
                Color bcolor = new Color(gray, gray, gray);
                g1.setColor(bcolor);
                g1.fillRect(i + X, j + Y, 1, 1);
            }
        }
    }

    public void drawBinaryImage() {
        for (int i = 0; i < w; i += 1) {
            for (int j = 0; j < h; j += 1) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red + green + blue) / 3;
                if (gray < 50) {
                    g1.setColor(Color.BLACK);
                } else {
                    g1.setColor(Color.WHITE);
                }
                g1.fillRect(i + X, j + Y, 1, 1);
            }
        }
    }

    public void drawWarmColourImage() {
        for (int i = 0; i < w; i += 1) {
            for (int j = 0; j < h; j += 1) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                red += 30;
                if (red > 255) red = 255;
                Color bcolor = new Color(red, green, blue);
                g1.setColor(bcolor);
                g1.fillRect(i + X, j + Y, 1, 1);
            }
        }
    }

    public void drawOilImage() {
        Random random = new Random();
        for (int i = 0; i < w; i += 3) {
            for (int j = 0; j < h; j += 2) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                g1.setColor(color);
                int ow = random.nextInt(10) + 4;
                int oh = random.nextInt(8) + 6;
                g1.fillOval(i + X, j + Y, ow, oh);
            }
        }
    }

    public void drawBorderImage() {
        for (int i = 0; i < w - 2; i++) {
            for (int j = 0; j < h - 2; j++) {

                int rgb1 = img.getRGB(i, j);
                Color color1 = new Color(rgb1);
                int red1 = color1.getRed();
                int green1 = color1.getGreen();
                int blue1 = color1.getBlue();
                int gray1 = (red1 + green1 + blue1) / 3;
// 第2个像素
                int rgb2 = img.getRGB(i + 2, j + 2);
                Color color2 = new Color(rgb2);
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();
                int gray2 = (red2 + green2 + blue2) / 3;
                if (Math.abs(gray1 - gray2) >= 20) {
                    g1.setColor(Color.BLACK);
                } else {
                    g1.setColor(Color.WHITE);
                }
                g1.fillRect(i + X, j + Y, 1, 1);
            }
        }
    }

    public void drawT7Image() {
        for (int i = 0; i < w; i += 1) {
            for (int j = 0; j < h; j += 1) {
                int rgb = img.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red + green + blue) / 3;
                if (gray < 100) {
                    g1.setColor(Color.BLACK);
                } else {
                    g1.setColor(Color.RED);
                }
                g1.fillRect(i + X, j + Y, 1, 1);
            }
        }
    }
}

