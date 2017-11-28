package com.mine.server;

import java.io.IOException;
import java.net.Socket;

import com.mine.servlet.Servlet;
import com.mine.util.CloseUtil;

public class Dispatcher implements Runnable {

	private Socket client;
	private Request request;
	private Response response;
	private int code;

	public Dispatcher(Socket client) {
		System.out.println("------------");
		this.code = 200;
		this.client = client;
		// System.out.println("socket open...");
		try {
			this.request = new Request(client.getInputStream());
			this.response = new Response(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			code = 500;
		}
	}

	@Override
	public void run() {
		try {
			Servlet serv = WebApp.getServlet(request.getUrl());
			if (serv == null) {
				code = 404;
			} else {
				serv.service(request, response);
			}
			response.pushToClient(code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stop();
		}
	}

	public void stop() {

		CloseUtil.closeSocket(client);
		// System.out.println("socket close...");

	}

}
