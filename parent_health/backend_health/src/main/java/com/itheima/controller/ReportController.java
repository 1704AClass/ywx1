package com.itheima.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.MemberService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Reference
	private MemberService memberService;
	//会员统计
	@RequestMapping("/getMemberReport")
	public Result getMemberReport(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,-12);
		List<String> list=new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			calendar.add(Calendar.MONTH, 1);
			list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("months", list);
		List<Integer> memberCount=memberService.findMemberCountByMonth(list);
		map.put("memberCount", memberCount);
		return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS);
	}
}
