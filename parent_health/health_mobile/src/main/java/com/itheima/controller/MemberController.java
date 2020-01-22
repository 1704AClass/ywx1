package com.itheima.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;

@RequestMapping("/member")
@RestController
public class MemberController {
	
	@Reference
	private MemberService memberService;
	@Autowired
	private JedisPool jedisPool;
	
	//使用手机号和验证码登录
	@RequestMapping("/login")
	public Result login(HttpServletResponse response,@RequestBody Map map){
		String telephone = (String) map.get("telephone");
		String validateCode = (String) map.get("validateCode");
		//从Redis中获取缓存的验证码
		String codeInRedis = jedisPool.getResource().get(telephone+RedisMessageConstant.SENDTYPE_LOGIN);
		if(codeInRedis==null || !codeInRedis.equals(validateCode)){
			//验证码输入错误
			return new Result(false, MessageConstant.VALIDATECODE_ERROR);
		}else{
			//验证码输入正确
			//判断当前用户是否是会员
			Member member=memberService.findByTelephone(telephone);
			if(member==null){
				//当前用户不是会员
				member = new  Member();
				member.setPhoneNumber(telephone);
				member.setRegTime(new Date());
				memberService.add(member);
			}
			//登录成功
			//写入Cookie，跟踪用户
			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("login_member_telephone",telephone);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			//保存会员信息到redis中
			String json = JSON.toJSON(member).toString();
			jedisPool.getResource().setex(telephone, 60*30, json);
			return new Result(true, MessageConstant.LOGIN_SUCCESS);
		}
	}
	
}
