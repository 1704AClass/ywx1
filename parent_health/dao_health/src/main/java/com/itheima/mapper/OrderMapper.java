package com.itheima.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Order;

public interface OrderMapper {

	void add(Order order);

	List<Order> findByCondition(Order order);
	@Select(value="select m.name member ,s.name setmeal,o.orderDate orderDate,o.orderType  orderType   from   t_order o,   t_member m,   t_setmeal s   where o.member_id=m.id and o.setmeal_id=s.id and o.id=#{id} ")
	Map findById4Detail(Integer id);


}
