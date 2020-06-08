package jar.bean;

public class UserBean {
    private int id;
    private String username;
    private String password;

    public UserBean() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toJson() {
        String str = "{\"id\":" + id + ",\"username\":\"" + username + "\"}";
        return str;
    }
}
