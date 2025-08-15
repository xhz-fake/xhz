package ConstructorDemo.ConstructorMethod;

public class Car {
    private String brand;
    private int price;

    //有参构造1
    public Car(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    //有参构造2
    public Car(String brand) {
        this(brand, 100000);//调用上面的双参构造器
    }
}

class ElectricCar extends Car {
    private int battery;

    public ElectricCar(String brand, int price, int battery) {
        super(brand, price);//先初始化父类(调用父类构造器)
        this.battery = battery;
    }
}

class GasCar extends Car {
    private int gas;

    public GasCar() {
        this(100);//// 调用GasCar类的其他构造器
    }

    public GasCar(int gas) {
        super("车车");
    }
}
