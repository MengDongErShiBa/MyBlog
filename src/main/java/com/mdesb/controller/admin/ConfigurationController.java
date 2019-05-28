package com.mdesb.controller.admin;

import javax.servlet.http.HttpServletRequest;

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

import com.mdesb.model.Config;
import com.mdesb.service.IConfigService;
import com.mdesb.util.Result;
import com.mdesb.util.ResultGenerator;

/**
 * 系统配置
 * 
 * @author 孟冬二十八。
 * 
 *         2019年5月23日-下午4:09:06
 */
@Controller
@RequestMapping("/admin")
public class ConfigurationController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConfigurationController.class);

	@Autowired
	private IConfigService configService;

	/**
	 * 获取所有配置
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/configurations")
	public String list(HttpServletRequest request) {
		request.setAttribute("path", "configurations");
		request.setAttribute("configurations", configService.getAllConfigs());
		LOG.info(request.getSession().getAttribute("loginUser") + "获取所有配置项！");
		return "admin/configuration";
	}

	/**
	 * 修改站点信息
	 * 
	 * @param config
	 * @return
	 */
	@PostMapping("/configurations/website")
	@ResponseBody
	public Result website(Config config) {
		LOG.info("准备修改站点信息！");
		int updateResult = configService.updateConfig(config);
		return ResultGenerator.genSuccessResult(updateResult > 0);
	}

	/**
	 * 修改个人信息
	 * 
	 * @param config
	 * @return
	 */
	@PostMapping("/configurations/userInfo")
	@ResponseBody
	public Result userInfo(Config config) {
		LOG.info("准备修改个人信息！");
		int updateResult = configService.updateConfig(config);
		return ResultGenerator.genSuccessResult(updateResult > 0);
	}

	/**
	 * 修改底部信息
	 * 
	 * @param config
	 * @return
	 */
	@PostMapping("/configurations/footer")
	@ResponseBody
	public Result footer(Config config) {
		LOG.info("准备修改底部信息！");
		int updateResult = configService.updateConfig(config);
		return ResultGenerator.genSuccessResult(updateResult> 0);
	}

}
