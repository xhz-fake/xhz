package ThreadDemo;

public class Test {
    public static void main(String[] args) {
        UserAccount account=new UserAccount(100,"鸽哥",666666);
        UserThread thread=new UserThread(account);
        new Thread(thread,"鸽哥").start();
        new Thread(thread,"三石").start();
    }
}
