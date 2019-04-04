package main.com.store.service;

import main.com.store.domain.User;

public interface UserService {
    void registerUser(User user) throws Exception;

    void activeUser(String activeCode) throws Exception;

    void updateUser(User user) throws Exception;

}
