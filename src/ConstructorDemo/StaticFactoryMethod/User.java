package ConstructorDemo.StaticFactoryMethod;

import java.util.regex.Pattern;

public class User {//静态工厂模式的常见使用场景
    private final String name;
    private final String email;
    private final String userId;

    //私有构造器- 强制使用静态工厂方法
    private User(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }
    /*
      创建用户的基本静态工厂方法
      @param name 用户名（非空，2-50字符）
     * @param email 有效邮箱地址
     * @return 用户实例
     * @throws IllegalArgumentException 如果参数无效
     */

    //高级用法--多种创建方式
    //邮箱创建用户
    public static User createWithEmail(String name, String email) {
        validateName(name);
        validateEmail(email);
        return new User(name, email, generateUserId());//返回一个用户对象
    }

    //社交媒体创建
    public static User createWithSocial(String name, SocialProvider provider, String socialId) {
        validateName(name);
        if (socialId == null || socialId.trim().isEmpty()) {
            throw new IllegalArgumentException("社交媒体账号不得为空");
        }
        String email = name.toLowerCase() + "@" + provider.getDomain();
        return new User(name, email, "SOCIAL-" + provider + "-" + socialId);//返回一个用户对象
    }

    // 验证用户名
    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("名字长度需要在2-50个字符之间");
        }
    }

    // 验证邮箱格式
    private static void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null || !pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("无效的邮箱格式");
        }
    }

    //生成唯一的用户Id
    private static String generateUserId() {
        return "USER-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 1000);
    }

    // 其他方法...
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {//规范:重写toString方法
        return "User[name=" + name + ", email=" + email + ", id=" + userId + "]";
    }
}
