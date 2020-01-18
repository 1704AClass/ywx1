package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;

@Service
public class CheckItemServiceImpl implements CheckItemService{

	@Autowired
	private CheckItemMapper checkItemMapper;
	@Override
	public void add(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkItemMapper.add(checkItem);
	}
	@Override
	public PageResult findPage(Integer currentPage, Integer pageSize,
			String queryString) {
		// TODO Auto-generated method stub
		PageHelper.startPage(currentPage,pageSize);
		Page<CheckItem> page=checkItemMapper.pageQuery(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		long count=checkItemMapper.findCountByCheckItemId(id);
		if(count>0){
			throw new RuntimeException("当前检查项被引用,不能被删除");
		}
		checkItemMapper.dels(id);
	}
	@Override
	public CheckItem findById(Integer id) {
		// TODO Auto-generated method stub
		return checkItemMapper.findById(id);
	}
	@Override
	public void edit(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkItemMapper.edit(checkItem);
	}
	@Override
	public List<CheckItem> findAll() {
		// TODO Auto-generated method stub
		return checkItemMapper.findAll();
	}

}
