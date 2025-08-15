package ThreadDemo.ThreadPra.g_synchronized;

public class MyTicket implements Runnable {
    int ticket = 1000;//定义100张票

    @Override
    public void run() {//买票
        while (true) {
            try {//一定延时
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            method01();
            method02();
        }

    }

//    public synchronized void method01(){//方法2:单独写一个非静态的synchronized方法独立于run方法(在run中调用即可)
//        if (ticket > 0) {
//            System.out.println(Thread.currentThread().getName() + "买了第" + ticket + "张票");
//            ticket--;
//        }
//    }

    public void method02() {//方法3:单独写一个非静态的方法独立于run方法并在核心代码块外包裹synchronized(在run中调用即可)
        synchronized (this) {//默认锁:this(当前唯一new的对象)
            System.out.println(this);
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "买了第" + ticket + "张票");
                ticket--;
            }
        }
    }
}
