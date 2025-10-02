package Reflection;

import org.junit.Test;

public class Demo01_GetClass {
    @Test
    public void get1() throws ClassNotFoundException {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();//1
        System.out.println("aClass=" + aClass);

        Class <Person> aClass2 = Person.class;//2
        System.out.println("aClass2=" + aClass2);

        Class<?> aClass3  = Class.forName("Reflection.Person");//3最常用,参数为String
        System.out.println("aClass3="+aClass3);

    }


}
