package com.alex.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBroker {

    public static void main(String[] args) throws Exception {
        /**
         * 第一种

        BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616");
        broker.start();
      */

        //第二种
//        String Uri = "properties:broker.properties";
//        BrokerService broker = BrokerFactory.createBroker(Uri);
//        broker.addConnector("tcp://localhost:61616");
//        broker.start();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }
}
