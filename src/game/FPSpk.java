package game;

public class FPSpk {
    public static void main(String[] args) {
//分类
        Bernard bn1 = new Bernard();
        Bernard bn2 = new Bernard();
        Kurt kt1 = new Kurt();
        Kurt kt2 = new Kurt();
        Rolf rf = new Rolf();
//初始化
        bn1.init("fake");
        bn2.init("dark");
        kt1.init("drawn");
        kt2.init("dask");
        rf.init("muscle");
//调用方法 相互攻击
        int round = 0;
        while (bn1.HP > 0 && kt1.HP > 0) {
            bn1.atk(kt1);
            kt1.atk(bn1);
            round++;
            if (round == 2) {
                bn1.uplv();
                kt1.uplv();
                round = 0;
            }
        }
        if (bn1.HP <= 0 && kt1.HP > 0) {
            System.out.println("科特胜出");
            round = 0;
            while (kt1.HP > 0 && rf.HP > 0) {
                kt1.atk(rf);
                round++;
                if (round == 2) {
                    rf.uplv();
                    kt1.uplv();
                    round = 0;
                }
            }
            if (kt1.HP<=0 && rf.HP>0){
                System.out.println("罗尔夫最终胜出");
            }
            if (kt1.HP>0 && rf.HP<=0){
                System.out.println("科特最终胜出");
            }
        }
        if (bn1.HP > 0 && kt1.HP <= 0) {
            System.out.println("伯纳德胜出");
            round = 0;
            while (bn1.HP > 0 && rf.HP > 0) {
                bn1.atk(rf);
                rf.atk(bn1);
                round++;
                if (round == 2) {
                    rf.uplv();
                    bn1.uplv();
                    round = 0;
                }
            }
            if (bn1.HP<=0 && rf.HP>0){
                System.out.println("罗尔夫最终胜出");
            }
            if (bn1.HP>0 && rf.HP<=0){
                System.out.println("伯纳德最终胜出");
            }

        }

    }
}