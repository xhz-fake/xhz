package ConstructorDemo.StaticFactoryMethod;

public class PizzaDemo {
    public static void main(String[] args) {
        Pizza pizza = Pizza.builder(Size.LARGE).addPepperoni()
                .addBacon()
                .addCheese()
                .addMushroom()
                .build();
        System.out.println("定制的披萨"+pizza);
    }
}
