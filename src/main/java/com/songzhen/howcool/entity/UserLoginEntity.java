package com.songzhen.howcool.entity;

/**
 * 用户登录请求对象.
 * ＜p＞用户登录请求对象＜br＞
 * 用户登录请求对象
 *
 * @author Lucas
 * @date 2019/3/19
 */
public class UserLoginEntity {
    private String userName;
    private String password;
    private String deviceId;
    private String captchaId;
    private String captchaCode;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", captchaId='" + captchaId + '\'' +
                ", captchaCode='" + captchaCode + '\'' +
                '}';
    }
}
