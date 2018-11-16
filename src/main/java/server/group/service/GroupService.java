package server.group.service;

import server.SocketWrapper;
import server.group.entity.Group;
import util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GroupService {
    /**
     * <group name, Group object>
     */
    private static Map<String, Group> db = new HashMap<>();
    private static ConcurrentMap<String, ArrayList> rooms = new ConcurrentHashMap<>();

    public Group createGroup(String name,String password) {
        if(StringUtil.isEmpty(name) && StringUtil.isNotEmpty(password))  {
            return null;
        }

        if(db.containsKey(name)) {
            return null;//exist
        }

        Group group = new Group();
        group.setGroupName(name);
        group.setPassword(password);
        db.put(name, group);
        return group;
    }



    public boolean putRoom(String key,String password, ArrayList<SocketWrapper> room) {
        if(db.containsKey(key)) {
            return false;
        }

        if(!db.get(key).getPassword().equals(password)) {
            return false;
        }
        synchronized (rooms) {
            if (!rooms.containsKey(key)) {
                rooms.put(key, room);
                System.out.println("room was created");
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


    public static void leaveRoom(String key, SocketWrapper wrapper) {
        if (rooms.containsKey(key)) {
            ArrayList<SocketWrapper> room = rooms.get(key);
            synchronized (room) {
                room.remove(wrapper);
            }
        }
    }




}
