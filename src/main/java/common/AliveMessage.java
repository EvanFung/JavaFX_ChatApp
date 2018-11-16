package common;

public class AliveMessage extends BaseMessage {
    private final int type = MessageType.ALIVE;

    public int getType() {
        return type;
    }
}
