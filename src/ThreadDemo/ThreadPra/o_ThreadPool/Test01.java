package ThreadDemo.ThreadPra.o_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test01 {
    public static void main(String[] args) {

        ExecutorService pool=Executors.newFixedThreadPool(2); //创建线程池对象
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());

        pool.shutdown();//关闭线程池对象,若不关闭则线程池等待新任务来
    }
}
