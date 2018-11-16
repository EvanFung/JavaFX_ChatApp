package common;

public class RegisterMessage extends BaseMessage {


    private final int type = MessageType.REGISTER;
    private String username;
    private String password;
    private String confirm;

    public String getUsername() {
        return username;
    }
    public RegisterMessage setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public RegisterMessage setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getConfirm() {
        return confirm;
    }
    public RegisterMessage setConfirm(String confirm) {
        this.confirm = confirm;
        return this;
    }
    public int getType() {
        return type;
    }
}
