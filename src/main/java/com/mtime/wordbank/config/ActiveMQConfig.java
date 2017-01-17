package com.mtime.wordbank.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;

/**
 * Created by Mtime on 2016/3/10.
 */
@Configuration
@ConfigurationProperties(prefix = ActiveMQConfig.ACTIVEMQCONFIG_PREFIX)
public class ActiveMQConfig {
    private Logger log = LoggerFactory.getLogger(getClass());
    public static final String ACTIVEMQCONFIG_PREFIX = "spring.activemq";

    //@Value("${spring:activemq:broker-url}")
    @Getter
    @Setter
    private String brokerUrl;
    @Getter
    @Setter
    private String user;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Boolean inMemory;
    @Getter
    @Setter
    private Boolean pooled;

    @Bean
    public ConnectionFactory createConnectionFactory(){
        log.info(user + " " + password + "============================= " + brokerUrl);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, brokerUrl);
        //connectionFactory.setUseAsyncSend();
        return connectionFactory;
    }

    /*
    可要可不要
    @Bean
    @Autowired
    public JmsMessagingTemplate createJmsMessagingTemplate(ConnectionFactory connectionFactory){
        JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(connectionFactory);
        return jmsMessagingTemplate;
    }*/


}
