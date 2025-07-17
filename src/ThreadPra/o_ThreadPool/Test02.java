package ThreadPra.o_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test02  {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池对象
        ExecutorService es= Executors.newFixedThreadPool(2);
        Future<String> future=es.submit(new MyCallable());//Future接受call方法返回值:666
        System.out.println(future.get());//输出获取的返回值

    }
}
