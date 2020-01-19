package com.itheima.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.OrderSetting;

public interface OrderSettingMapper {
	@Select(value="select count(*) from t_ordersetting where orderDate=#{orderDate}")
	long findCountByOrderDate(Date orderDate);
	@Insert(value="insert into t_ordersetting (orderDate,number,reservations) values(#{orderDate},#{number},#{reservations})")
	void add(OrderSetting orderSetting);
	@Select(value="select * from t_ordersetting where orderDate between #{dateBegin} and #{dateEnd}")
	List<OrderSetting> getOrderSettingByMonth(Map map);
	@Update(value="update t_ordersetting set number = #{number} where orderDate=#{orderDate} ")
	void editNumberByOrderDate(OrderSetting orderSetting);


}
