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
	@Select(value="select count(id) from t_order where orderDate = #{value}")
	Integer findOrderCountByDate(String today);
	@Select(value="select count(id) from t_order where orderDate &gt;= #{value}")
	Integer findOrderCountAfterDate(String thisWeekMonday);
	@Select(value="select count(id) from t_order where orderDate = #{value} and  orderStatus = '已到诊'")
	Integer findVisitsCountByDate(String today);
	@Select(value="select count(id) from t_order where orderDate &gt;= #{value} and  orderStatus = '已到诊'")
	Integer findVisitsCountAfterDate(String thisWeekMonday);
	@Select(value="select s.name count(o.id) setmeal_count,count(o.id)/(select count(id) from t_order) proportion from t_order o inner join t_setmeal s on s.id =o.setmeal_id group by o.setmeal_id order by setmeal_count desc limit 0,4")
	List<Map> findHotSetmeal();


}
