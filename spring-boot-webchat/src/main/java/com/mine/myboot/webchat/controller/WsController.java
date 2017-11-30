package com.mine.myboot.webchat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		if (principal.getName().equals("aa")) {
			messagingTemplate.convertAndSendToUser("bb", "/queue/notifications",
					principal.getName() + "给您发来了消息：" + msg);
		} else {
			messagingTemplate.convertAndSendToUser("aa", "/queue/notifications",
					principal.getName() + "给您发来了消息：" + msg);
		}
	}
}
