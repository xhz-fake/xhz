package ThreadDemo.ThreadPra.f_synchronized;

public class Test01 {
    public static void main(String[] args) {
        MyTicket myTicket=new MyTicket();

        Thread t1=new Thread(myTicket,"马牛逼");
        Thread t2=new Thread(myTicket,"蔡徐坤");
        Thread t3=new Thread(myTicket,"鸽哥");

        t1.start();
        t2.start();
        t3.start();
    }
}
