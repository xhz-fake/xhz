package Reflection;

import java.lang.reflect.Constructor;

public class Demo03_GetConstructor {
    public static void main(String[] args) {
        //获取Class对象
        Class<?>aClass = Person.class;
        //获取该类所有 public 的构造
        Constructor<?>[] constructors = aClass.getConstructors();
        for(Constructor<?> constructor :constructors){
            System.out.println(constructor);
        }
    }
}
