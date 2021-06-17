package com.example.demo.service.implementations;

import com.example.demo.dal.implementations.UserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.User;
import com.example.demo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserDao userdao;

    public UserService(UserDao userdao) {
        this.userdao = userdao;
    }

    @Override
    public int createUser() throws ServiceException, DaoException{
        return userdao.createUser();
    }

    @Override
    public User getUser(int userID) throws ServiceException, DaoException {
        return userdao.getUser(userID);
    }

}
