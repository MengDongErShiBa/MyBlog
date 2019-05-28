package com.mdesb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 后台访问拦截
 * @author 孟冬二十八。
 *
 * 2019年5月22日-下午5:28:43
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器；
	 * true表示继续流程（如调用下一个拦截器或处理器）
	 * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取访问路径
		String uri=request.getRequestURI();
		//如果是访问amdin，并且loginUser为空，则跳转到登陆页面。
		if(uri.startsWith("/admin")&&null==request.getSession().getAttribute("loginUser")){
			request.getSession().setAttribute("errorMsg", "请重新登陆");
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return false;
		}else{
			//否则删除错误提示信息
			request.removeAttribute("errorMsg");
			return true;
		}
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
