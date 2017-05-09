package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class testInterceptor1 implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("执行到afterCompletion");
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("执行到postHandle");
		arg3.addObject("msg","修改被传回的数据");
		
	}
/**返回值  表示我们是否需要将当前请求拦截下来
 * 返回true 请求继续
 * 返回false 请求被拦截
 * arg2 被拦截的请求目标对象
 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		//替代过滤器来处理乱码
		arg0.setCharacterEncoding("utf-8");
		//对用户是否登录做处理
		if(arg0.getSession().getAttribute("user")==null){
			//终止请求并去往登录页
			arg0.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(arg0, arg1);
			return false;
		}
		System.out.println("执行到preHandle");
		return true;
	}
	

}
