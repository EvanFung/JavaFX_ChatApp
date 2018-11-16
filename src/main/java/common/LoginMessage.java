package common;

public class LoginMessage extends BaseMessage {
    private final int type = MessageType.LOGIN;
    private String username;
    private String password;

    public int getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public LoginMessage setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginMessage setPassword(String password) {
        this.password = password;
        return this;
    }
}
