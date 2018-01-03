package com.mine.myboot.shiro.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "/error/50X";

	/**
	 * 500
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		System.out.println("---404,500---");
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	/*
	 * Json str
	 * 
	 * @ExceptionHandler(value = Exception.class)
	 * 
	 * @ResponseBody public String jsonErrorHandler(HttpServletRequest req,
	 * Exception e) throws Exception {
	 * 
	 * return "json str"; }
	 */

}
