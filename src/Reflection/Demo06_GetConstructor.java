package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo06_GetConstructor {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取Class对象
        Class<Person> aClass = Person.class;

        Constructor<Person> constructor = aClass.getConstructor(String.class,Integer.class);
        System.out.println(constructor);

        //创建对象, 类似于Person person =  new Person("三上",26);
        Person person = constructor.newInstance("三上",26);
        System.out.println(person);

    }
}
