package Practice;

import java.util.Scanner;

interface Runable<T> {
    void run(T t);
}

class People implements Runable {
    private String name;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void run(Object o) {
        System.out.println("People " + name + " is running");
    }

}

class Car implements Runable {
    private String  carCode;

    public Car(String  carCode) {
        this.carCode = carCode;
    }

    @Override
    public void run(Object o) {
        System.out.println("Car " + carCode + " is running");
    }
}

public class pra251020_2 {
    public static void main(String[] args) {
        Scanner in  =new Scanner(System.in);
        int n =in.nextInt();
        while(n-->0){
            String s = in.next();
            if(s.equals("People")){
                String name  = in.next();
                People people =new People(name);
                people.run(people);
            }else{
                String carCode  = in.next();
                Car car  = new Car(carCode);
                car.run(car);

            }
        }
    }
}
