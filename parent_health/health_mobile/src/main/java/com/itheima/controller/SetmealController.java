package com.itheima.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Reference
	private SetmealService setmealService;
	//获取所有套餐信息
	@RequestMapping("/getSetmeal")
	public Result  getSetmeal(){
		try {
			List<Setmeal> list=setmealService.findAll();
			return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
		}
	}
	@RequestMapping("/findById")
	public Result findById(int id){
		try {
			Setmeal setmeal=setmealService.findById(id);
			return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
		}
		
	}
}
