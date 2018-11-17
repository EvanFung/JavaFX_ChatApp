package common;

public class FileMessage extends BaseMessage {
    private final int type = MessageType.FILE;
    private String name;
    private long size;
    private String ext;
    private String timer;
    boolean isUpload = true;

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public FileMessage setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExt() {
        return ext;
    }

    public FileMessage setExt(String ext) {
        this.ext = ext;
        return this;
    }
}
