package com.h2k.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {
	
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
			// Step 5 - Message Consumer
			MessageConsumer consumer = session.createConsumer(testQueue);
			Message message = null;
			while(true) {
				message = consumer.receiveNoWait();
				System.out.println("Message Received : " + message);
				Thread.sleep(1000);
			}
		}catch(JMSException jmEx) {
			jmEx.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		

	}


}
