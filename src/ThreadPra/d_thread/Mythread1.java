package ThreadPra.d_thread;

public class Mythread1 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<=30;i++){
            System.out.println(Thread.currentThread().getName()+"执行了"+i);
        }
    }
}
