package Inherit;

public class Hero {
    int hp;
    int ad;
    int df;
    int speed;
    String name;
    String id;

public void showInfo(){
    System.out.println("name="+name+";"+"id="+id+";"+"hp="+hp+";"+"ad="+ad+";"+"df="+df+";"+"speed="+speed+";");
}
public void showTime(){
    System.out.println("-----"+name+"精彩时刻---");

}
}
