package com.alex.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PersistenceSender {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();


        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("Mytopic");

        MessageProducer messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message to mysql--"+i);
            messageProducer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
