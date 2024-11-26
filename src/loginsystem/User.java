package loginsystem;

public class User {
    String userName;
    String passward;
    int score;
    boolean isLogin;
    String phoneNumber;
    String address;
    String email;

    public User( String name,String passward){
        this.passward=passward;
        userName=name;
    }

    public boolean login(String pwdIn){
        if(pwdIn.equals(passward)){
            isLogin=true;
            score+=1;
            System.out.println("登录成功！！！");
            return true;
        }else {
            System.out.println("密码错误");
            return false;
        }
    }
    public void logOut(){
        isLogin=false;
    }

    public static void main(String[] args) {
        User user1=new User("admin","admin123");
        user1.login("admin123");

    }

}
