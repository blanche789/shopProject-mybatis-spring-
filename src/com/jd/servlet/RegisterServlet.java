package com.jd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jd.pojo.UserInfoDto;
import com.jd.service.UserService;
import com.jd.service.impl.UserServiceImpl;
import com.jd.util.MD5Encrypt;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/regis")
public class RegisterServlet extends HttpServlet {
	private UserService userService;

    @Override
    public void init() throws ServletException {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	UserService userService = ac.getBean("userServiceImpl",UserServiceImpl.class);
    }
    

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String telephone = req.getParameter("telephone");
		String validateCode = req.getParameter("validateCode");
		UserInfoDto userInfo = new UserInfoDto();
		if(userService.loginService(userName, password) != null) {
			System.out.println("用户名已存在");
			req.getRequestDispatcher("/shop/alreadyRegister.jsp").forward(req, resp);
		}else {
		System.out.println("用户信息成功保存到数据库" );
		userInfo.setUserName(userName);
		userInfo.setPassword(MD5Encrypt.encryptByMD5(password));
		userInfo.setTelephone(telephone);
		userService.regisService(userInfo);
		req.getRequestDispatcher("/shop/RegisterSuccess.jsp").forward(req, resp);
		}
	}
}


