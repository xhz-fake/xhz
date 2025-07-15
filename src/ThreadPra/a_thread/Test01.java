package ThreadPra.a_thread;

public class Test01 {
    public static void main(String[] args) {
        MyThread t1=new MyThread();//创建线程对象
        t1.start();//调用start开启线程,jvm自动调用run方法

        for(int i=0;i<10;i++){
            System.out.println("main线程--------"+i);
        }
    }
}
