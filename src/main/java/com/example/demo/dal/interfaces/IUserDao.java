package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Admin.User;

public interface IUserDao {
    int createUser() throws DaoException;
    User getUser(int userID) throws DaoException;
}
