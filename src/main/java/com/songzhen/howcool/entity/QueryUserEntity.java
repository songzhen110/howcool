package com.songzhen.howcool.entity;

import com.songzhen.howcool.model.vo.CurrentUser;
import org.springframework.web.bind.annotation.RequestAttribute;

/**
 * 查询分页用户请求对象.
 * ＜p＞查询分页用户请求对象＜br＞
 * show 类的详细说明第一行
 *
 * @author Lucas
 * @date 2019/3/22
 */
public class QueryUserEntity {

    /**
     * token解析出的通用参数
     */
    private CurrentUser currentUser;

    private String mobile;
    private int pageNum;
    private int pageSize;

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
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
                "currentUser=" + currentUser +
                ", mobile='" + mobile + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
