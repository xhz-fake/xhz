package ImagePro_v_3;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageListener implements ActionListener, MouseListener {

    ImageUtils imgUtils = new ImageUtils();
    protected static ArrayList<BufferedImage> imgList = new ArrayList<>();//为了保存每次操作后的图片创建一个列表
    ImagePanel imagePanel = new ImagePanel();

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
                    System.out.println("选中的图片地址为:" + path);
                    imgUtils.loadingImage(path);
                }
            }
            case "原图" -> {
                BufferedImage img = imgUtils.drawImage();
                imgList.add(img);
                System.out.println("显示原图");
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
                System.out.println("保存图片");
            }

        }
        imagePanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

}
