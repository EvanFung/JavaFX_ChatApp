package server;

import common.ConstantValue;

import java.util.Date;

public class SocketSchedule implements Runnable {
    @Override
    public void run() {
        for (String key : SocketHolder.keySet()) {
            SocketWrapper wrapper = SocketHolder.get(key);
            if (wrapper != null &&  wrapper.getLastAliveTime() != null) {
                if (((new Date().getTime() - wrapper.getLastAliveTime().getTime()) / 1000) > ConstantValue.TIME_OUT) {
                    // remove socket if timeout
                    SocketHolder.remove(key);
                }
            }
        }
    }
}
