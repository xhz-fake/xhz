package ImagePro_v_4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageProUI {//本类主要负责基本框架UI界面的展示

    ImageListener imgl = new ImageListener();//创建监听器对象
    ImagePanel imgPanel=new ImagePanel();
    ImageUtils imgUtils = new ImageUtils();

    protected static int UI_WIDTH = 1000;
    protected static int UI_HEIGHT = 800;
    protected static int imgBtnFrameW = 200;
    protected static int imgBtnFrameH = 100;

    // 展示UI
    public void showUI() {
        //创建总窗体
        JFrame jf = new JFrame();
        jf.setTitle("照片");
        jf.setSize(UI_WIDTH, UI_HEIGHT);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        //创建面板
        JPanel btnPanel = new JPanel();
        JPanel listPanel = new JPanel();
        //ImagePanel imgPanel = new ImagePanel();////////////////////////////////////////////////////////////

        //设置基本属性
        btnPanel.setBackground(Color.LIGHT_GRAY);
        imgPanel.setBackground(Color.white);
        listPanel.setBackground(Color.LIGHT_GRAY);

        //设置按钮面板大小
        Dimension dim = new Dimension(220, 100);
        btnPanel.setPreferredSize(dim);
        listPanel.setPreferredSize(dim);

        initBtnPanel(btnPanel);//初始化按钮界面
        initListPanel(listPanel);

        //设置图像和按钮的布局
        jf.add(btnPanel, BorderLayout.SOUTH);
        jf.add(imgPanel, BorderLayout.CENTER);
        jf.add(listPanel, BorderLayout.WEST);

        jf.setVisible(true);

        imgPanel.addMouseMotionListener(imgl);
        imgPanel.addMouseListener(imgl);
        jf.addComponentListener(imgl);

        imgPanel.passImageUtils(imgUtils);
        imgl.passImageUtils(imgUtils);
        imgl.passImagePanel(imgPanel);
    }

    public void initBtnPanel(JPanel btnPanel) {//初始化按钮面板
        String[] btnTexts = {"打开", "原图", "保存", "马赛克", "灰度"};
        for (String btnText : btnTexts) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.white);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
        String[] btnTests2 = {"<截图>","<画笔>", "<直线>", "<矩形>",  "<马赛克笔>"};
        for (String btnText : btnTests2) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.white);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
    }

    public  void initListPanel(JPanel listPanel) {//初始化列表面板
        listPanel.setLayout(new BorderLayout());
        String rootpath = "D:\\桌面\\邢浩哲\\照片";
        JTextField jtf = new JTextField();
        listPanel.add(jtf, BorderLayout.NORTH);

        JPanel imgBtnPanel = new JPanel();
        imgBtnPanel.setLayout(new GridLayout(0, 1));
        File rootfile = new File(rootpath);
        File[] files = rootfile.listFiles();
        if (files != null) {
            for (File imgFile : files) {//遍历对应目录下每个图片文件，并为之设置图片按钮
                JButton btn = new JButton();
                btn.setContentAreaFilled(false);//移除按钮的背景填充
                BufferedImage btnImage = imgUtils.getBtnImage(imgFile);//由于在单个循环内，每个画笔的坐标xy即指单个btn上的坐标
                btn.setIcon(new ImageIcon(btnImage));//设置按钮的图标
                btn.setBackground(new Color(200,200,200));
                btn.setPreferredSize(new Dimension(imgBtnFrameW, imgBtnFrameH));

                imgBtnPanel.add(btn);//疑问

                //为按钮添加监听器
                btn.addActionListener(e -> {
                    String fileName =imgFile.getName();
                    jtf.setText(rootpath + "\\" + fileName);
                    imgUtils.loadImage(jtf.getText());
                    BufferedImage img=imgUtils.drawImage();
                    ImageListener.imgList.add(img);
                    imgPanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
                });
                //imgBtnPanel.add(btn);//将按钮添加到面板//疑问
            }
            //添加滑动窗口
            JScrollPane jsp = new JScrollPane(imgBtnPanel);
            jsp.setViewportView(imgBtnPanel);/////////////////////////////////////////////////////////////////////
            listPanel.add(jsp, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        ImageProUI imageProUI = new ImageProUI();
        imageProUI.showUI();
    }
}
