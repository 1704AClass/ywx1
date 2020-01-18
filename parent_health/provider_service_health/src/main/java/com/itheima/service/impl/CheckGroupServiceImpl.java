package com.itheima.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckGroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
@Service
public class CheckGroupServiceImpl implements CheckGroupService{

	@Autowired
	private CheckGroupMapper checkGroupMapper;
	//添加检查组合,同时需要设置检查组和检查项的关联关系
	@Override
	public void add(CheckGroup checkGroup, Integer[] checkitems) {
		// TODO Auto-generated method stub
		checkGroupMapper.add(checkGroup);
		setCheckGroupAndItem(checkGroup.getId(),checkitems);
	}
	//设置检查组和检查项的关联关系
	public void setCheckGroupAndItem(Integer checkGroupId,Integer[] checkitemids){
		if(checkitemids!=null&&checkitemids.length>0){
			for(Integer checkitemId : checkitemids){
				Map<String,Integer> map=new HashMap<>();
				map.put("checkgroup_id", checkGroupId);
				map.put("checkitem_id", checkitemId);
				checkGroupMapper.updateCheckGroupIdAndCheckitemId(map);
			}
		}
	}
	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize,
			String queryString) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage,pageSize);
		Page<CheckGroup> page=checkGroupMapper.pageQuery(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
		// TODO Auto-generated method stub
		return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
	}
	@Override
	public CheckGroup findById(Integer id) {
		// TODO Auto-generated method stub
		return checkGroupMapper.findById(id);
	}
	//编辑检查组，同时需要更新和检查项的关系
	@Override
	public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		//根据检查组id删除中间表数据(清理原有关联关系)
		checkGroupMapper.deleteAssociation(checkGroup.getId());
		//向中间表插入数据
		setCheckGroupAndItem(checkGroup.getId(), checkitemIds);
		//更新检查组基本信息
		checkGroupMapper.edit(checkGroup);
	}
	@Override
	public List<CheckGroup> findAll() {
		// TODO Auto-generated method stub
		return checkGroupMapper.findAll();
	}

}
