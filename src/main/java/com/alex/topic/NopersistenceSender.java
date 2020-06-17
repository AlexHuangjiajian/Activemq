package com.alex.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class NopersistenceSender {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("my-topic");

         MessageProducer messageProducer = session.createProducer(destination);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message111--"+i);
            messageProducer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
