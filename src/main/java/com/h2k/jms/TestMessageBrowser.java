package com.h2k.jms;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestMessageBrowser {
	
	private static final String brokerURL = "tcp://localhost:61616";

	public static void main(String[] args) {
	
	try {
		// Step 1 - Connection Factory
		ConnectionFactory conf = new ActiveMQConnectionFactory(brokerURL);
		// Step 2 - Create Connection 
		Connection connection = conf.createConnection();
		connection.start(); // Start connection is most important
		// Step 3 - Create a Session
		final Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		// Step 4 - Create Destination
		Queue testQueue = session.createQueue("H2K.DEC.2019.TEST");
		// Step 5 - Message Consumer
		MessageConsumer consumer = session.createConsumer(testQueue);
		
	//	QueueBrowser browser = session.createBrowser(testQueue);
	//	Enumeration<Message> messages = browser.getEnumeration();
		
	
	}catch(JMSException jmEx) {
		jmEx.printStackTrace();
	}catch (Exception e) {
		// TODO: handle exception
	}
	

}


}
