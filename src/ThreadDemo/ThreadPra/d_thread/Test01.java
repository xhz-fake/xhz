package ThreadDemo.ThreadPra.d_thread;

public class Test01 {
    public static void main(String[] args) {
        Mythread1 t1=new Mythread1();
        t1.setName("金莲");

        t1.start();

        try {
            t1.join();//表示把t1插入到当前线程之前,当前线程就是main线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<=30;i++){
            System.out.println(Thread.currentThread().getName()+"执行了"+i);
        }

    }
}
