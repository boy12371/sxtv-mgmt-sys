package com.sx.tv.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sx.tv.entites.Role;

@SuppressWarnings("deprecation")
public class MyUserDetailService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(MyUserDetailService.class);  

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TypedQuery<com.sx.tv.entites.User> _u = com.sx.tv.entites.User.findUsersByNameEquals(username);
		com.sx.tv.entites.User _user = null;
		try {
			_user = _u.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(null == _user){
			String message = "用户"+username+"不存在";  
            throw new UsernameNotFoundException(message);  
		}
		
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		List<Role> roles = _user.getRoles();
		for (Role role : roles) {
			GrantedAuthority ga = new GrantedAuthorityImpl(role.getName());
			auths.add(ga);
			logger.debug("用户：["+_user.getName()+"]拥有角色：["+role.getName()+"],即spring security中的access");  
		}
		User user = new org.springframework.security.core.userdetails.User(_user.getName(),_user.getPassword(),true,true,true,true,auths);
		
		return user;
	}
}
