package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.model.IdGenerator;
import com.example.demo.model.Admin.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class UserDao implements IUserDao {
    static final HashMap<Integer, User> users = new HashMap<>();
    @Override
    public int createUser() throws DaoException {
        int uniqueId = IdGenerator.generateUniqueId();
        User user = new User("name", uniqueId, 0);
        users.put(user.getUserId(), user);
        return user.getUserId();

    }

    @Override
    public User getUser(int userID) throws DaoException {
        return users.get(userID);
    }
}
