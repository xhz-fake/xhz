package ThreadDemo.ThreadPra.d_thread;

public class Mythread1 extends Thread{//实现多线程的方式1-> 继承Thread
    @Override
    public void run() {
        for(int i=0;i<=30;i++){
            System.out.println(Thread.currentThread().getName()+"执行了"+i);
        }
    }
}
