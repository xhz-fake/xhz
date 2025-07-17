package ThreadPra.j_consume_produce_;

public class Producer implements  Runnable {
    final BaoZiPu baoZiPu=new BaoZiPu();
    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (baoZiPu){
                //判断 flag是否为true
                if(baoZiPu.isFlag()){//有包子则等待
                    try {
                        baoZiPu.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }//没包子则生产
                    baoZiPu.AddCount();
                    baoZiPu.setFlag(true);

            }
        }
    }
}
