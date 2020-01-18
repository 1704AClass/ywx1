package com.itheima.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

public interface CheckGroupMapper {

	Page<CheckGroup> pageQuery(String queryString);
	
	void add(CheckGroup checkGroup);
	@Select(value="select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
	void updateCheckGroupIdAndCheckitemId(Map map);

	List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
	@Select(value="select * from t_checkgroup where id = #{id}")
	CheckGroup findById(Integer id);
	
	void edit(CheckGroup checkGroup);
	@Delete(value="delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
	void deleteAssociation(Integer id);
	@Select(value="select * from t_checkgroup")
	List<CheckGroup> findAll();

}
