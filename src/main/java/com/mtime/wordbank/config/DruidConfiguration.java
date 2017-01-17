package com.mtime.wordbank.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {

    @Autowired
    private DruidDataSourceProp druidDataSourceProp;
    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidDataSourceProp.getDriverClassName());
        druidDataSource.setUrl(druidDataSourceProp.getUrl());
        druidDataSource.setUsername(druidDataSourceProp.getUsername());
        druidDataSource.setPassword(druidDataSourceProp.getPassword());

        try {
            if(StringUtils.isNotBlank(druidDataSourceProp.getName())){
                druidDataSource.setName(druidDataSourceProp.getName());
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getFilters())){
                druidDataSource.setFilters(druidDataSourceProp.getFilters());
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getMaxActive())){
                druidDataSource.setMaxActive(Integer.parseInt(druidDataSourceProp.getMaxActive()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getInitialSize())){
                druidDataSource.setInitialSize(Integer.parseInt(druidDataSourceProp.getInitialSize()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getMinIdle())){
                druidDataSource.setMinIdle(Integer.parseInt(druidDataSourceProp.getMinIdle()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getMaxWait())){
                druidDataSource.setMaxWait(Integer.parseInt(druidDataSourceProp.getMaxWait()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getTimeBetweenEvictionRunsMillis())){
                druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(druidDataSourceProp.getTimeBetweenEvictionRunsMillis()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getMinEvictableIdleTimeMillis())){
                druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong((druidDataSourceProp.getMinEvictableIdleTimeMillis())));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getValidationQuery())){
                druidDataSource.setValidationQuery(druidDataSourceProp.getValidationQuery());
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getTestWhileIdle())){
                druidDataSource.setTestWhileIdle(Boolean.parseBoolean(druidDataSourceProp.getTestWhileIdle()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getTestOnBorrow())){
                druidDataSource.setTestOnBorrow(Boolean.parseBoolean(druidDataSourceProp.getTestOnBorrow()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getTestOnReturn())){
                druidDataSource.setTestOnReturn(Boolean.parseBoolean(druidDataSourceProp.getTestOnReturn()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getPoolPreparedStatements())){
                druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(druidDataSourceProp.getPoolPreparedStatements()));
            }
            if(StringUtils.isNotBlank(druidDataSourceProp.getMaxOpenPreparedStatements())){
                druidDataSource.setMaxOpenPreparedStatements(Integer.parseInt(druidDataSourceProp.getMaxOpenPreparedStatements()));
            }

            //druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
