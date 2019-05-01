package com.jd;

import com.jd.service.UserService;

public class test {
	private static UserService userService;
	
	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = ac.getBean("userServiceImpl",UserServiceImpl.class);
//		UserInfoDto userInfo = userService.loginService("уехЩ", "mx123123");
//		System.out.println(userInfo);
		
		byte[] b = {1,2,3};
		byte[] c = new byte[12];
		System.arraycopy(b, 0, c, 0, 3);
		for (byte i : c) {
			System.out.println(i);
		}
	}
}
