package com.jd.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.jd.pojo.UserInfoDto;

public interface UserMapper {
	@Select("select * from user_info where user_name=#{0} and password=#{1}")
	UserInfoDto login(String userName,String password);
	
	@Insert("insert into user_info values(default,#{userName},#{password},#{telephone})")
	int regis(UserInfoDto userInfo);
}
