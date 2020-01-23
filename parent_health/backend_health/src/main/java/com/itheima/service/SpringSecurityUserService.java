package com.itheima.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;

public class SpringSecurityUserService implements UserDetailsService{

	@Reference
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userService.findByUsername(username);
		if(user==null){
			return null;
		}
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority(role.getKeyword()));
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				list.add(new SimpleGrantedAuthority(permission.getKeyword()));
			}
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
	}
}
