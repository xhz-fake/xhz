package ImagePro_v_3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ImageListener implements ActionListener, MouseListener, ComponentListener {

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
                BufferedImage img=imgList.getLast();
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
                        JOptionPane.showMessageDialog(null, "保存成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        System.out.println("保存失败!");
                        JOptionPane.showMessageDialog(null, "保存失败！","提示",JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        }
        imagePanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
    }

//MouseListener接口的方法
    @Override
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
