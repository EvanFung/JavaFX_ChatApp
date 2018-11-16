package common;

public class GroupMessage extends BaseMessage {
    private final int type = MessageType.GROUP;

    private String groupName;
    private String creator;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

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
