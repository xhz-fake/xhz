package ThreadPra.j_consume_produce_1;

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

            synchronized (baoZiPu) {
                //判断 flag是否为true
                if (!baoZiPu.isFlag()) {//无包子则消费线程等待
                    try {
                        baoZiPu.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }//有包子则消费
                baoZiPu.SubCount();
                baoZiPu.setFlag(false);
                //消费后唤醒生产者线程
                baoZiPu.notify();

            }
        }
    }
}
