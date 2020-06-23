package com.alex.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");

         MessageConsumer messageConsumer = session.createConsumer(destination);

        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) messageConsumer.receive();
            System.out.println(message.getText());
            session.commit();
        }
        session.close();
        connection.close();
    }
}
