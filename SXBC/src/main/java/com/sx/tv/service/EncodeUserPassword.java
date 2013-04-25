package com.sx.tv.service;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncodeUserPassword {

	public static String encrypt(String username, String passwd) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(passwd, username);
	}

}
