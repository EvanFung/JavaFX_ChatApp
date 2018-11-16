package common;

public class EnterGroupMessage extends BaseMessage {
    private final int type = MessageType.ENTERGROUP;
    private String groupName;

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
}
