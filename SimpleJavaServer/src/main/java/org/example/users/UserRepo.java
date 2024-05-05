package org.example.users;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    private final List<User> db = new ArrayList<>();

    public User getById(Long id) {
        return db.stream()
                .filter(user -> user.id().equals(id))
                .findAny()
                .orElse(null);
    }

    public void save(User user) {
        db.add(user);
    }

}
