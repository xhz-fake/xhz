package ThreadDemo.ThreadPra.e_runnable;

public class Test02 {
    public static void main(String[] args) {
        Thread t2=new Thread(new Runnable(){//匿名内部类实现Runnable接口
            @Override
            public void run() {
                for(int i=0;i<4;i++){
                    System.out.println(Thread.currentThread().getName()+"...执行了"+i);
                }
            }
        });
        t2.setName("t2");
        t2.start();

    }
}
