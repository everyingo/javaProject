package com.mine.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mine.util.CloseUtil;

/**
 * ∂‡œﬂ≥ÃServer
 * 
 * @author Administrator
 * 
 */
public class Server {

	private ServerSocket server;
	private boolean isShutdown = false;

	public static void main(String[] args) {
		Server server = new Server();
		server.start(8888);
	}

	public void start(int code) {
		try {
			server = new ServerSocket(code);
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void receive() {
		try {
			while (!isShutdown) {
				Socket client = server.accept();
				new Thread(new Dispatcher(client)).start();
			}
		} catch (IOException e) {
			isShutdown = true;
			CloseUtil.closeSocket(server);
		}

	}
}
