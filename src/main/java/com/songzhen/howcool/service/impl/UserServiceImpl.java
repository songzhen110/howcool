package com.songzhen.howcool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.songzhen.howcool.dao.UserDao;
import com.songzhen.howcool.model.UserModel;
import com.songzhen.howcool.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Service
 *
 * @author lucas
 * @date 2018-08-27 13:51
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserModel> implements UserService {
}
