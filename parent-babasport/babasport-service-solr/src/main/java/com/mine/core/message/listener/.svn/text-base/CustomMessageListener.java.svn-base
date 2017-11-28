package com.mine.core.message.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.core.service.search.InsertService;

public class CustomMessageListener implements MessageListener {

	@Autowired
	private InsertService insertService;

	@Override
	public void onMessage(Message message) {
		ActiveMQTextMessage am = (ActiveMQTextMessage) message;
		try {
			String productId = am.getText();
			System.out.println("mq pid:" + productId);
			insertService.insertProduct2Solr(Long.parseLong(productId));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
