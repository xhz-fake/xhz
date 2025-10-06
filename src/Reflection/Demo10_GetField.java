package Reflection;

import java.lang.reflect.Field;

public class Demo10_GetField {
    public static void main(String[] args) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        //method01();
        //method02();
        //method03();
        method04();
    }

    private static void method01(){
        Class<Student> aClass = Student.class;
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    private static void method02(){
        Class<Student> aClass = Student.class;
        Field[] fields = aClass.getDeclaredFields();//所有Field
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    private static void method03() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class<Student> aClass =Student.class;
        Field age  = aClass.getField("age");
        System.out.println(age);
        Student student  = aClass.newInstance();

        //调用set方法为属性赋值
        age.set(student,18);
        // 调用get方法获取属性值
        Object o = age.get(student);
        System.out.println(o);
    }

    private static void method04() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class<Student> aClass= Student.class;
        Field name = aClass.getDeclaredField("name");

        //对于私有的属性,赋值时先暴力反射
        name.setAccessible(true);//解除私有权限

        Student student = aClass.newInstance();
        name.set(student,"鸽哥");
        Object o  = name.get(student);
        System.out.println(o);
    }
}
