package com.itheima.mapper;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;

public interface RoleMapper {
	@Select(value="select  r.* from t_role r ,t_user_role ur where r.id = ur.role_id and ur.user_id = #{userId}")
	Set<Role> findByUserId(Integer userId);


}
