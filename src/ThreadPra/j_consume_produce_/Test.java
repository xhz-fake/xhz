package ThreadPra.j_consume_produce_;

public class Test {
    public static void main(String[] args) {
        BaoZiPu baoZiPu = new BaoZiPu();//new一个包子铺,将其作为参数传入两个线程类的构造器里

        Producer producer = new Producer(baoZiPu);
        Consumer consumer = new Consumer(baoZiPu);

        Thread t1 = new Thread(producer, "生产者");
        Thread t2 = new Thread(consumer, "消费者");

        t1.start();
        t2.start();
    }
}
