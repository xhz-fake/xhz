package ThreadPra.n_callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "要塞军阀-鸽哥的故事";//线程任务
    }


}
