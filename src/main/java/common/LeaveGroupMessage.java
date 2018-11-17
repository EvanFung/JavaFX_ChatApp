package common;

public class LeaveGroupMessage extends BaseMessage {
    private final int type = MessageType.LEAVEGROUP;

    private String groupName;
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
