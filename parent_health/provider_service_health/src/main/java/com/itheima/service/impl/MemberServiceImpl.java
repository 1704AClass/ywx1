package com.itheima.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.MD5Utils;
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Override
	public Member findByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return memberMapper.findByTelephone(telephone);
	}

	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		if(member.getPassword()!=null){
			member.setPassword(MD5Utils.md5(member.getPassword()));
		}
		memberMapper.add(member);
	}

	@Override
	public List<Integer> findMemberCountByMonth(List<String> month) {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<>();
		for (String m : month) {
			m=m+".31";
			Integer count=memberMapper.findMemberCountByMonth(m);
			list.add(count);
		}
		return list;
	}

}
