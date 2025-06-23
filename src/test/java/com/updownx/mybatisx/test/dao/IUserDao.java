package com.updownx.mybatisx.test.dao;

import com.updownx.mybatisx.test.po.User;

public interface IUserDao {

  User queryUserInfoById(Long uId);
}
