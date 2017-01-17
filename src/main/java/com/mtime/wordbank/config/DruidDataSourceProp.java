package com.mtime.wordbank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by Mtime on 2016/2/22.
 */
@Configuration
@ConfigurationProperties(prefix = DruidDataSourceProp.DRUID_DATASOURCE_PREFIX)
public class DruidDataSourceProp {
    public static final String DRUID_DATASOURCE_PREFIX = "spring.datasource";

    /*
    name: wordbank
        url: jdbc:mysql://localhost:3306/wordbank
        username: root
        password: root
        driver-class-name: com.mysql.jdbc.Driver
        # 使用druid数据源
        #type: com.alibaba.druid.pool.DruidDataSource
        #配置监控统计拦截的filters，去掉后监控界面sql无法统计
        filters: stat
        #配置初始化大小、最小、最大
        maxActive: 20
        initialSize: 1
        minIdle: 1
        #配置获取连接等待超时的时间
        maxWait: 60000
        #配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        timeBetweenEvictionRunsMillis: 60000
        #配置一个连接在池中最小生存的时间，单位是毫秒
        minEvictableIdleTimeMillis: 300000
        validationQuery: select 'x'
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        #打开PSCache，并且指定每个连接上PSCache的大小
        poolPreparedStatements: true
        maxOpenPreparedStatements: 20
     */

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String driverClassName;
    @Getter
    @Setter
    private String filters;
    @Getter
    @Setter
    private String maxActive;
    @Getter
    @Setter
    private String initialSize;
    @Getter
    @Setter
    private String minIdle;
    @Getter
    @Setter
    private String maxWait;
    @Getter
    @Setter
    private String timeBetweenEvictionRunsMillis;
    @Getter
    @Setter
    private String minEvictableIdleTimeMillis;
    @Getter
    @Setter
    private String validationQuery;
    @Getter
    @Setter
    private String testWhileIdle;
    @Getter
    @Setter
    private String testOnBorrow;
    @Getter
    @Setter
    private String testOnReturn;
    @Getter
    @Setter
    private String poolPreparedStatements;
    @Getter
    @Setter
    private String maxOpenPreparedStatements;

}
