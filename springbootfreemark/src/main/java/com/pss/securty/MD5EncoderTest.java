package com.pss.securty;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class MD5EncoderTest {
	
	private static final String newPassword = "_pengs1";
	
	private static final String CHARSET_NAME = "UTF-8";
	
	private static final String oldpasswd = "gcjiD52RmolovLWTYz3SqQ==";
	
	public static void main(String[] args) {
		try {
			System.out.println(checkpassword(newPassword, oldpasswd));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String EncoderByMd5(String password) {
		String stringByMD5 = StringUtils.EMPTY;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			Base64Encoder base64en = new Base64Encoder();
			stringByMD5 = base64en.encode(messageDigest.digest(password.getBytes(CHARSET_NAME)));
			return stringByMD5;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return stringByMD5;
	}
	
	
	public static boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return EncoderByMd5(newpasswd).equals(oldpasswd);
	}
	
}
