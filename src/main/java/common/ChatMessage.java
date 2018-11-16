package common;

public class ChatMessage extends BaseMessage {
    private final int type = MessageType.CHAT;
    private String roomName;
    private String content;
    private String timer;
    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public ChatMessage setContent(String content) {
        this.content = content;
        return this;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }
}
