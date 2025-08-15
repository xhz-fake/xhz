package ThreadDemo.ThreadPra.k_consume_produce_2;

public class Consumer implements Runnable {

    BaoZiPu baoZiPu;

    public Consumer(BaoZiPu baoZiPu) {
        this.baoZiPu = baoZiPu;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            baoZiPu.SubCount();
        }
    }
}
