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
		response.println("<title>��Ӧtitle</title></head>");
		response.println("<body>��� :").println(request.getParameter("uname"))
				.println(" ע��ɹ���");
		response.println("</body></html>");
	}

}
