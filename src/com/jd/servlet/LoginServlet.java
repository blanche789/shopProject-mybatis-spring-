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
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private UserService userService;
	@Override
	public void init() throws ServletException {
//		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = ac.getBean("userServiceImpl",UserServiceImpl.class);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		UserInfoDto userInfo = userService.loginService(userName, password);
		if(userInfo != null) {
			if(MD5Encrypt.validatePassword(password, userInfo.getPassword())) {
				req.getRequestDispatcher("/LoginSuccess.jsp").forward(req, resp);
			}else {
				System.out.println("√‹¬Î¥ÌŒÛ");
				resp.sendRedirect("/shop/login.html?flag=false");
			}
			
		}else {
				System.out.println("”√ªß√˚¥ÌŒÛ");
			resp.sendRedirect("/shop/login.html?flag=false");
//				resp.sendRedirect("/shop/index.jsp");
		}
	 
	}
}
