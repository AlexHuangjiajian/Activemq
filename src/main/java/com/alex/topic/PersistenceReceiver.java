package com.alex.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PersistenceReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("cc1");


        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic("Mytopic");
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(destination,"T1");

        connection.start();

        Message message = topicSubscriber.receive();
        while(message!=null){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到消息："+textMessage.getText());
            message = topicSubscriber.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
