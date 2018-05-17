package com.chp.modules.mybatis.dao.mapper;

import com.chp.modules.mybatis.dao.entity.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByPrimaryKey(Integer id);

}