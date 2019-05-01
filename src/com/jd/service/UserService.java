package com.jd.service;

import com.jd.pojo.UserInfoDto;

public interface UserService {
	UserInfoDto loginService(String userName,String password);
	
	int regisService(UserInfoDto userInfo);

}
