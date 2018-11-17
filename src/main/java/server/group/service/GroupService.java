package server.group.service;

import server.SocketWrapper;
import server.group.entity.Group;
import util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GroupService {
    /**
     * <group name, Group object>
     */
    private static Map<String, Group> db = new HashMap<>();
    private static ConcurrentMap<String, ArrayList> rooms = new ConcurrentHashMap<>();

    public boolean createGroup(String name,String password,String creator) {
        if(StringUtil.isEmpty(name) && StringUtil.isEmpty(password))  {
            return false;//empty input
        }

        if(db.containsKey(name)) {
            return false;//exist
        }

        Group group = new Group();
        group.setGroupName(name);
        group.setPassword(password);
        group.setCreator(creator);
        db.put(name, group);
        return true;
    }

    public static ConcurrentMap<String, ArrayList> getRooms() {
        return rooms;
    }

    public boolean putRoom(String key, ArrayList<SocketWrapper> room) {
        if(!db.containsKey(key)) {
            return false;
        }

        synchronized (rooms) {
            if (!rooms.containsKey(key)) {
                rooms.put(key, room);
                return true;
            } else {
                return false;
            }
        }
    }


    public static boolean enterRoom(String key, String password, SocketWrapper wrapper) {
        if(!db.get(key).getPassword().equals(password)) {
            return false;
        }

        if (rooms.containsKey(key)) {
            ArrayList<SocketWrapper> room = rooms.get(key);
            for (int i = 0; i < room.size(); i++) {
                if (room.get(i).getSocket().equals(wrapper.getSocket())) {
                    System.out.println("this client was already in the room");
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

    public static Set<String> keySetOfRoom() {
        return rooms.keySet();
    }


    public static void leaveRoom(String key, SocketWrapper wrapper) {
        if (rooms.containsKey(key)) {
            ArrayList<SocketWrapper> room = rooms.get(key);
            synchronized (room) {
                room.remove(wrapper);
            }
        }
    }

    public static ArrayList<SocketWrapper> getRoom(String key) {
        return rooms.get(key);
    }

}
