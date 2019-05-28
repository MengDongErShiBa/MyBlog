package com.mdesb.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mdesb.dao.AdminUserMapper;
import com.mdesb.model.AdminUser;
import com.mdesb.util.MD5Util;

@Controller
public class TestController {

	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@RequestMapping("/test")
	public @ResponseBody String getUser(){
		AdminUser user = adminUserMapper.selectByPrimaryKey(1);
		System.out.println(user);
		return "项目运行成功";
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.MD5Encode("admin", "UTF-8"));
	}
	
}
