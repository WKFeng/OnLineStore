package main.com.store.dao.impl;

import main.com.store.dao.UserDao;
import main.com.store.domain.User;
import main.com.store.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements UserDao {
    @Override
    public void userRegister(User user) throws Exception {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        Object[] parameters = {
                user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
                user.getState(), user.getCode()
        };

        queryRunner.execute(sql, parameters);
    }

    @Override
    public void updateUser(User user) throws Exception {
        String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        Object[] params = {
                user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(),
                user.getCode(), user.getUid()
        };
        queryRunner.update(sql, params);
    }

    @Override
    public User activeUser(String code) throws Exception {
        String sql = "select * from user where code=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql, new BeanHandler<User>(User.class), code);
    }

    @Override
    public User userLogin(String userName, String password) throws Exception {
        String sql="select * from user where username=? and password=?";
        QueryRunner queryRunner=new QueryRunner(JDBCUtil.getDataSource());
        return queryRunner.query(sql,new BeanHandler<User>(User.class),userName,password);
    }


}
