package com.mdesb.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.IbatisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.mdesb.model.AdminUser;
import com.mdesb.service.IAdminUserService;
import com.mdesb.service.IBlogService;
import com.mdesb.service.ICategoryService;
import com.mdesb.service.ICommentService;
import com.mdesb.service.IConfigService;
import com.mdesb.service.ILinkService;
import com.mdesb.service.ITagService;

/**
 * 用户控制
 * @author 孟冬二十八。
 * 
 *         2019年5月22日-下午5:30:00
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger LOG = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IBlogService blogService;
	@Autowired
	private ILinkService linkService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IConfigService configService;

	/**
	 * get方式跳转登陆页面
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		LOG.info(request.getLocalName() + "访问博客后台！");
		return "admin/login";
	}

	/**
	 * 加载首页
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping({ "", "/", "/index", "index.html" })
	public String index(HttpServletRequest request) {
		request.setAttribute("path", "index");
		request.setAttribute("categoryCount",
				categoryService.getTotalCategories());
		request.setAttribute("blogCount", blogService.getTotalBlogs());
		request.setAttribute("linkCount", linkService.getTotalLinks());
		request.setAttribute("tagCount", tagService.getTotalTags());
		request.setAttribute("commentCount", commentService.getTotalComments());
		request.getSession().setAttribute("configurations", configService.getAllConfigs());
		return "admin/index";
	}

	/**
	 * 登陆 用户名
	 * 
	 * @param userName
	 * @param password
	 * @param verifyCode
	 * @param session
	 * 
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode, HttpSession session) {
		if (StringUtils.isEmpty(verifyCode)) {
			session.setAttribute("errorMsg", "验证码不能为空！");
			return "admin/login";
		}
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			session.setAttribute("errorMsg", "用户名或密码不能为空！");
			return "admin/login";
		}
		String kaptchaCode = session.getAttribute("verifyCode") + "";
		if (StringUtils.isEmpty(kaptchaCode) || !kaptchaCode.equals(verifyCode)) {
			session.setAttribute("errorMsg", "验证码错误！");
			return "admin/login";
		}
		AdminUser adminUser = adminUserService.login(userName, password);
		if (null != adminUser) {
			session.setAttribute("loginUser", adminUser.getNickName());
			session.setAttribute("loginUserId", adminUser.getAdminUserId());
			// session过期时间设置为7200秒 即两小时
			// session.setMaxInactiveInterval(60 * 60 * 2);
			LOG.info("用户" + adminUser.getNickName() + "登陆博客后台");
			return "redirect:/admin/index";
		} else {
			session.setAttribute("errorMsg", "用户名或密码错误！");
			return "admin/login";
		}
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
		Integer loginUserId = (int) request.getSession().getAttribute(
				"loginUserId");
		AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
		if (adminUser == null) {
			System.out.println("没找到用户？" + loginUserId);
			return "admin/login";
		}
		request.setAttribute("path", "profile");
		request.setAttribute("loginUserName", adminUser.getLoginUserName());
		request.setAttribute("nickName", adminUser.getNickName());
		return "admin/profile";
	}

	@PostMapping("/profile/password")
	@ResponseBody
	public String passwordUpdate(HttpServletRequest request,
			@RequestParam("originalPassword") String originalPassword,
			@RequestParam("newPassword") String newPassword) {
		if (StringUtils.isEmpty(originalPassword)
				|| StringUtils.isEmpty(newPassword)) {
			return "参数不能为空";
		}
		Integer loginUserId = (int) request.getSession().getAttribute(
				"loginUserId");
		if (adminUserService.updatePassword(loginUserId, originalPassword,
				newPassword)) {
			// 修改成功后清空session中的数据，前端控制跳转至登录页
			request.getSession().removeAttribute("loginUserId");
			request.getSession().removeAttribute("loginUser");
			request.getSession().removeAttribute("errorMsg");
			return "success";
		} else {
			return "修改失败";
		}
	}

	@PostMapping("/profile/name")
	@ResponseBody
	public String nameUpdate(HttpServletRequest request,
			@RequestParam("loginUserName") String loginUserName,
			@RequestParam("nickName") String nickName) {
		if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
			return "参数不能为空";
		}
		Integer loginUserId = (int) request.getSession().getAttribute(
				"loginUserId");
		if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
			return "success";
		} else {
			return "修改失败";
		}
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUserId");
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("errorMsg");
		return "admin/login";
	}
}
