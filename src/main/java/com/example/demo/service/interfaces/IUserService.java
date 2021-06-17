package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.User;

public interface IUserService {
    int createUser() throws ServiceException, DaoException;

    User getUser(int userID) throws ServiceException, DaoException;

}
