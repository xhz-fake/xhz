package ThreadPra.j_consume_produce;

public class Test {
    public static void main(String[] args) {
        MyRunnable myRunnable=new MyRunnable();
        Thread consumer=new Thread(myRunnable,"消费者");
        Thread producer=new Thread(myRunnable,"生产者");

        consumer.start();
        producer.start();
    }
}
