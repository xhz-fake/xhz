package ThreadDemo;

public class UserThread implements Runnable{
    public UserAccount account;

    public UserThread(UserAccount account) {
        this.account = account;
    }

    public void run(){
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(account.money-60>=0){
                this.take();
            }else{
                if( Thread.currentThread().getName().equals("三石")){
                   this.deposit();
                }else if(Thread.currentThread().getName().equals("鸽哥")){
                   continue;
                }
            }
        }
    }

    protected synchronized void deposit(){
        account.money+=100;
        System.out.println(Thread.currentThread().getName()+" 对当前账户存入"+100+",还剩"+account.money);
    }

    protected synchronized void take(){
        account.money-=60;
        System.out.println(Thread.currentThread().getName()+" 对当前账户取出"+60+",还剩"+account.money);
    }
}
