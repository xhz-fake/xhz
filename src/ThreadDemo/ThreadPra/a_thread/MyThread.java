package ThreadDemo.ThreadPra.a_thread;

public class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            System.out.println("my thread---"+i);
        }
    }
}
