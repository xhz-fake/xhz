package ThreadPra.h_synchronized;

public class MyTicket implements Runnable {
    static int ticket = 1000;//

    @Override
    public void run() {//具体操作
        while (true) {
            try {//延时
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            method03();
        }
    }

    public static void method03(){//方法4:静态同步方法
        synchronized (MyTicket.class) {//默认锁:class对象
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "买了第" + ticket + "张票");
                ticket--;
            }
        }
    }

}
