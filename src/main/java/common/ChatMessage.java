package common;

public class ChatMessage extends BaseMessage {
    private final int type = MessageType.CHAT;
    private String roomName;
    private String content;
    private String timer;
    private String chatType;
    private FileMessage fileMessage;
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

    public String getChatType() {
        return chatType;
    }

    public FileMessage getFileMessage() {
        return fileMessage;
    }

    public void setFileMessage(FileMessage fileMessage) {
        this.fileMessage = fileMessage;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
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
