package com.mine.myboot.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.mine.myboot.websocket.message.RequestMessage;
import com.mine.myboot.websocket.message.ResponseMessage;

@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public ResponseMessage say(RequestMessage message) {
		System.out.println(message.getName());
		return new ResponseMessage("welcome," + message.getName() + " !");
	}
}
