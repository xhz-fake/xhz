package ThreadPra.i_deadlock;

public class Test {
    public static void main(String[] args) {
        Deadlock deadlock1=new Deadlock(true);
        Deadlock deadlock2=new Deadlock(false);

        new Thread(deadlock1).start();
        new Thread(deadlock2).start();


    }
}
