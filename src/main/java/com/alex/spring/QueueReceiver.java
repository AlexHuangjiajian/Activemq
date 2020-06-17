package com.alex.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueReceiver {
    @Autowired
    private JmsTemplate jt = null;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueReceiver queueReceiver= (QueueReceiver) context.getBean("queueReceiver");
        String msg = (String) queueReceiver.jt.receiveAndConvert();
        System.out.println("msg = " + msg);
    }
}
