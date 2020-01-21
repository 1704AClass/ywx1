package com.itheima.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPool;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import com.itheima.utils.SMSUtils;
@RestController
@RequestMapping("order")
public class OrderController {

	@Reference
	private OrderService orderService;
	@Autowired
	private JedisPool jedisPool;
	
	/**
	 * 体检预约
	 * */
	@RequestMapping("/submit")
	public Result submitOrder(@RequestBody Map map){
		String telephone = (String) map.get("telephone");
		//从redis中获取缓存的验证码，key为手机号
		String codeInRedis = jedisPool.getResource().get(telephone+RedisMessageConstant.SENDTYPE_ORDER);
		String validataCode = (String) map.get("validatedateCode");
		//校验手机验证码
		if(codeInRedis==null||!codeInRedis.equals(validataCode)){
			return new Result(false, MessageConstant.VALIDATECODE_ERROR);
		}
		Result result=null;
		//调用预约服务
		try {
			map.put("orderType", Order.ORDERTYPE_WEIXIN);
			result=orderService.order(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//调用失败
			return result;
		}
		if(result.isFlag()){
			//预约成功，发送短信
			String orderDate = (String) map.get("orderDate");
			try {
				SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, orderDate);
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@RequestMapping("/findById")
	public Result findById(Integer id){
		try {
			Map map=orderService.findById(id);
			//查询预约信息成功
			return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//查询预约信息失败
			return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
		}
		
	}
}
