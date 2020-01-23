package com.itheima.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.User;

public interface UserMapper {
	@Select(value="select * from t_user where username = #{username}")
	User findByUsername(String username);


}
