package com.itheima.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Reference
	private SetmealService setmealService;
	//图片上传
	@RequestMapping("/upload")
	public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
		try {
			//获取原始文件
			String originalFilename = imgFile.getOriginalFilename();
			int count=originalFilename.lastIndexOf(".");
			//获取文件后缀
			String string = originalFilename.substring(count-1);
			//使用UUID随机产生文件名称，防止同名文件覆盖
			String fileName=UUID.randomUUID().toString()+string;
			QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
			//图片上传成功
			Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//图片上传失败
			return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
		}
	}
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
		try {
			setmealService.add(setmeal,checkgroupIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//新增失败
			return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
		}
		//新增成功
		return new Result(false, MessageConstant.ADD_SETMEAL_SUCCESS);
	}
	//分页查询
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageResult=setmealService.findPage(
					queryPageBean.getCurrentPage(),
					queryPageBean.getPageSize(),
					queryPageBean.getQueryString()
				);
		return pageResult;
	}
}
