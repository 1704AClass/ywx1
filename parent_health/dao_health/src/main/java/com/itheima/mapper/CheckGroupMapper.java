package com.itheima.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

public interface CheckGroupMapper {

	Page<CheckGroup> pageQuery(String queryString);
	
	void add(CheckGroup checkGroup);

	void updateCheckGroupIdAndCheckitemId(Map map);

}
