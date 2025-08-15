package ConstructorDemo.StaticFactoryMethod;

public class UserDemo {
    public static void main(String[] args) {
        try {
            User user = User.createWithEmail("Alice", "alice@example.com");
            System.out.println("用户创建成功!" + user);

            User user1 = User.createWithSocial("Bob", SocialProvider.TWITTER,"12345");
            System.out.println("用户创建成功!"+user1);

            //无效创建实例
            User invalidUser = User.createWithEmail("A", "invalid-Email");
        } catch (IllegalArgumentException e) {
            System.err.println("创建失败!" +e.getMessage());
        }
    }
}
