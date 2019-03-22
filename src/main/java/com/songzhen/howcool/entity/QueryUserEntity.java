package com.songzhen.howcool.entity;

/**
 * 查询分页用户请求对象.
 * ＜p＞查询分页用户请求对象＜br＞
 * show 类的详细说明第一行
 *
 * @author Lucas
 * @date 2019/3/22
 */
public class QueryUserEntity {
    private String realName;
    private String mobile;
    private int pageNum;
    private int pageSize;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryUserEntity{" +
                "realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
