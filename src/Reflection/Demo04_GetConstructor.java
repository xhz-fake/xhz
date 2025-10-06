package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo04_GetConstructor {//获取空参构造_public
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> aClass = Person.class;
        Constructor<Person> constructor = aClass.getConstructor();
        System.out.println(constructor);

        Person person  = constructor.newInstance();//类似于 Person Person = new Person();
        System.out.println(person);
    }
}
