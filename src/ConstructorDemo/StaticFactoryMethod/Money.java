package ConstructorDemo.StaticFactoryMethod;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {//优势之--实例控制--(不可变对象)
    private final BigDecimal amount;
    private final Currency currency;

    private Money (BigDecimal amount , Currency currency){
        this.amount=amount;
        this.currency=currency;
    }

    public static Money of(BigDecimal amount , Currency currency){
        return new Money(amount.setScale(currency.getDefaultFractionDigits()), currency);
    }
}
