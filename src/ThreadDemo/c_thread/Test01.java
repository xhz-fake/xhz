package ThreadDemo.c_thread;

public class Test01 {
    public static void main(String[] args) {
        Mythread1 t1=new Mythread1();
        t1.setName("金莲");

        Mythread1 t2=new Mythread1();
        t2.setName("庆子");

//        //1.设置线程优先级
//        t1.setPriority(1);//最小
//        t2.setPriority(10);//最大
//        //t2.setPriority(5);//默认
//        //2.获取2个线程的优先级

//        //设置线程守护-->非守护线程执行完毕,守护线程在接收到执行完毕的信息后停止执行(即并非立刻停止)
//        t2.setDaemon(true);

        //3.设置礼让线程(让两个交替执行的线程平衡一些,防止某个线程绝对压制)


        t1.start();
        t2.start();
    }
}
