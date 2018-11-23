package com.cms.web.filter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageVisitMsgInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest req,
			HttpServletResponse resp, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod h = (HandlerMethod)handler;
			// System.out.println("执行方法： " + h.getMethod());
		}
		return super.preHandle(req, resp, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest req,
			HttpServletResponse resp, Object handler,
			ModelAndView modelAndView) throws Exception {
		StringBuffer url = req.getRequestURL();
		String context = req.getContextPath();
		req.setAttribute("bath", url.substring(0, url.indexOf(context)+context.length()));
		super.postHandle(req, resp, handler, modelAndView);
	}
}
