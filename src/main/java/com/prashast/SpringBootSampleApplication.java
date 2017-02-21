package com.prashast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/*
disable auto security configuration: http://stackoverflow.com/questions/30761253/remove-using-default-security-password-on-spring-boot
else your log file shows "Using default security password: " and unsecured resources will need authentication too if spring security has not been configured.
 */

@SpringBootApplication
public class SpringBootSampleApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootSampleApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
