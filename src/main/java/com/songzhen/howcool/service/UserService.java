package com.songzhen.howcool.service;

import com.baomidou.mybatisplus.service.IService;
import com.songzhen.howcool.model.UserModel;
import org.apache.ibatis.annotations.Mapper;


/**
 * Service
 *
 * @author lucas
 * @date 2018-08-27 13:51
 */
@Mapper
public interface UserService extends IService<UserModel> {
}
