package server.user.service;

import server.user.entity.User;
import util.MD5Util;
import util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    //TODO database implementation later
    private static Map<String, User> db = new HashMap<String,User>();

    public User register(String username, String password) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return null;
        }

        if(db.containsKey(username)) {
            return null;

        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Util.getMD5Code(password));
        db.put(username,user);
        return user;
    }

    public User login(String username, String password) {
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return null;
        }
        if (db.containsKey(username)) {
            User usr = db.get(username);
            if (MD5Util.getMD5Code(password).equals(usr.getPassword())) {
                return usr;
            }
        }
        return null;
    }
}
