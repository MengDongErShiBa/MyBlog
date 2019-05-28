package com.mdesb.service.impl;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdesb.dao.AdminUserMapper;
import com.mdesb.model.AdminUser;
import com.mdesb.service.IAdminUserService;
import com.mdesb.util.MD5Util;

/**
 * 用户service
 * 
 * @author 孟冬二十八。
 * 
 *         2019年5月23日-上午11:07:15
 */
@Service
@Transactional
public class AdminUserService implements IAdminUserService {

	@Autowired
	private AdminUserMapper userMapper;

	public AdminUser login(String userName, String password) {
		password = MD5Util.MD5Encode(password, "UTF-8");
		return userMapper.login(userName, password);
	}

	public AdminUser getUserDetailById(Integer loginUserId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(loginUserId);
	}

	public Boolean updatePassword(Integer loginUserId, String originalPassword,
			String newPassword) {
		AdminUser adminUser = userMapper.selectByPrimaryKey(loginUserId);
		// 当前用户非空才可以进行更改
		if (adminUser != null) {
			String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword,
					"UTF-8");
			String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
			// 比较原密码是否正确
			if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
				// 设置新密码并修改
				adminUser.setLoginPassword(newPasswordMd5);
				if (userMapper.updateByPrimaryKeySelective(adminUser) > 0) {
					// 修改成功则返回true
					return true;
				}
			}
		}
		return false;
	}

	public Boolean updateName(Integer loginUserId, String loginUserName,
			String nickName) {
        AdminUser adminUser = userMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (userMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
	}

	@Override
	public Boolean addAdminUser(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return null;
	}


}
