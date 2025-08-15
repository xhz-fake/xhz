package ConstructorDemo.StaticFactoryMethod;

import java.math.BigDecimal;

interface PaymentProcessor {
    void processPayment(BigDecimal amount);
}

public class PaymentFactory {
    public static PaymentProcessor create(String type) {
        switch (type) {
            case "CREDIT_CARD":
                return new CreditProcessor();
            case "PAYPAL_CARD":
                return new PayPalProcessor();
            default:
                throw new IllegalArgumentException("不支持的支付类型: " + type);
        }
    }
}

class PayPalProcessor implements PaymentProcessor {//优势之---返回子类型(接口编程)

    @Override
    public void processPayment(BigDecimal amount) {
        //具体的PayPal处理逻辑
        System.out.println("PayPal");
    }
}

class CreditProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        //具体的Credit处理逻辑
        System.out.println("CREDIT");
    }
}


