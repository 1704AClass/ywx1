package com.itheima.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/getUsername")
	public Result getUsername(){
		try {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, MessageConstant.GET_USERNAME_FAIL);
		}
		return new Result(true, MessageConstant.GET_USERNAME_SUCCESS);
	}
}
