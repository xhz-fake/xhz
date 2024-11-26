package Inherit;

public class Game {
    public static void main(String[] args) {
        Kert kt = new Kert();
        kt.name = "科特";
        kt.id = "AUG";
        kt.ad = 49;
        kt.hp = 440;
        kt.df = 90;
        kt.speed = 80;
        kt.showInfo();

        Bernand bnd = new Bernand();
        bnd.name = "伯纳德";
        bnd.id = "M110";
        bnd.hp = 440;
        bnd.df = 80;
        bnd.speed = 90;
        bnd.ad = 72;
        bnd.showInfo();

        kt.showTime();
        bnd.showTime();
        kt.killinonescond();

            kt.atk(bnd);
            bnd.atk(kt);

    }
}
