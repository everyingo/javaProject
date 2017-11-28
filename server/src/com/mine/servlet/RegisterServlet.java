package com.mine.servlet;

import com.mine.server.Request;
import com.mine.server.Response;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request request, Response response) {

	}

	@Override
	public void doPost(Request request, Response response) {
		response.println("<html><head>");
		response.println("<title>响应title</title></head>");
		response.println("<body>你好 :").println(request.getParameter("uname"))
				.println(" 注册成功！");
		response.println("</body></html>");
	}

}
