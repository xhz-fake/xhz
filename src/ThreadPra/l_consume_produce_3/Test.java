package ThreadPra.l_consume_produce_3;

public class Test {//多等待,多唤醒,多生产,多消费

    public static void main(String[] args) {
        BaoZiPu baoZiPu = new BaoZiPu();//new一个包子铺,将其作为参数传入两个线程类的构造器里

        Producer producer = new Producer(baoZiPu);
        Consumer consumer = new Consumer(baoZiPu);

        new Thread(producer, "生产者1").start();
        new Thread(producer, "生产者2").start();
        new Thread(producer, "生产者3").start();

        new Thread(consumer, "消费者1").start();
        new Thread(consumer, "消费者2").start();
        new Thread(consumer, "消费者3").start();
    }
}
