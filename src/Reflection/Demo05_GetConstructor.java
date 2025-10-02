package Reflection;

public class Demo05_GetConstructor {//利用空参构造创建对的快捷方式
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<Person> aClass = Person.class;
        Person person = aClass.newInstance();
        System.out.println(person);
    }
}
