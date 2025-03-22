package TankWar_v_2_0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GamePanel extends JPanel implements KeyListener {
    private  TankA tankA;
    private  TankB tankB;
    private final Set<Integer> pressedKeys = new HashSet<>();
    private final Timer gameTimer;
    private final Random ran = new Random();
    private final BattleMaps map;
    private final scorePanel sPanel;

    public GamePanel() {
        map = new BattleMaps();
        sPanel=new scorePanel();

        // 生成坦克A的合法位置
        tankA = generatePositionA(45, 35);
        tankB = generatePositionB(45, 35);
        // 生成坦克B的合法位置（且不与A重叠）

        // 初始化游戏定时器（每16ms≈60FPS）
        //使用游戏循环（Timer）来定期处理按键状态，更新坦克位置。
        gameTimer = new Timer(16, e -> {
            processInput();// 处理输入
            updateGame();// 更新游戏状态
            repaint(); // 请求重绘
        });
        gameTimer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    private TankA generatePositionA(int width, int height) {
        Random ran = new Random();
        Rectangle tempRect;
        int x, y;
        do {
            x = 50 + ran.nextInt(900);
            y = 50 + ran.nextInt(700);
            tempRect = new Rectangle(x, y, width, height);
        } while (map.isCollidingWithWall(tempRect)); // 确保不生成在墙上
        return new TankA(x, y); // 或 TankB
    }

    private TankB generatePositionB(int width, int height) {
        Random ran = new Random();
        Rectangle tempRect;
        int x, y;
        do {
            x = 50 + ran.nextInt(900);
            y = 50 + ran.nextInt(700);
            tempRect = new Rectangle(x, y, width, height);
        } while (map.isCollidingWithWall(tempRect)); // 确保不生成在墙上
        return new TankB(x, y); // 或 TankB
    }

    private void processInput() {
        // 处理坦克A
        // 每次循环先重置速度
        tankA.setSpeedX(0);
        tankA.setSpeedY(0);
        // 根据当前按下的键设置速度
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            tankA.setDirection(0);
            tankA.setSpeedX(-4);
        }
        if (pressedKeys.contains(KeyEvent.VK_D)) {
            tankA.setDirection(2);
            tankA.setSpeedX(4);
        }
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            tankA.setDirection(1);
            tankA.setSpeedY(-4);
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            tankA.setDirection(3);
            tankA.setSpeedY(4);
        }

        // 处理坦克B
        tankB.setSpeedX(0);
        tankB.setSpeedY(0);
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            tankB.setDirection(0);
            tankB.setSpeedX(-4);
        }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            tankB.setDirection(2);
            tankB.setSpeedX(4);
        }
        if (pressedKeys.contains(KeyEvent.VK_UP)) {
            tankB.setDirection(1);
            tankB.setSpeedY(-4);
        }
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
            tankB.setDirection(3);
            tankB.setSpeedY(4);
        }
    }

    private void updateGame() {
      handleTankMovement(tankA);
      handleTankMovement(tankB);
    }

    private void handleTankMovement(MoveObjects tank){
        //保存移动前的位置
        int oldX=tank.getX();
        int oldY=tank.getY();
        //移动坦克
        tank.move();
        // 获取移动后的碰撞区域
        Rectangle newBounds=tank.getBounds();

        //检测是否会与墙体发射碰撞
        if(map.isCollidingWithWall(newBounds)){//碰撞后回退位置并重置速度
            tank.setX(oldX);
            tank.setY(oldY);
            tank.setSpeedX(0);
            tank.setSpeedY(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {//自动启用Swing双缓冲，避免闪烁
        super.paintComponent(g);// 清空背景，清除前一帧画面
        Graphics2D g2d = (Graphics2D) g.create();
        map.paintMap(g2d);
        tankA.drawTankA(g2d);
        tankB.drawTankB(g2d);
        sPanel.drawTankPicture(g2d);
        g2d.dispose();//保证图形状态隔离
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        // 处理平滑停止（可选）
        processInput();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
/*

注意：

（1）Swing的绘图应该在事件分派线程（EDT）中进行，使用多线程可能导致不可预测的行为。因此，应该将所有绘
 图逻辑放在一个主循环中，使用Swing的Timer来定期触发重绘，而不是在独立的线程中使用while循环和
 Thread.sleep

（2）KeyListener的keyPressed事件机制设计为单次触发模式，无法跟踪组合按键状态
当同时按下多个键时，操作系统会快速交替触发多个keyPressed事件，但无法保持持续状态

（3）Swing使用被动绘制机制，应重写paintComponent()方法getGraphics()获取的是临
时图形上下文，无法持久化
*/
















