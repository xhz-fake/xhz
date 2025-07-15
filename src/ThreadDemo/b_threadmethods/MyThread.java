package ThreadDemo.b_threadmethods;

public class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){

            try {//线程睡眠  //因为Thread类中的run方法没有抛出异常,所以只能try catch不能throw
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(getName()+"执行了"+i);
        }
    }
}
