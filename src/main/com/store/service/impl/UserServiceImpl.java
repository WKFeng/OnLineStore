package main.com.store.service.impl;

import main.com.store.dao.UserDao;
import main.com.store.dao.impl.UserDaoImpl;
import main.com.store.domain.User;
import main.com.store.exception.DataWrongException;
import main.com.store.service.UserService;
import main.com.store.utils.MailUtils;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) throws Exception {
        userDao.userRegister(user);
        MailUtils.sendSimpleMail(user.getEmail(), user.getCode());
    }

    @Override
    public void activeUser(String activeCode) throws Exception {
        User user = userDao.activeUser(activeCode);
        if (user == null) {
            throw new DataWrongException("用户不存在或用户已激活");
        } else {
            user.setState(1);
            user.setCode(null);
            updateUser(user);
        }
    }

    @Override
    public void updateUser(User user) throws Exception {

        userDao.updateUser(user);
    }

}
