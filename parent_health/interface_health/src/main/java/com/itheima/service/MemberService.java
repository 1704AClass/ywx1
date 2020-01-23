package com.itheima.service;

import java.util.List;

import com.itheima.pojo.Member;

public interface MemberService {

	Member findByTelephone(String telephone);

	void add(Member member);

	List<Integer> findMemberCountByMonth(List<String> list);

}
