package com.mine.server;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.mine.servlet.Servlet;

public class WebApp {

	public static ServletContext servletContext;
	static {
		servletContext = new ServletContext();

		// 1.获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.从解析工厂中获取解析器
		SAXParser parser;
		PersonHandler handler = new PersonHandler();
		try {
			parser = factory.newSAXParser();
			parser.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("web.xml"), handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 赋值
		servletContext.setServlet(handler.getServlet());
		servletContext.setMapping(handler.getMapping());
	}

	public static Servlet getServlet(String url) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		if (url == null || (url = url.trim()).equals("")) {
			return null;
		} else {
			String servletPath = servletContext.getServlet().get(
					servletContext.getMapping().get(url));

			return servletPath == null ? null : (Servlet) Class.forName(
					servletPath).newInstance();
		}
	}
}
