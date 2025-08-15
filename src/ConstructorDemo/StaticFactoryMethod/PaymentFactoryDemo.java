package ConstructorDemo.StaticFactoryMethod;

import java.math.BigDecimal;

public class PaymentFactoryDemo {
    public static void main(String[] args) {
        PaymentProcessor processor= PaymentFactory.create("PAYPAL_CARD");
        processor.processPayment(new BigDecimal("99.99"));
    }
}
