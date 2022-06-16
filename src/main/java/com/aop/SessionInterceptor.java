package com.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器,用于控制用户,必须有session才能登录 HandlerInterceptorAdapter 必须继承！！
 * 
 * @author AB-IN
 *
 */
@SuppressWarnings("deprecation")
public class SessionInterceptor extends HandlerInterceptorAdapter {

	/**
	 * handler 这个参数,可以得到调用的方法的相关信息
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * HandlerMethod h = (HandlerMethod) handler; System.out.println("当前被拦到的方法是：" +
		 * h.getMethod().getName()); System.out.println("被拦截到的方法参数个数：" +
		 * h.getMethod().getParameterCount()); System.out.println("这个方法所属的类名： " +
		 * h.getBean().getClass().getName());
		 */

		// 没有session,则转到登录页
		if (request.getSession().getAttribute("session_user") == null) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}
	}

}