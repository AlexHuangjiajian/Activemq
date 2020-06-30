package com.alex.staticnetwork;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueReceiver2 {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory  connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        for (int i = 0; i <10 ; i++) {
            Thread thread = new MyThread(connectionFactory);
            thread.start();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread2 extends Thread{
    private ConnectionFactory connectionFactory;
    public MyThread2(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void run(){
        try{
            Connection connection = connectionFactory.createConnection();
            connection.start();

            final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("my-queue");

            MessageConsumer messageConsumer = session.createConsumer(destination);

//            for (int i = 0; i < 3; i++) {
//                TextMessage message = (TextMessage) messageConsumer.receive();
//                System.out.println(message.getText());
//                session.commit();
//            }
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("receiver2接到消息:"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                    try {
                        session.commit();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}