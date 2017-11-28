package com.mine.servlet;

import com.mine.server.Request;
import com.mine.server.Response;

public class LoginServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) {

		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		if (login(name, pwd)) {
			response.println("µÇÂ½³É¹¦£¡");
		} else {
			response.println("µÇÂ½Ê§°Ü£¡");
		}
	}

	@Override
	public void doPost(Request request, Response response) {
		// TODO Auto-generated method stub

	}

	public boolean login(String name, String pwd) {
		if (name == null || pwd == null) {
			return false;
		}
		return "myname".equals(name) && "123456".equals(pwd);
	}

}
