package com.itheima.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

public interface SetmealService {

	void add(Setmeal setmeal, Integer[] checkgroupIds);

	PageResult findPage(Integer currentPage, Integer pageSize,String queryString);

	List<Setmeal> findAll();

	Setmeal findById(int id);

}
