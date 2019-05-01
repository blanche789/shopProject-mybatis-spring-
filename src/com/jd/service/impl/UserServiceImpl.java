package com.jd.service.impl;

import com.jd.mapper.UserMapper;
import com.jd.pojo.UserInfoDto;
import com.jd.service.UserService;

public class UserServiceImpl implements UserService{
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserInfoDto loginService(String userName,String password) {
		UserInfoDto userInfo = userMapper.login(userName, password);
		
		return userInfo;
	}

	@Override
	public int regisService(UserInfoDto userInfo) {
		int index = userMapper.regis(userInfo);
		return index;
	}

}
