package ThreadDemo.ThreadPra.j_consume_produce_1;

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

            synchronized (baoZiPu) {
                //判断 flag是否为true
                if (baoZiPu.isFlag()) {//有包子则生产线程等待
                    try {
                        baoZiPu.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }//没包子则生产
                baoZiPu.AddCount();
                baoZiPu.setFlag(true);
                //生产后唤醒消费者
                baoZiPu.notify();

            }
        }
    }
}
