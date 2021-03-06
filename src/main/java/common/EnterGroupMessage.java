package common;

public class EnterGroupMessage extends BaseMessage {
    private final int type = MessageType.ENTERGROUP;
    private String groupName;
    private String password;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    @Override
    public int getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
