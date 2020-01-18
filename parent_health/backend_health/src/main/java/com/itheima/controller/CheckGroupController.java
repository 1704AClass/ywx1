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
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;

/**
 * 检查组
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

	@Reference
	private CheckGroupService checkGroupService;
	
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitems){
		try {
			checkGroupService.add(checkGroup,checkitems);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
		}
		return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
	}
	//分页查询
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageResult=checkGroupService.pageQuery(
					queryPageBean.getCurrentPage(),
					queryPageBean.getPageSize(),
					queryPageBean.getQueryString()
				);
		return pageResult;
	}
	//根据id查询
	@RequestMapping("/findById")
	public Result findById(Integer id){
		CheckGroup checkGroup=checkGroupService.findById(id);
		if(checkGroup!=null){
			Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
		}
		return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
	}
	//根据检查组合id查询对应的所有检查项id
	@RequestMapping("/findCheckItemIdsByCheckGroupId")
	public Result findCheckItemIdsByCheckGroupId(Integer id){
		try {
			List<Integer> checkItemIds=checkGroupService.findCheckItemIdsByCheckGroupId(id);
			return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
		}
	}
	//编辑
	@RequestMapping("/edit")
	public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
		try {
			checkGroupService.edit(checkGroup,checkitemIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
		}
		return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
	}
	//查询所有
	@RequestMapping("/findAll")
	public Result findAll(){
		List<CheckGroup> list=checkGroupService.findAll();
		if(list!=null&&list.size()>0){
			Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
			result.setData(list);
		}
		return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
	}
}
