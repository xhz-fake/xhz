package ThreadPra.k_consume_produce_2;

public class BaoZiPu {

    private int count;
    private boolean flag;

    public synchronized void SubCount() {//有包子则消费
        //判断 flag是否为true
        if (!this.flag) {//1.判断 flag是否为false ,无则等待,有则消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("消费了第" + count + "个包子-----");//2.有包子则输出
        this.flag = false;//3. 标记为无包子(已消费)

        this.notify();//4.消费后唤醒生产者线程

    }

    public synchronized void AddCount() {

        if (this.flag) {//1.判断 flag是否为true ,有则等待,无则生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        count++;//2.无包子则生产
        System.out.println("生产了第" + count + "个包子+++++");

        this.flag = true;//3. 标记为有包子

        this.notify();//4. 生产后唤醒消费者
    }


}
