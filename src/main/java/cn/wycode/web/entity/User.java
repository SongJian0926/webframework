package cn.wycode.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 账户
 * Created by wy on 2015/11/5.
 */
@Entity
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String mobile;
    @Column(unique = true)
    private String username;
    private int gender;
    private String userIcon;
    private int age;
    private String birthday;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    private List<Role> roles; //多对多关系，一个用户只能有多角色，一个角色被多个用户共享

    public User(String mobile, String username, int gender, String userIcon, int age, String birthday, String email, String password) {
        this.mobile = mobile;
        this.username = username;
        this.gender = gender;
        this.userIcon = userIcon;
        this.age = age;
        this.birthday = birthday;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", mobile='" + mobile + '\'' +
                ", userName='" + username + '\'' +
                ", gender=" + gender +
                ", userIcon='" + userIcon + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
