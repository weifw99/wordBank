package com.mtime.wordbank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan
public class Application{
	private Logger log = LoggerFactory.getLogger(Application.class);

	public Application() {
		log.info("自动启动SpringBootWebApplication应用程序。。。");
	}

	public static void main(String[] args) {
		/*SpringApplication springApplication =new SpringApplication(Application.class);
		//添加注册ApplicationListener
		springApplication.addListeners(new ApplicationStartup());
		springApplication.run(args);*/

		SpringApplication.run(Application.class, args);
	}
}
