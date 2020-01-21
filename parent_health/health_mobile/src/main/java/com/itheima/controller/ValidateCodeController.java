package com.itheima.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
@RestController
@RequestMapping("validateCode")
public class ValidateCodeController {

	@Reference
	private JedisPool jedisPool;
	
	//体现预约时发送手机验证码
	@RequestMapping("/send4Order")
	public Result send4Order(String telephone){
		//生成数字验证码
		Integer code = ValidateCodeUtils.generateValidateCode(4);
		try {
			//发送短信
			SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//验证码发送失败
			return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
		}
		jedisPool.getResource().setex(telephone+RedisMessageConstant.SENDTYPE_ORDER, 5*60, code.toString());
		//验证码发送成功
		return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
	}
}
