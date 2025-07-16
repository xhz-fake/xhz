package ThreadPra.i_deadlock;

public class Deadlock implements Runnable {

    private final boolean flag;

    public Deadlock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (LockA.locka) {
                System.out.println("if...locka");
                synchronized (LockB.lockb) {
                    System.out.println("if...lockb");
                }
            }
        } else {
            synchronized (LockB.lockb) {
                System.out.println("else...lockb");
                synchronized (LockA.locka) {
                    System.out.println("else...locka");
                }
            }
        }
    }
}
