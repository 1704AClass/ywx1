package com.itheima.mapper;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Permission;

public interface PermissionMapper {
	@Select(value = "select  p.* from t_permission p ,t_role_permission rp where p.id = rp.permission_id and rp.role_id = #{roleId}")
	Set<Permission> findByRoleId(Integer roleId);


}
