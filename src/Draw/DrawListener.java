package Draw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawListener implements MouseListener, ActionListener {
    Graphics g1;
    int x1, x2, y1, y2;
    String type ;

    public void actionPerformed(ActionEvent event) {
        System.out.println("点击按钮");
        String btnText = event.getActionCommand();
        System.out.println(btnText);

if(btnText.equals("RED")){
    g1.setColor(Color.red);
}else if (btnText.equals("YELLOW")){
    g1.setColor(Color.YELLOW);
}else if(btnText.equals("BLUE")){
    g1.setColor(Color.BLUE);
}else if (btnText.equals("GREEN")){
    g1.setColor(Color.GREEN);
}else if(btnText.equals("BLACK")){
    g1.setColor(Color.black);
}else if (btnText.equals("橡皮擦")){g1
        .setColor(new Color(237,237,237));
    type="圆形";
}else {
    type=btnText;///////////////////////////////////////////////
}

    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("点击");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("按下");
        int x = e.getX();
        int y = e.getY();
        System.out.println("x:" + x + "y:" + y);
        x1 = x;
        y1 = y;
    }

    public void mouseReleased(MouseEvent event) {
        System.out.println("释放");
        x2 = event.getX();
        y2 = event.getY();
        int a = x2 - x1;
        int b = y2 - y1;
        a = a < 0 ? -a : a;
        b = b < 0 ? -b : b;
        int x=x2<x1 ? x2:x1;
        int y = y2 < y1 ? y2 : y1;
        if ("直线".equals(type)){
            g1.drawLine(x1,y1,x2,y2);
        } else if ("矩形".equals(type)) {
            g1.fillRect(x,y,a,b);
        } else if ("空心矩形".equals(type)) {
            g1.drawRect(x,y,a,b);
        }else if("圆形".equals(type)){
            g1.fillOval(x,y,a,b);
        }else if ("空心圆".equals(type)) {
            g1.drawOval(x, y, a, b);
        }

    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("进入");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("离开");
    }
}

