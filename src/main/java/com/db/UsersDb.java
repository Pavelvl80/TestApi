package com.db;

import com.model.User;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edvard Piri on 14.05.2017.
 */
public final class UsersDb {
    private static Set<User> usersDb = new HashSet<User>();


    public static Set<User> getAll() {
        return usersDb;
    }

    public static User get(int id) {
        for (User user : usersDb) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    public static User save(User newUser) {

        if (newUser == null)
            return null;
        int id = newUser.getId();

        for (User user : usersDb) {
            if (user.getId() == id) {
                usersDb.remove(user);
                usersDb.add(newUser);
                return newUser;
            }
        }
        usersDb.add(newUser);
        return newUser;
    }

    public static User delete(int id) {

        for (User user : usersDb) {
            if (user.getId() == id) {
                usersDb.remove(user);
                return user;
            }
        }
        return null;
    }
}
