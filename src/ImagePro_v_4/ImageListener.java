package ImagePro_v_4;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageListener implements ActionListener, MouseListener, ComponentListener,MouseMotionListener {

    ImagePanel imagePanel;
    ImageUtils imgUtils ;

    protected static ArrayList<BufferedImage> imgList = new ArrayList<>();//为了保存每次操作后的图片创建一个列表
    private Point startPoint;
    private Point endPoint;
    protected Rectangle selectionRect;
    protected boolean isSelecting=false;


    public void passImagePanel(ImagePanel imgPanel){///////////////////////////////////////////
        this.imagePanel=imgPanel;
    }
    public void passImageUtils(ImageUtils imgUtils){//////////////////////////////////////////
        this.imgUtils=imgUtils;
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
            case "右旋"->{
                BufferedImage img=imgList.getLast();
                img=imgUtils.rotateImage(img,90);
                imgList.add(img);
                System.out.println("进行右旋");
                imagePanel.repaint();
            }
            case "左旋"->{
                BufferedImage img=imgList.getLast();
                img=imgUtils.rotateImage(img,-90);
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
                BufferedImage img=imgList.getLast();
                imgUtils.saveImage(img);
            }

        }
        imagePanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
    }


//MouseListener接口的方法
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint=e.getPoint();//记录鼠标按下时的起点
        selectionRect=new Rectangle(startPoint);// 初始化选择区域
    }
    public void mouseReleased(MouseEvent e) {
        if (selectionRect!=null&& !selectionRect.isEmpty()){
            BufferedImage img=ImageListener.imgList.getLast();
            BufferedImage croppedImage=img.getSubimage(selectionRect.x,selectionRect.y,selectionRect.width,selectionRect.height);
            ////////////////////////////////////////////////////////////////////////////////////////////////
            ImageListener.imgList.add(croppedImage); // 将截取的图片添加到列表
            imagePanel.repaint();
        }
        imgUtils.setSelectionRect(null);//清空选择区域
        selectionRect=null;//清空选择区域
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
        endPoint=e.getPoint();// 记录鼠标拖拽时的终点
        selectionRect.setFrameFromDiagonal(startPoint,endPoint);// 更新选择区域
        imagePanel.repaint();
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
