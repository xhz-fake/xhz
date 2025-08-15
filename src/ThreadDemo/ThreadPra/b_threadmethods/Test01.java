package ThreadDemo.ThreadPra.b_threadmethods;

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1=new MyThread();//创建线程对象
        t1.setName("赵四");

        t1.start();//调用start开启线程,jvm自动调用run方法

        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"线程--------"+i);
        }
    }
}
