package com.alex.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueSender {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61676");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("networkMessage ccc"+i);
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
