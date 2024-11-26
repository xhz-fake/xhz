package Inherit;

public class Kert extends Hero{
  public void showTime(){
      super.showTime();
      System.out.println("此人是电视台男团的老大");
  }
  public void killinonescond(){
      System.out.println(name+"特有技能：秒杀");
  }
  public void atk(Hero bnd){
    int num=(int)(30+ad*1.5+speed*0.5);
    num-=bnd.df;
    if (num>0){
        bnd.hp-=num;
        System.out.println(name+"给了"+bnd.name+"一枪"+"使其生命值减少了"+num+"其还剩下"+bnd.hp+"点生命值");
    }
else{
        System.out.println(name+"给了"+bnd.name+"一枪"+"未能击穿HTac-A5护甲");
    }

  }

}
