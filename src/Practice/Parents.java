package Practice;

public class Parents {
    private static final Parents Child = new Parents();//创建全局唯一实例

    private Parents(){// 私有构造器
    }

    public static Parents getInstance(){
        return Child;//返回唯一的实例
    }
}
