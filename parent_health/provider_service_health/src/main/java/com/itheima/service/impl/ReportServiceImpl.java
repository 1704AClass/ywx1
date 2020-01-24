package com.itheima.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.MemberMapper;
import com.itheima.mapper.OrderMapper;
import com.itheima.service.ReportService;
import com.itheima.utils.DateUtils;
@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public Map<String, Object> getBusinessReportData() throws Exception {
		// TODO Auto-generated method stub
		//获取当前日期
		String today = DateUtils.parseDate2String(DateUtils.getToday());
		//获取本周一的日期
		String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
		//获得本月的第一天的日期
		String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
		//今日新增会员数
		Integer todayNewMember = memberMapper.findMemberCountByDate(today);
		//总会员数
		Integer totalMember = memberMapper.findMemberTotalCount();
		//本周新增会员数
		Integer thisWeekNewMember=memberMapper.findMemberCountAfterDate(thisWeekMonday);
		//本月新增会员数
		Integer thisMonthNewMember=memberMapper.findMemberCountAfterDate(firstDay4ThisMonth);
		//今日预约数
		Integer todayOrderNumber =orderMapper.findOrderCountByDate(today);
		//本周预约数
		Integer thisWeekOrderNumber=orderMapper.findOrderCountAfterDate(thisWeekMonday);
		//本月预约数
		Integer thisMonthOrderNumber=orderMapper.findOrderCountAfterDate(firstDay4ThisMonth);
		//今日到诊数
		Integer todayVisitsNumber=orderMapper.findVisitsCountByDate(today);
		//本周到诊数
		Integer thisWeekVisitsNumber=orderMapper.findVisitsCountAfterDate(thisWeekMonday);
		//本月到诊数
		Integer thisMonthVisitsNumber=orderMapper.findVisitsCountAfterDate(firstDay4ThisMonth);
		//热门套餐(去前4)
		List<Map> hotSetmeal=orderMapper.findHotSetmeal();
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("reportDate",today);
		result.put("todayNewMember",todayNewMember);
		result.put("totalMember",totalMember); 
		result.put("thisWeekNewMember",thisWeekNewMember);
		result.put("thisMonthNewMember",thisMonthNewMember);
		result.put("todayOrderNumber",todayOrderNumber);
		result.put("thisWeekOrderNumber",thisWeekOrderNumber);
		result.put("thisMonthOrderNumber",thisMonthOrderNumber);
		result.put("todayVisitsNumber",todayVisitsNumber);
		result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
		result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
		result.put("hotSetmeal",hotSetmeal);
		return result;
	}
	@Override
	public Map<String, Object> getBusinessReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
