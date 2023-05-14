package com.ylb.mybatise.test.dao;

import com.ylb.mybatise.test.po.User;

public interface  IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

    String queryUserInfoById(String uId);
}
