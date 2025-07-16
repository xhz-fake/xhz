package ThreadPra.g_synchronized;

public class Test01 {
    public static void main(String[] args) {
        MyTicket myTicket=new MyTicket();
        System.out.println(myTicket);

        Thread t1=new Thread(myTicket,"马牛逼");
        Thread t2=new Thread(myTicket,"蔡徐坤");
        Thread t3=new Thread(myTicket,"鸽哥");

        t1.start();
        t2.start();
        t3.start();
    }
}
