package ThreadDemo.ThreadPra.n_callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {//实现多线程的方式3 ->实现Callable接口

    @Override
    public String call() throws Exception {
        return "要塞军阀-鸽哥的故事";//线程任务
    }


}
