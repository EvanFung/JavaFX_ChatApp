package client.handler.impl;

import client.handler.Handler;
import common.ReturnMessage;

public class TipHdl implements Handler {
    @Override
    public Object handle(Object obj) {
        if(obj != null) {
            ReturnMessage rm = new ReturnMessage();
            if(rm.isSuccess() && rm.getContent() != null) {
                String tip = rm.getContent().toString();
                System.out.println("Tip is " + tip);
            }
        }
        return null;
    }
}
