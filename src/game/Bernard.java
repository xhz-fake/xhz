package game;

public class Bernard {
    String name;
    int HP;
    int df;
    int at;
    int lv;

    public void init(String playerID) {
        name = "伯纳德(" + playerID + ")";
        df = 55;
        HP = 440 + df;
        at = 75;
        lv = 1;
        System.out.println(name + "初始化已完成，加载成功！");
    }

    public void uplv() {
        lv++;
        at += 10;
        df += 2;
        System.out.println(name + "已升级，目前等级为" + lv);
    }

    public void atk(Kurt kt) {
        int num = (int) (at + lv * 1.5);
        kt.HP -= num;
        System.out.println(name + "攻击了" + kt.name + "，对其造成了" + num + "点伤害" + "目前的血量为" + kt.HP);

    }
    public void atk(Rolf rf) {
        int num = (int) (at + lv * 1.2);
        rf.HP -= num;
        System.out.println(name + "攻击了" + rf.name + "对其造成了" + num + "点伤害" + "目前的血量为" + rf.HP);
    }

}

