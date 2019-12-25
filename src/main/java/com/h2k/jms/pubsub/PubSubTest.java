package com.h2k.jms.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PubSubTest {
	
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
			Topic testTopic = session.createTopic("H2K.DEC.2019.TOPIC");
			// Step 5 - Message Consumer
			MessageConsumer consumerOne = session.createConsumer(testTopic);
			consumerOne.setMessageListener(new FirstSubscriber());
			
			MessageConsumer consumerTwo = session.createConsumer(testTopic);
			consumerTwo.setMessageListener(new SecondSubscriber());
			
			MessageConsumer consumerThree = session.createConsumer(testTopic);
			consumerThree.setMessageListener(new ThirdSubscriber());
		
		}catch(JMSException jmEx) {
			jmEx.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		

	}


}
