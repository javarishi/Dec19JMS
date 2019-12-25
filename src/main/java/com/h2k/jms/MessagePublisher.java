package com.h2k.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessagePublisher {
	
	private static final String brokerURL = "tcp://localhost:61616";

	public static void main(String[] args) {
		try {
			// Step 1 - Connection Factory
			ConnectionFactory conf = new ActiveMQConnectionFactory(brokerURL);
			// Step 2 - Create Connection 
			Connection connection = conf.createConnection();
			connection.start(); // Start connection is most important
			// Step 3 - Create a Session
			Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
			// Step 4 - Create Destination
			Topic testTopic = session.createTopic("H2K.DEC.2019.TOPIC");
			// Step 5 - Message Producer
			MessageProducer producer = session.createProducer(testTopic);
			// Step 6 - Create a message
			Message message = session.createTextMessage("My First Text Message to Topic");
			message.setStringProperty("CustomParam", "CustomValue");
			
			// Step 7 - Send the message with Send() method
			producer.send(message);
			System.out.println("Message send is successful");
			
			session.commit();
			connection.close();
		}catch(JMSException jmEx) {
			jmEx.printStackTrace();
		}
		

	}

}
