package com.songzhen.howcool.controller;

import com.songzhen.howcool.entity.QueryUserEntity;
import com.songzhen.howcool.entity.UserLoginEntity;
import com.songzhen.howcool.auth.NeedLogin;
import com.songzhen.howcool.biz.UserBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户相关.
 * ＜p＞用户相关＜br＞
 * 用户相关
 *
 * @author Lucas
 * @date 2018/8/9
 */
@RestController
@RequestMapping("**/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserBizService userBizService;

    /**
     * 登录
     */
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody UserLoginEntity userLoginEntity) {
        logger.info("login input params userLoginEntity={}", userLoginEntity);
        return userBizService.login(userLoginEntity.getUserName(), userLoginEntity.getPassword(), userLoginEntity.getDeviceId());
    }


    /**
     * 登出
     *
     * @param request request
     */
    @NeedLogin
    @PostMapping("logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String,Object> currentUser = (Map<String,Object>)request.getAttribute("currentUser");

        return userBizService.logout(currentUser.get("uid").toString());
    }

    /**
     * 列表用户
     */
    @NeedLogin
    @PostMapping("pageUser")
    public Map<String, Object> pageUser(@RequestBody QueryUserEntity queryUserEntity) {
        logger.info("login input params queryUserEntity={}", queryUserEntity);
        return userBizService.pageUsers(queryUserEntity);
    }

}
