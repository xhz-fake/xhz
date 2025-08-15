package ThreadDemo.ThreadPra.n_callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable=new MyCallable();
        FutureTask<String> futureTask=new FutureTask<>(myCallable);//FutureTask<>获取call方法的返回值

        // 创建Thread对象 -> Thread(Runnable target)
        Thread t1=new Thread(futureTask);
        t1.start();

        //调用get方法获取call方法的返回值
        System.out.println(futureTask.get());

    }
}
