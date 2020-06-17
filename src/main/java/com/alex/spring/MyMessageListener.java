package com.alex.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("receive txt msg == "+msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
