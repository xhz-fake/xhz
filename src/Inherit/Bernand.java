package Inherit;

public class Bernand extends Hero{
    public void showTime() {
      super.showTime();
        System.out.println(name + "此人曾秒杀全装哥");
        System.out.println("此人曾百米用M80秒人");
    }
    public void atk(Kert kt) {
        int num = (int) (30+ ad * 2.5);
        num -= kt.df;
        if (num > 0) {
            kt.hp -= num;
            System.out.println(name + "给了" + kt.name + "一枪，使其HP减少了" + num +
                    "点。其还剩下" + kt.hp);
        } else {
            System.out.println(name + "给了" + kt.name + "一枪，并未击穿AL制式弹挂甲");
        }
    }
}
