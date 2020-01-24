package com.itheima.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
@Service
public class SetmealServiceImpl implements SetmealService{

	@Autowired
	private SetmealMapper setmealMapper;
	@Override
	public void add(Setmeal setmeal, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		setmealMapper.add(setmeal);
		if(checkgroupIds!=null&& checkgroupIds.length>0){
			setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
		}
	}
	//设置检查组和检查项的关联关系
		public void setSetmealAndCheckGroup(Integer checkGroupId,Integer[] checkitemids){
			if(checkitemids!=null&&checkitemids.length>0){
				for(Integer checkitemId : checkitemids){
					Map<String,Integer> map=new HashMap<>();
					map.put("checkgroup_id", checkitemId);
					map.put("setmeal_id", checkGroupId);
					setmealMapper.setSetmealAndCheckGroup(map);
				}
			}
		}
		@Override
		public PageResult findPage(Integer currentPage, Integer pageSize,
				String queryString) {
			// TODO Auto-generated method stub
			PageHelper.startPage(currentPage, pageSize);
			Page<Setmeal> page=setmealMapper.findPage(queryString);
			return new PageResult(page.getTotal(), page.getResult());
		}
		@Override
		public List<Setmeal> findAll() {
			// TODO Auto-generated method stub
			return setmealMapper.findAll();
		}
		@Override
		public Setmeal findById(int id) {
			// TODO Auto-generated method stub
			return setmealMapper.findById(id);
		}
		@Override
		public List<Map<String, Object>> findSetmealCount() {
			// TODO Auto-generated method stub
			return setmealMapper.findSetmealCount();
		}

}
