package ThreadPra.e_runnable;

public class Test01 {
    public static void main(String[] args) {
        MyRunnable myRunnable=new MyRunnable();
        Thread t1=new Thread(myRunnable);
        //调用Thread中的方法开启线程
        t1.start();
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"...执行了"+i);
        }
    }
}
