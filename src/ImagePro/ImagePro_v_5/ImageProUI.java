package ImagePro.ImagePro_v_5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProUI {//本类主要负责基本框架UI界面的展示

    ImageListener imgl = new ImageListener();//创建监听器对象
    ImagePanel imgPanel = new ImagePanel();//创建图像面板对象
    ImageUtils imgUtils = new ImageUtils();//创建面板绘制方法对象

    protected static int UI_WIDTH = 1000;
    protected static int UI_HEIGHT = 800;
    protected static int imgBtnFrameW = 200;
    protected static int imgBtnFrameH = 100;

    // 展示UI
    public void showUI() {
        //创建总窗体
        JFrame jf = new JFrame();
        jf.setTitle("图像处理工具");
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

        //在图像绘制面板上添加鼠标监听器和鼠标动作监听器，在整个UI界面上添加组件监听器
        imgPanel.addMouseMotionListener(imgl);
        imgPanel.addMouseListener(imgl);
        jf.addComponentListener(imgl);

        imgPanel.passImageUtils(imgUtils);
        imgUtils.passImagePanel(imgPanel);
        imgl.passImageUtils(imgUtils);
        imgl.passImagePanel(imgPanel);
        imgl.passlistPanel(listPanel);
    }

    public ImageProUI() {
        showUI();
    }

    public ImageProUI(ImageListener imgl, ImagePanel imgPanel, ImageUtils imgUtils) {//有参构造
        this.imgl = imgl;
        this.imgPanel = imgPanel;
        this.imgUtils = imgUtils;
    }

    public ImageListener getImgl() {
        return imgl;
    }

    public void setImgl(ImageListener imgl) {
        this.imgl = imgl;
    }

    public ImagePanel getImgPanel() {
        return imgPanel;
    }

    public void setImgPanel(ImagePanel imgPanel) {
        this.imgPanel = imgPanel;
    }

    public ImageUtils getImgUtils() {
        return imgUtils;
    }

    public void setImgUtils(ImageUtils imgUtils) {
        this.imgUtils = imgUtils;
    }

    public static int getUiWidth() {
        return UI_WIDTH;
    }

    public static void setUiWidth(int uiWidth) {
        UI_WIDTH = uiWidth;
    }

    public static int getUiHeight() {
        return UI_HEIGHT;
    }

    public static void setUiHeight(int uiHeight) {
        UI_HEIGHT = uiHeight;
    }

    public static int getImgBtnFrameW() {
        return imgBtnFrameW;
    }

    public static void setImgBtnFrameW(int imgBtnFrameW) {
        ImageProUI.imgBtnFrameW = imgBtnFrameW;
    }

    public static int getImgBtnFrameH() {
        return imgBtnFrameH;
    }

    public static void setImgBtnFrameH(int imgBtnFrameH) {
        ImageProUI.imgBtnFrameH = imgBtnFrameH;
    }

    public void initBtnPanel(JPanel btnPanel) {//初始化按钮面板
        String[] btnTexts = {"打开", "原图", "保存","左旋", "右旋",  "马赛克", "灰度", "油画", "轮廓"};
        for (String btnText : btnTexts) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.white);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
        String[] btnTests2 = {"<截图>", "<画笔>", "<马赛克笔>", "<灰度笔>"};
        for (String btnText : btnTests2) {//增强的for循环遍历每个按钮
            JButton btn = new JButton(btnText);
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btnPanel.add(btn);
            btn.addActionListener(imgl);//添加监听器
        }
    }


    public void initListPanel(JPanel listPanel) {//初始化列表面板
        listPanel.setLayout(new BorderLayout());
        String rootpath = "D:\\桌面\\Xing\\Photos";
        JTextField jtf = new JTextField();
        listPanel.add(jtf, BorderLayout.NORTH);

        JPanel imgBtnPanel = new JPanel();
        imgBtnPanel.setLayout(new GridLayout(0, 1));
        File rootfile = new File(rootpath);
        File[] files = rootfile.listFiles();
        if (files != null) {
            for (File imgFile : files) {//遍历对应目录下每个图-片文件，并为之设置图片按钮
                JButton btn = new JButton();
                btn.setContentAreaFilled(false);//移除按钮的背景填充
                BufferedImage btnImage = imgUtils.getBtnImage(imgFile);//由于在单个循环内，每个画笔的坐标xy即指单个btn上的坐标
                btn.setIcon(new ImageIcon(btnImage));//设置按钮的图标
                btn.setBackground(new Color(200, 200, 200));
                btn.setPreferredSize(new Dimension(imgBtnFrameW, imgBtnFrameH));

                imgBtnPanel.add(btn);//

                //为按钮添加监听器
                btn.addActionListener(_ -> {
                    String fileName = imgFile.getName();
                    jtf.setText(rootpath + "\\" + fileName);
                    imgUtils.loadImage(jtf.getText());
                    BufferedImage img = imgUtils.drawImage();
                    ImageListener.imgList.add(img);
                    imgPanel.repaint();// 在ImagePanel类中调用，用来重绘图像面板
                });

            }
            //添加滑动窗口
            JScrollPane jsp = new JScrollPane(imgBtnPanel);
            jsp.setViewportView(imgBtnPanel);/////////////////////////////////////////////////////////////////////
            listPanel.add(jsp, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageProUI::new);//将 GUI 初始化任务提交到 Swing
        // 的事件调度线程（Event Dispatch Thread, EDT）
        //使用前提:以上代码的目标类(ImagePanel)必须有构造函数并且在构造函数中调用具体的界面按绘制方法(showUI)
    }
}
