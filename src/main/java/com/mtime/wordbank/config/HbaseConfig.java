package com.mtime.wordbank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Mtime on 2016/5/3.
 */
@Configuration
@ImportResource("classpath:/hbase/spring-hbase.xml")
public class HbaseConfig {
}
