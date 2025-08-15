package ThreadDemo.ThreadPra.o_ThreadPool;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<2;i++) {
            System.out.println(Thread.currentThread().getName() + "在执行"+i);
        }
    }
}
