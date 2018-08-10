package com.ldh.service;

import com.ldh.model.User;

public interface UserService {

	public int deleteByKey(Integer userId);

	public int insert(User user);

	public User selectByKey(Integer userId);

	public int updateByKey(User user);
}
