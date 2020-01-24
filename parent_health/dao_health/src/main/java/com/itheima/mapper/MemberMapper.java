package com.itheima.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Member;

public interface MemberMapper {
	@Select(value="select * from t_member where phoneNumber = #{phoneNumber}")
	Member findByTelephone(String telephone);

	void add(Member member);
	@Select(value="select count(id) from t_member where regTime &lt;= #{value}")
	Integer findMemberCountByMonth(String m);
	@Select(value="select count(id) from t_member where regTime = #{value}")
	Integer findMemberCountByDate(String today);
	@Select(value="select count(id) from t_member")
	Integer findMemberTotalCount();
	@Select(value="select count(id) from t_order where orderDate &gt;= #{value}")
	Integer findMemberCountAfterDate(String thisWeekMonday);


}
