package server;

import common.SendHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketHolder {
    /**
     * <room name, list of sockets>
     */
    public static ConcurrentMap<String, ArrayList> rooms = new ConcurrentHashMap<>();
    /**
     * <username, socket>
     */
    private static ConcurrentMap<String, SocketWrapper> listSocketWrap = new ConcurrentHashMap<String, SocketWrapper>();

    public static Set<String> keySet() {
        return listSocketWrap.keySet();
    }

    public static SocketWrapper get(String key) {
        return listSocketWrap.get(key);
    }

    public static void put(String key, SocketWrapper value) {
        listSocketWrap.put(key, value);
    }

    public static SocketWrapper remove(String key) {
        return listSocketWrap.remove(key);
    }

    public static Set<String> keySetOfRoom() {
        return rooms.keySet();
    }

    public static ArrayList<SocketWrapper> getRoom(String key) {
        return rooms.get(key);
    }

    public static boolean putRoom(String key, ArrayList<SocketWrapper> room) {
        synchronized (rooms) {
            if (!rooms.containsKey(key)) {
                rooms.put(key, room);
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean enterRoom(String key, SocketWrapper wrapper) {
        if (rooms.containsKey(key)) {
            ArrayList<SocketWrapper> room = rooms.get(key);
            for (int i = 0; i < room.size(); i++) {
                if (room.get(i).getSocket().equals(wrapper.getSocket())) {
                    return false;
                }
            }
            synchronized (room) {
                room.add(wrapper);
            }
            return true;
        }
        return false;
    }

    public static void leaveRoom(String key, SocketWrapper wrapper) {
        if (rooms.containsKey(key)) {
            ArrayList<SocketWrapper> room = rooms.get(key);
            synchronized (room) {
                room.remove(wrapper);
            }
        }
    }

    public static ArrayList<SocketWrapper> removeRoom(String key) {
        return rooms.remove(key);
    }
}
