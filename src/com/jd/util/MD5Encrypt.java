package com.jd.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MD5Encrypt {
	
	public static byte[] encryptByMD5(String password) {
		
		MessageDigest md = null;
		byte[] randomBytes = new byte[12];
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(randomBytes);
		System.out.println(Arrays.toString(randomBytes));
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		md.update(randomBytes);
		md.update(password.getBytes());
		byte[] passwordBytes = md.digest();
		System.out.println(Arrays.toString(passwordBytes));
		byte[] resultPassword = new byte[randomBytes.length + passwordBytes.length];
		System.arraycopy(randomBytes, 0, resultPassword, 0, randomBytes.length);
		System.arraycopy(passwordBytes, 0, resultPassword, randomBytes.length, passwordBytes.length);
		return resultPassword;
		
	}
	
	public static boolean  validatePassword(String password,byte[] resultPassword) {
	      MessageDigest md = null;
	      byte[] randomBytes = new byte[12];
	      System.arraycopy(resultPassword, 0, randomBytes, 0, randomBytes.length);  //从最终数据库中保存密码获取随机值
	      System.out.println("随机数=" + Arrays.toString(randomBytes));

	      try {
	          md = MessageDigest.getInstance("MD5");  //创建MessageDigest加密对象
	      } catch (NoSuchAlgorithmException e) {
	          e.printStackTrace();
	      }

	      md.update(randomBytes);
	      md.update(password.getBytes());  //调用MessageDigest对象的update（）方法对随机值及密码进行加密
	      byte[] passwordBytes = md.digest();
	      System.out.println("校验密码=" + Arrays.toString(passwordBytes));

	      byte[] passwordInDB = new byte[resultPassword.length - randomBytes.length];
	      System.arraycopy(resultPassword, randomBytes.length, passwordInDB, 0, passwordInDB.length);  //从最终数据库中保存密码获取加密之后的密码
	      System.out.println("数据库校验密码=" + Arrays.toString(passwordInDB));
	      if(Arrays.equals(passwordBytes, passwordInDB)) {  //两个密码进行比较
	          return true;
	      }else
	          return false;
	  }
	
	public static void main(String[] args) {
		
		byte[] bytes = MD5Encrypt.encryptByMD5("123");
		MD5Encrypt.validatePassword("123", bytes);
//		System.out.println(Arrays.toString(bytes));
		
	}
	
}
