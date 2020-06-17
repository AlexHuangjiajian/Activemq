package com.alex.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class NopersistenceReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("my-topic");
        MessageConsumer consumer = session.createConsumer(destination);

//        for (int i = 0; i < 3; i++) {
//            TextMessage message = session.createTextMessage("message2222"+i);
//            producer.send(message);
//        }
        Message message = consumer.receive();
        while(message!=null){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到消息："+textMessage.getText());
            message = consumer.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
