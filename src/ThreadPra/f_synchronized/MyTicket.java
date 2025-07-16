package ThreadPra.f_synchronized;

public class MyTicket implements Runnable {
    int ticket = 10;//定义100张票

    //任意new一个对象
    final Object obj=new Object();

    @Override
    public void run() {//买票
        while (true) {
            try {//一定延时
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (obj) {//方法一:对于代码块(可能出现线程安全问题的)
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了第" + ticket + "张票");
                    ticket--;
                }
            }
        }
    }
}
