package com.mdesb.service;

import org.apache.ibatis.annotations.Param;

import com.mdesb.model.AdminUser;

public interface IAdminUserService {

	/**
	 * 登陆方法
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	AdminUser login(@Param("userName") String userName,
			@Param("password") String password);

	/**
	 * 获取用户信息
	 * 
	 * @param loginUserId
	 * @return
	 */
	AdminUser getUserDetailById(Integer loginUserId);

	/**
	 * 修改当前登录用户的密码
	 * 
	 * @param loginUserId
	 * @param originalPassword
	 * @param newPassword
	 * @return
	 */
	Boolean updatePassword(Integer loginUserId, String originalPassword,
			String newPassword);

	/**
	 * 修改当前登录用户的名称信息
	 * 
	 * @param loginUserId
	 * @param loginUserName
	 * @param nickName
	 * @return
	 */
	Boolean updateName(Integer loginUserId, String loginUserName,
			String nickName);
	
	Boolean addAdminUser(AdminUser adminUser);
}
