package Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo09_GetMethod {//获取所有成员方法

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //method01();
        //method02();
        //method03();
        method04();
    }

    private static void method01() {
        Class<Person> aClass = Person.class;
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    private static void method02() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<Person> aClass = Person.class;

        // 创建对象
        Person person = aClass.newInstance();

        Method setName = aClass.getMethod("setName", String.class);
        System.out.println(setName);

        setName.invoke(person, "鸽哥");//相当于person.setName("鸽哥");
        System.out.println(person);

        Method getName = aClass.getMethod("getName");
        Object o = getName.invoke(person);//  相当于person.getName();
        System.out.println(o);
    }

    private static void method03() {
        Class<Person> aClass = Person.class;
        Method[] dm = aClass.getDeclaredMethods();// 获取所有方法
        for (Method method : dm) {
            System.out.println(method);
        }
    }

    private static void method04() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {// 反射指定的成员方法:包括private的
        Class<Person> aClass = Person.class;
        Person person = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("eat");
        method.setAccessible(true);
        method.invoke(person);


    }

}
