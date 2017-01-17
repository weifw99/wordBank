package com.mtime.wordbank.config;

import com.mtime.wordbank.filter.MyFilter;
import com.mtime.wordbank.filter.MyFilter1;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mtime on 2016/2/18.
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/");
    }

    @Bean
    public ConfigurableWebBindingInitializer configurableWebBindingInitializer(){
        ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
        return bindingInitializer;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean1(MyFilter1 myFilter1){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter1);
        filterRegistrationBean.setEnabled(false);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(MyFilter myFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setEnabled(false);//当前过滤器是否有效
        filterRegistrationBean.addUrlPatterns("/test/*");
        return filterRegistrationBean;
    }

}
