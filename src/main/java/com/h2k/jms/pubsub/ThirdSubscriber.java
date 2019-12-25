package com.h2k.jms.pubsub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ThirdSubscriber implements MessageListener {

	
	public void onMessage(Message message) {
		try {
			System.out.println("ThirdSubscriber  :: " + ((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
