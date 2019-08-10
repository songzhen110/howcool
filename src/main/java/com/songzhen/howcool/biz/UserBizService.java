package com.songzhen.howcool.biz;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.songzhen.howcool.constants.Application;
import com.songzhen.howcool.entity.QueryUserEntity;
import com.songzhen.howcool.model.UserModel;
import com.songzhen.howcool.model.vo.CurrentUser;
import com.songzhen.howcool.service.UserService;
import com.songzhen.howcool.util.JwtUtil;
import com.songzhen.howcool.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 用户相关.
 *
 * 用户注册、登录、登出、用户分页
 *
 * @author Lucas
 * @date 2019/3/16
 */
@Service
public class UserBizService {

    private static final Logger logger = LoggerFactory.getLogger(UserBizService.class);

    private static final String MOBILE_REGEX = "^[1]([3-9])[0-9]{9}$";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 注册账号.
     * ＜p＞s注册账号＜br＞
     * s注册账号
     *
     * @return Map<String   ,   Object>
     * @author Lucas
     * @date 2019-03-18
     */
    public Map<String, Object> addUser() {

        String uId = Application.USER_ID_PREFIX + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);

        if (!checkIsExistUId(uId)) {
            UserModel model = new UserModel();
            model.setUId(uId);
            model.setUserName("zhangsan001");
            model.setPassword(Md5Util.encodeMD5(Application.USER_DEFAULT_PASSWORD));
            model.setStatus((byte) 1);
            model.setMobile("17211111112");
            model.setEmail("xiaoling@126.com");
            model.setCreateBy("admin");
            model.setUpdateBy("admin");
            userService.insert(model);
        }

        return Maps.newHashMap();
    }

    /**
     * 检查用户ID是否已存在
     *
     * @param uId uId
     * @return true：存在 false :不存在
     * @author Lucas
     * @date 2019-03-17
     */
    private boolean checkIsExistUId(String uId) {
        if (logger.isDebugEnabled()) {
            logger.debug("uId = {}", uId);
        }
        return userService.selectCount(new EntityWrapper<UserModel>().eq("u_id", "HC20190317192731")) != 0;
    }

    public Map<String, Object> login(String userName, String password, String deviceId) {
        logger.info("login input params userName={}, deviceId={}", userName, deviceId);

        // 存放返回数据
        HashMap<String, Object> retMap = Maps.newHashMap();

        EntityWrapper<UserModel> userModelWrapper = new EntityWrapper<>();
        userModelWrapper.setSqlSelect("u_id", "user_name", "password", "mobile", "email");
        userModelWrapper.eq("is_delete", 0).eq("status", 1);
        if(Pattern.matches(MOBILE_REGEX,userName)){
            userModelWrapper.eq("mobile", userName);
        }else{
            userModelWrapper.eq("user_name", userName);
        }

        UserModel userModel = userService.selectOne(userModelWrapper);

        if (null == userModel) {
            retMap.put("code", "01");
            retMap.put("msg", "用户或密码错误");
            return retMap;
        }

        if (!password.equals(userModel.getPassword())) {
            retMap.put("code", "01");
            retMap.put("msg", "用户或密码错误");
            return retMap;
        }

        // 生成TOKEN并保存到REDIS
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUid(userModel.getUId());
        currentUser.setUserName(userModel.getMobile());
        currentUser.setRealName(userModel.getEmail());
        currentUser.setDeviceId(deviceId);
        String token = this.saveTokenToRedis(currentUser);

        retMap.put("code", "00");
        retMap.put("msg", "登陆成功");
        retMap.put("token", token);
        retMap.put("currentUser", currentUser);

        return retMap;
    }

    public Map<String, Object> logout(String uid) {
        logger.info("logout input params uid={}", uid);
        // 存放返回数据
        HashMap<String, Object> retMap = Maps.newHashMap();

        if (StringUtils.isEmpty(uid)) {
            retMap.put("code", "01");
            retMap.put("msg", "登出失败");
            return retMap;
        }

        // 删除REDIS中的TOKEN
        redisTemplate.delete(uid);

        retMap.put("code", "00");
        retMap.put("msg", "登出成功");

        return retMap;
    }

    public Map<String, Object> pageUsers(QueryUserEntity queryUserEntity) {
        logger.info("logout input params queryUserEntity={}", queryUserEntity);
        // 存放返回数据
        HashMap<String, Object> retMap = Maps.newHashMap();

        String realName = queryUserEntity.getRealName();
        String mobile = queryUserEntity.getMobile();
        int pageNum = queryUserEntity.getPageNum()<=0?1:queryUserEntity.getPageNum();
        int pageSize = queryUserEntity.getPageSize()<=0?10:queryUserEntity.getPageSize();

        Page<UserModel> page = new Page<>(pageNum, pageSize);
        EntityWrapper<UserModel> userEW = new EntityWrapper<>();
        userEW.setSqlSelect("id","u_id","user_name","mobile","email","status","create_time");
        userEW.eq(!StringUtils.isEmpty(realName),"user_name", realName).eq(!StringUtils.isEmpty(mobile),"mobile",mobile);
        Page<UserModel> userPage = userService.selectPage(page, userEW);

        List<UserModel> records = userPage.getRecords();
        retMap.put("current", pageNum);
        retMap.put("pages", userPage.getPages());
        retMap.put("size", userPage.getSize());
        retMap.put("total", userPage.getTotal());
        retMap.put("records", records);

        return retMap;
    }

    /**
     * 生成并保存TOKEN
     *
     * @param currentUser currentUser
     *                      NOTE:REDIS KEY EXPIRE 30 MINUTES
     *                      TOKEN INTERNAL STORAGE EXPIRATION TIME IS 3 HOURS AFTER SYSTEM TIME DELAY
     */
    private String saveTokenToRedis(CurrentUser currentUser) {
        String jwt = Application.PREFIX_TOKEN + JwtUtil.createJWT("howcool", currentUser, "", System.currentTimeMillis() + 3 * 60 * 60 * 1000);
        redisTemplate.opsForValue().set(currentUser.getUid(), jwt, 30, TimeUnit.MINUTES);
        return jwt;
    }

}
