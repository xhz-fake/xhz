package ThreadPra.e_runnable;

public class MyRunnable implements Runnable{////实现多线程的方式2->实现接口
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println(Thread.currentThread().getName()+"...执行了"+i);
        }
    }
}
