package ThreadPra.o_ThreadPool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "666";
    }
}
