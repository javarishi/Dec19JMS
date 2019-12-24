package com.h2k.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestMessageSender {
	
	private static final String brokerURL = "tcp://localhost:61616";

	public static void main(String[] args) {
		try {
			// Step 1 - Connection Factory
			ConnectionFactory conf = new ActiveMQConnectionFactory(brokerURL);
			// Step 2 - Create Connection 
			Connection connection = conf.createConnection();
			connection.start(); // Start connection is most important
			// Step 3 - Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Step 4 - Create Destination
			Queue testQueue = session.createQueue("H2K.DEC.2019.TEST");
			// Step 5 - Message Producer
			MessageProducer producer = session.createProducer(testQueue);
			// Step 6 - Create a message
			Message message = session.createTextMessage("My First Text Message to Queue");
			// Step 7 - Send the message with Send() method
			producer.send(message);
			System.out.println("Message send is successful");
			
			session.close();
			connection.close();
		}catch(JMSException jmEx) {
			jmEx.printStackTrace();
		}
		

	}

}
