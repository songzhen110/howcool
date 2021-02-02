package com.songzhen.howcool.entity;
/**
 * 用户注册请求对象.
 * ＜p＞用户注册请求对象＜br＞
 * 用户注册请求对象
 *
 * @author Lucas
 * @date 2019/3/19
 */
public class UserRegisterEntity {
    private String userName;
    private String password;
    private String mobile;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRegisterEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
