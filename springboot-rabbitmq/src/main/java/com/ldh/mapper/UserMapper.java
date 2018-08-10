package com.ldh.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ldh.model.User;
@Mapper
public interface UserMapper {
	
	public int deleteByKey(Integer userId);

	public int insert(User user);

	public User selectByKey(Integer userId);
	
	public User selectByPhone(String phone);

	public int updateByKey(User user);
}