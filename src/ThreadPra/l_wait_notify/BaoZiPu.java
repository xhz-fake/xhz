package ThreadPra.l_wait_notify;

public class BaoZiPu {

    private int count;
    private boolean flag;

    public synchronized void SubCount() {//有包子则消费
        //判断 flag是否为true
        while (!this.flag) {//1. 保证了解除了等待状态并抢到锁的的线程再次经过判断flag的过程 以保证生产消费的正常交替
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Thread.currentThread().getName() + "消费了第" + count + "个包子-----");//2.有包子则输出
        this.flag = false;//3. 标记为无包子(已消费)

        this.notifyAll();//4.消费后唤醒所有线程
    }

    public synchronized void AddCount() {

        while (this.flag) {//1.  保证解除了等待状态的线程再次判断flag的值以保证生产消费的正常交替
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        count++;//2.无包子则生产
        System.out.println(Thread.currentThread().getName() + "生产了第" + count + "个包子+++++");

        this.flag = true;//3. 标记为有包子

        this.notifyAll();//4. 生产后唤醒所有线程
    }


}
