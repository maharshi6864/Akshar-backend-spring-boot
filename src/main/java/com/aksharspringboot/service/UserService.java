package com.aksharspringboot.service;

import com.aksharspringboot.model.UserVo;

public interface UserService {

  void insertUser(UserVo user);

  UserVo findByUserName(String username);

  UserVo getCurrentUser();
}
