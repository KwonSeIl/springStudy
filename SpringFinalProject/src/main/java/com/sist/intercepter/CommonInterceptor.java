package com.sist.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * 								|인터셉트(preHandle() 사용) => 컨트롤러 찾기 전에 먼저 처리한다 ==> 자동 로그인, ID 저장 
 *  사용자 요청  ======> DispatcherServlet ======> @Controller 찾음 ======> ViewResolver =====> JSP - 인터셉트 가능(afterCompletion() 사용) => 로그인을 해야한다 메시지 날림/접근거부 
 *  	.do										--------------    | 인터셉트(postHandle() 사용)
 *  												|
 *  											  @GetMapping / @PostMapping / @RequestMapping 으로 구분 => 메소드를 찾아서 처리
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
