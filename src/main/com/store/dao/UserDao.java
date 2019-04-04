package main.com.store.dao;

import main.com.store.domain.User;

public interface UserDao {
    void userRegister(User user) throws Exception;

    void updateUser(User user) throws Exception;

    User activeUser(String code) throws Exception;
}
