package FileControl;

public class User {
    String username;
    String password;
    String filePath;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(){
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
