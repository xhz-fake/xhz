package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo08_GetConstructor {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> aClass = Person.class;
        Constructor<Person> dc = aClass.getDeclaredConstructor(String.class);//特定参数
        System.out.println(dc);

        dc.setAccessible(true);//接触私有构造的私有权限->暴力反射

        Person person = dc.newInstance("三上");//反射无法直接对私有构造进行对象赋值
        System.out.println(person);
    }
}
