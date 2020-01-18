package com.itheima.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;

/**
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
	
	@Reference//远程调用服务
	
    private CheckItemService checkItemService;
	//添加
	@RequestMapping("/add")
	public Result add(@RequestBody CheckItem checkItem){
		try {
			checkItemService.add(checkItem);
		} catch (Exception e) {
			return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
	}
	//列表
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageResult=checkItemService.findPage(
				queryPageBean.getCurrentPage(),
				queryPageBean.getPageSize(),
				queryPageBean.getQueryString());
		return pageResult;
	}
	//删除
	@RequestMapping("/delete")
	public Result delete(Integer id){
		try {
			checkItemService.delete(id);
		}catch (RuntimeException e) {
			return new Result(false, e.getMessage());
		}catch (Exception e) {
			return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
		}
		return new Result(false, MessageConstant.DELETE_CHECKITEM_SUCCESS);
	}
	//编辑
	@RequestMapping("/edit")
	public Result edit(@RequestBody CheckItem checkItem){
		try {
			checkItemService.edit(checkItem);
		}catch (Exception e) {
			return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
		}
		return new Result(false, MessageConstant.EDIT_CHECKITEM_SUCCESS);
	}
	@RequestMapping("/findById")
	public Result findById(Integer id){
		try {
			CheckItem checkItem=checkItemService.findById(id);
			return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
		}catch (Exception e) {
			//服务调用失败
			return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
		}
	}
	@RequestMapping("/findAll")
	public Result findAll(){
		List<CheckItem> checkItemlist=checkItemService.findAll();
		if(checkItemlist!=null&& checkItemlist.size()>0){
			Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
			result.setData(checkItemlist);
			return result;
		}
		return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
		
	}
}
