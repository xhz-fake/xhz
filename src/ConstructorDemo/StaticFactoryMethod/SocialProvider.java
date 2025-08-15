package ConstructorDemo.StaticFactoryMethod;

public enum SocialProvider {//社交媒体提供商的枚举
    GOOGLE("google.com"),
    FACEBOOK("facebook.com"),
    TWITTER("twitter.com");

    private String domain;

    SocialProvider(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
