package Reflection;

import java.io.FileInputStream;
import java.util.Properties;

public class Demo02_GetClass {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src\\pro.properties");
        properties.load(in);

        String className = properties.getProperty("className");
        System.out.println("className=" + className);

        Class<?> aClass = Class.forName(className);
        System.out.println("aClass = "+ aClass);
    }
}
