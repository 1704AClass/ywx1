package com.itheima.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.PermissionMapper;
import com.itheima.mapper.RoleMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user=userMapper.findByUsername(username);
		if(user==null){
			return null;
		}
		Integer userId = user.getId();
		Set<Role> roles=roleMapper.findByUserId(userId);
		if(roles!=null&&roles.size()>0){
			for (Role role : roles) {
				Integer roleId = role.getId();
				Set<Permission> permissions=permissionMapper.findByRoleId(roleId);
				if(permissions!=null&&permissions.size()>0){
					role.setPermissions(permissions);
				}
			}
			user.setRoles(roles);
		}
		return user;
	}

}
