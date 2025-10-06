package Reflection.reflectPra;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Demo01_Reflect {//自动解析配置文件,根据方法名调用方法
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1.创建properties集合
        Properties properties = new Properties();

        //2.读取配置文件,解析配置文件
        InputStream in = Demo01_Reflect.class.getClassLoader().getResourceAsStream("pro.properties");
        properties.load(in);
        System.out.println(properties);

        //3. 根据解析出来的className,创建class对象
        String className = properties.getProperty("className");
        Class<?> aClass = Class.forName(className);

        //4. 根据解析出来的methodName,获取对应的的方法
        String methodName = properties.getProperty("methodName");
        Object o = aClass.newInstance();

        Method method = aClass.getMethod(methodName);

        //5. 执行方法
        method.invoke(o);
    }
}
