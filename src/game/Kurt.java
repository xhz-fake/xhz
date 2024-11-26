package game;

public class Kurt {
    String name;
    int HP;
    int df;
    int at;
    int lv;

    public void init(String playerID) {
        name = "科特（" + playerID + ")";
        HP = 440 + df;
        df = 75;
        at = 49;
        lv = 1;
        System.out.println(name + "初始化完成，加载成功！");
    }

    public void uplv() {
        lv++;
        at += 5;
        df += 3;
        System.out.println(name + "已升级，目前等级为" + lv);
    }

    public void atk(Bernard bn) {
        int num = (int) (at + lv * 1.2);
        bn.HP -= num;
        System.out.println(name + "攻击了" + bn.name + "对其造成了" + num + "点伤害" + "目前的血量为" + bn.HP);

    }

    public void atk(Rolf rf) {
        int num = (int) (at + lv * 1.2);
        rf.HP -= num;
        System.out.println(name + "攻击了" + rf.name + "对其造成了" + num + "点伤害" + "目前的血量为" + rf.HP);
    }

}



