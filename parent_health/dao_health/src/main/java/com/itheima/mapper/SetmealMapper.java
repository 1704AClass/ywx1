package com.itheima.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

public interface SetmealMapper {

	void add(Setmeal setmeal);

	void setSetmealAndCheckGroup(Map<String, Integer> map);

	Page<Setmeal> findPage(String queryString);
	@Select(value="select * from t_setmeal")
	List<Setmeal> findAll();
	@Select(value="select * from t_setmeal  where id=#{id}")
	Setmeal findById(int id);
	@Select(value="select s.name,count(o.id) as value from t_order o ,t_setmeal s where o.setmeal_id = s.id group by s.name")
	List<Map<String, Object>> findSetmealCount();


}
