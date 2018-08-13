package com.ldh.service;

import com.ldh.model.User;

public interface UserService {

	 int deleteByKey(Integer userId);

	 int insert(User user);

	 User selectByKey(Integer userId);

	 int updateByKey(User user);
}
