package com.mine.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {

	public static void closeIO(Closeable... clos) {
		for (Closeable c : clos) {
			if (c != null) {
				try {
					c.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void closeSocket(Socket client)  {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void closeSocket(ServerSocket socket) {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
