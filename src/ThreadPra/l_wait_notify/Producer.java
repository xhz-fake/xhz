package ThreadPra.l_wait_notify;

public class Producer implements Runnable {

    BaoZiPu baoZiPu;

    public Producer(BaoZiPu baoZiPu) {
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

            baoZiPu.AddCount();
        }
    }
}
