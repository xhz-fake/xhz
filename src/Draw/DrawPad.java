package Draw;
import java.awt.*;//抽象窗口工具集
import javax.swing.*;//图形用户界面工具包，提供大量组件


public class DrawPad {


    public void showUI() {
        JFrame jf = new JFrame();
        jf.setTitle("画图板 v2.0");
        jf.setSize(700, 800);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("d:\\桌面\\f54e1348e201b85e9360df321c98807.png");
        jf.setIconImage(icon.getImage());////////////////////////////////////////////////////////////////////////////////////////
        jf.setLayout(new FlowLayout());
        DrawListener dl=new DrawListener();

        String[] btnText = {"直线", "圆形", "空心圆", "矩形", "空心矩形"};
        for (int i = 0; i < btnText.length; i++) {
            String text=btnText[i];
            JButton btn = new JButton(text);
            btn.addActionListener(dl);
            jf.add(btn);
        }

        String[] colortext = {"RED", "YELLOW", "BLUE", "GREEN", "BLACK", "橡皮擦"};
        Color[] colors = {Color.red, Color.YELLOW, Color.BLUE, Color.GREEN, Color.BLACK, new Color(241, 217, 217, 253)};
        for (int i = 0; i < colors.length; i++) {
            JButton btn = new JButton(colortext[i]);
            btn.setBackground(colors[i]);
            btn.addActionListener(dl);
            jf.add(btn);
        }

        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.addMouseListener(dl);
        Graphics g= jf.getGraphics();
        dl.g1=g;
    }

        public static void main (String[]args){
            DrawPad drawPad = new DrawPad();
            drawPad.showUI();
        }
}