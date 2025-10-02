package Reflection;

import java.lang.reflect.Constructor;

public class Demo07_GetConstructor {// 返回所有类型的构造方法
    public static void main(String[] args) {
        Class<Person> aClass = Person.class;
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }
}
