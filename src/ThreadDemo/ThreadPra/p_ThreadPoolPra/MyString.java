package ThreadDemo.ThreadPra.p_ThreadPoolPra;

import java.util.concurrent.Callable;

public class MyString implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "鸽哥";
    }
}
