package ImageProcessing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ImageListener implements ActionListener, MouseListener {

    ImageUtils imgUtils=new ImageUtils();
    ArrayList<BufferedImage>imgList=new ArrayList<>();
    ImagePanel imagePanel;

    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        System.out.println("操作" + ac);

        if (ac.equals("打开")) {
            JFileChooser jfc= new JFileChooser();//文件选择器
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG","png","jpg");
            //文件名过滤器
            jfc.setFileFilter(filter);
            int state = jfc.showOpenDialog(null);
            if (state == JFileChooser.APPROVE_OPTION) {
                String path = jfc.getSelectedFile().getAbsolutePath();//获取图片的绝对路径
                System.out.println("选中的图片地址为"+path);
                imgUtils.loadingImage(path);
            }
        }
        else if (ac.equals("原图")) {
            BufferedImage img = imgUtils.drawImage();
            imgList.add(img);
            System.out.println("显示原图");
        }
        else if (ac.equals("灰度")) {
            BufferedImage img = imgUtils.drawGray();
            imgList.add(img);
            System.out.println("显示灰度");
        }
        else if (ac.equals("保存")) {
            System.out.println("保存图片");
        }
        else if (ac.equals("马赛克")) {
            System.out.println("显示马赛克");
        }

        imagePanel.repaint();
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
