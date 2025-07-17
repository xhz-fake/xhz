package ThreadPra.j_consume_produce_;

public class BaoZiPu {

    private int count;
    private boolean flag;

    public BaoZiPu(int count, boolean flag) {
        this.count = count;
        this.flag = flag;
    }

    public BaoZiPu() {
    }

    public void SubCount() {
        System.out.println("消费了第" + count + "个包子-----");
    }

    public void AddCount() {
        count++;
        System.out.println("生产了第" + count + "个包子+++++");
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
