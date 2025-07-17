package ThreadPra.j_consume_produce;

public class MyRunnable implements Runnable{
    private int count =0;
    public  boolean flag=false;

    final Object lock=new Object();
    @Override
    public void run() {
        while (count<=100){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock){
                if(!flag){
                    System.out.println(Thread.currentThread().getName()+"处理了第"+count+"杯冰美式");
                }else{
                    count++;
                    System.out.println(Thread.currentThread().getName()+"处理了第"+count+"杯冰美式");
                }
            }
        }
    }
}
