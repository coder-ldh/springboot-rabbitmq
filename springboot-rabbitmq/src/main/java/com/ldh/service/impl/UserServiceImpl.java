package com.ldh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldh.mapper.UserMapper;
import com.ldh.model.User;
import com.ldh.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByKey(Integer userId) {
		return userMapper.deleteByKey(userId);
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User selectByKey(Integer userId) {
		return userMapper.selectByKey(userId);
	}

	@Override
	public int updateByKey(User user) {
		return userMapper.updateByKey(user);
	}
}