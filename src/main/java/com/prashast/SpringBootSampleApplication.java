package com.prashast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


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


	/*
	https://stackoverflow.com/questions/30170586/how-to-disable-errorpagefilter-in-spring-boot
	https://stackoverflow.com/questions/42292946/vaadin-spring-boot-cannot-forward-to-error-page-for-request
	https://gist.github.com/jonikarppinen/6ade2554946df21db0a6
	https://github.com/spring-projects/spring-boot/issues/2745
	 */

	@Bean
	public ErrorPageFilter errorPageFilter() {
		return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

}
