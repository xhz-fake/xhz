package ThreadDemo.ThreadPra.c_thread;

public class Mythread1 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"执行了"+i);
//          Thread.yield(); //3.设置礼让线程(让两个交替执行的线程平衡一些,防止某个线程绝对压制)
        }
    }
}
