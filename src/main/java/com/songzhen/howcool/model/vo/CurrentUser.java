package com.songzhen.howcool.model.vo;

/**
 * 当前用户对象.
 * ＜p＞当前用户对象＜br＞
 * 当前用户对象
 *
 * @author Lucas
 * @date 2019/3/18
 */
public class CurrentUser {

    private String uid;
    private String userName;
    private String realName;
    private String deviceId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
