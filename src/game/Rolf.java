package game;

public class Rolf {

    String name;
    int HP;
    int df;
    int at;
    int lv;

    public void init(String playerID) {
        name = "罗尔夫（" + playerID + ")";
        HP = 440 + df;
        df = 90;
        at = 35;
        lv = 1;
        System.out.println(name + "初始化完成，加载成功！");
    }

    public void uplv() {
        lv++;
        at += 2;
        df += 3;
        System.out.println(name + "已升级，目前等级为" + lv);
    }
    public void atk(Bernard bn) {
        int num = (int) (at + lv * 1.2);
        bn.HP -= num;
        System.out.println(name + "攻击了" + bn.name + "对其造成了" + num + "点伤害" + "目前的血量为" + bn.HP);

    }
    public void atk(Kurt kt) {
        int num = (int) (at + lv * 1.5);
        kt.HP -= num;
        System.out.println(name + "攻击了" + kt.name + "，对其造成了" + num + "点伤害" + "目前的血量为" + kt.HP);

    }

}
