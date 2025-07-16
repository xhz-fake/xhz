package ThreadPra.e_runnable;

public class MyRunnable implements Runnable{//创建线程的第二种方式-->实现接口
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()+"...执行了"+i);
        }
    }
}
