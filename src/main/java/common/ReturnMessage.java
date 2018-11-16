package common;

public class ReturnMessage extends BaseMessage {

    private final int type = MessageType.RETURN;
    private boolean success;
    // success
    private String key;
    private Object content;
    // error
    private String message;
    private String code;


    public int getType() {
        return type;
    }
    public boolean isSuccess() {
        return success;
    }
    public ReturnMessage setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public String getKey() {
        return key;
    }
    public ReturnMessage setKey(String key) {
        this.key = key;
        return this;
    }
    public Object getContent() {
        return content;
    }
    public ReturnMessage setContent(Object content) {
        this.content = content;
        return this;
    }
    public String getMessage() {
        return message;
    }
    public ReturnMessage setMessage(String message) {
        this.message = message;
        return this;
    }
    public String getCode() {
        return code;
    }
    public ReturnMessage setCode(String code) {
        this.code = code;
        return this;
    }
}
