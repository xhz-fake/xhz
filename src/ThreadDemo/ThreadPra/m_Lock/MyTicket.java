package ThreadDemo.ThreadPra.m_Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTicket implements Runnable {//通过Lock对象实现获取及释放锁

    int ticket = 10;
    Lock lock1 = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {//一定延时
                Thread.sleep(100);
                //获取锁
                lock1.lock();
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了第" + ticket + "张票");
                    ticket--;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock1.unlock();//释放锁
            }
        }
    }
}
