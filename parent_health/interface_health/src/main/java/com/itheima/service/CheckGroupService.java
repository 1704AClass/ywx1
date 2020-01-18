package com.itheima.service;

import java.util.List;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

public interface CheckGroupService {

	void add(CheckGroup checkGroup, Integer[] checkitems);

	PageResult pageQuery(Integer currentPage, Integer pageSize,String queryString);

	List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

	CheckGroup findById(Integer id);

	void edit(CheckGroup checkGroup, Integer[] checkitemIds);

	List<CheckGroup> findAll();

}
