package ImagePro_v_4;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageListener implements ActionListener, MouseListener, ComponentListener, MouseMotionListener {

    ImagePanel imagePanel;
    ImageUtils imgUtils;

    protected static ArrayList<BufferedImage> imgList = new ArrayList<>();//为了保存每次操作后的图片创建一个列表
    private Point startPoint;
    private Rectangle selectionRect;

    public void passImagePanel(ImagePanel imgPanel) {///////////////////////////////////////////
        this.imagePanel = imgPanel;
    }

    public void passImageUtils(ImageUtils imgUtils) {//////////////////////////////////////////
        this.imgUtils = imgUtils;
    }

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        System.out.println("activited:" + ac);
        switch (ac) {
            case "打开" -> {
                JFileChooser jfc = new JFileChooser();//文件选择器
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "png", "jpg");
                //文件名过滤器
                jfc.setFileFilter(filter);
                int state = jfc.showOpenDialog(null);
                if (state == JFileChooser.APPROVE_OPTION) {
                    String path = jfc.getSelectedFile().getAbsolutePath();//获取图片的绝对路径
                    System.out.println("图片地址为:" + path);
                    imgUtils.loadImage(path);
                }
            }
            case "原图" -> {
                BufferedImage img = imgUtils.drawImage();
                imgList.add(img);
                System.out.println("显示原图");
            }
            case "右旋" -> {
                BufferedImage img = imgList.getLast();
                img = imgUtils.rotateImage(img, 90);
                imgList.add(img);
                System.out.println("进行右旋");
                imagePanel.repaint();
            }
            case "左旋" -> {
                BufferedImage img = imgList.getLast();
                img = imgUtils.rotateImage(img, -90);
                imgList.add(img);
                System.out.println("进行左旋");
                imagePanel.repaint();
            }
            case "灰度" -> {
                BufferedImage img = imgUtils.drawGray();
                imgList.add(img);
                System.out.println("显示灰度");
            }
            case "马赛克" -> {
                BufferedImage img = imgUtils.drawMosaic();
                imgList.add(img);
                System.out.println("显示马赛克");
            }
            case "保存" -> {
                BufferedImage img = imgList.getLast();
                imgUtils.saveImage(img);
            }

        }
        imagePanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
    }


    //MouseListener接口的方法
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = imagePanel.panelToImageCoordinates(e.getPoint());//转换为图片坐标
        selectionRect = new Rectangle(startPoint);// 初始化选择区域
    }

    public void mouseReleased(MouseEvent e) {
        if (selectionRect != null && !selectionRect.isEmpty()) {
            BufferedImage img = imgList.getLast();

            //获取面板的缩放比例和居中偏移
            int panelWidth = imagePanel.getWidth();
            int panelHeight = imagePanel.getHeight();
            int imgWidth = img.getWidth();
            int imgHeight = img.getHeight();

            //计算缩放比例
            double scale = Math.min(panelWidth / imgWidth, panelHeight / imgHeight);
            int scaledWidth = (int) (imgWidth * scale);
            int scaledHeight = (int) (imgHeight * scale);

            //计算居中偏移
            int offsetX = (panelWidth - scaledWidth) / 2;
            int offsetY = (panelHeight - scaledHeight) / 2;

            //获取图片边界
            Point imgBottomRight = imagePanel.imageToPanelCoordinates(new Point(img.getWidth(), img.getHeight()));

            //确保选择区域的起点在图片范围内
            int x = Math.max(0, Math.min(selectionRect.x, imgBottomRight.x - 1));
            int y = Math.max(0, Math.min(selectionRect.y, imgBottomRight.y - 1));

            //确保选择区域的宽和高在图片范围内
            int width = Math.max(1, Math.min(selectionRect.width, imgBottomRight.x - x));
            int height = Math.max(1, Math.min(selectionRect.height, imgBottomRight.y - y));

            //将面板坐标转换回图片坐标
            int imgX = (int) ((x - offsetX) / scale);
            int imgY = (int) ((y - offsetY) / scale);
            int imgWidthCropped = (int) (width / scale);
            int imgHeightCropped = (int) (height / scale);

            //确保截取区域在图片范围内
            imgX = Math.max(0, Math.min(imgX, img.getWidth()-1));
            imgY = Math.max(0, Math.min(imgY, img.getHeight()-1));
            imgWidthCropped = Math.max(1, Math.min(imgWidthCropped, img.getWidth() - imgX));
            imgHeightCropped = Math.max(1, Math.min(imgHeightCropped, img.getHeight() - imgY));

            System.out.println("截取区域: x=" + imgX + ", y=" + imgY + ", width=" +
                    imgWidthCropped + ", height=" + imgHeightCropped);

            //截取的选择区域的子图片
            try {
                BufferedImage croppedImage = img.getSubimage(imgX, imgY, imgWidthCropped, imgHeightCropped);
                ////////////////////////////////////////////////////////////////////////////////////////////////
                imgList.add(croppedImage); // 将截取的图片添加到列表

                // 打印 imgList 的内容
                System.out.println("imgList 大小: " + imgList.size());
                for (int i = 0; i < imgList.size(); i++) {
                    BufferedImage image = imgList.get(i);
                    System.out.println("图片 " + i + ": 宽度=" + image.getWidth() + ", 高度=" + image.getHeight());
                }

                imagePanel.repaint();
            }catch (Exception ex){
                System.out.println("截图失败: " + ex.getMessage());
            }
        }
        imgUtils.setSelectionRect(null);//清空选择区域
        selectionRect = null;//清空选择区域
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    //MouseMotionListener接口的方法
    @Override
    public void mouseDragged(MouseEvent e) {
        Point endPoint = imagePanel.panelToImageCoordinates(e.getPoint());//转换为图片坐标
        selectionRect.setFrameFromDiagonal(startPoint, endPoint);// 更新选择区域
        imgUtils.setSelectionRect(selectionRect);//将选择区域传给ImageUtils
        imagePanel.repaint();//重绘面板以显示选择区域
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //ComponentListener接口的方法
    @Override
    public void componentResized(ComponentEvent e) {
        imagePanel.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
