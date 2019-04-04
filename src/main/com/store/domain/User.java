package main.com.store.domain;

import lombok.Data;
import main.com.store.utils.UUIDUtils;

@Data
public class User {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private String birthday;
    private String sex;
    private int state;
    private String code;

    public User() {
        this.state=0;//默认是没有激活的
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", state=" + state +
                '}';
    }

    public User(String username, String password, String name, String email, String telephone, String birthday, String sex) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.birthday = birthday;
        this.sex = sex;
        this.state = 0;
    }
}
