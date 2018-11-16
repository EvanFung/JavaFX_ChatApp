package common;

public class BaseMessage {

    protected String from;
    protected String to;
    protected String owner;
    protected int type;

    public String getFrom() {
        return from;
    }
    public BaseMessage setFrom(String from) {
        this.from = from;  // which tab will be select
        return this;
    }
    public String getTo() {
        return to;
    }
    public BaseMessage setTo(String to) {
        this.to = to;
        return this;
    }
    public String getOwner() {
        return owner;
    }
    public BaseMessage setOwner(String owner) {
        this.owner = owner; // display
        return this;
    }
    public int getType() {
        return type;
    }
    public BaseMessage setType(int type) {
        this.type = type;
        return this;
    }

}
