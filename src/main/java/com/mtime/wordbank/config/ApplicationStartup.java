package com.mtime.wordbank.config;

import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by Mtime on 2016/2/18.
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ApplicationStartup.onApplicationEvent");
    }
}