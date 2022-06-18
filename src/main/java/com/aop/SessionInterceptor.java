package com.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ������,���ڿ����û�,������session���ܵ�¼ HandlerInterceptorAdapter ����̳У���
 * 
 * @author AB-IN
 *
 */
@SuppressWarnings("deprecation")
public class SessionInterceptor extends HandlerInterceptorAdapter {

	/**
	 * handler �������,���Եõ����õķ����������Ϣ
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 * HandlerMethod h = (HandlerMethod) handler; System.out.println("��ǰ�������ķ����ǣ�" +
		 * h.getMethod().getName()); System.out.println("�����ص��ķ�������������" +
		 * h.getMethod().getParameterCount()); System.out.println("������������������� " +
		 * h.getBean().getClass().getName());
		 */

		// û��session,��ת����¼ҳ
		if (request.getSession().getAttribute("session_user") == null) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}
	}

}