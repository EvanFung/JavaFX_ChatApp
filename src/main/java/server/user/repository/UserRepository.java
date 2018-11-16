package server.user.repository;

import server.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private static Map<Long, User> db = new HashMap<Long,User>();

    public User get(long id) {
        return db.get(id);
    }

    public List<User> findAll() {
        return null;
    }

    public void save(User usr) {
        db.put(usr.getId(), usr);
    }
}
