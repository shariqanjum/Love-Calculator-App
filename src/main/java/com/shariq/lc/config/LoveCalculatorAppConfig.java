package com.shariq.lc.config;

import java.util.Properties;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.shariq.lc.formatter.PhoneNumberFormatter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.shariq.lc"})
@PropertySource("classpath:email.properties")
public class LoveCalculatorAppConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	
	Logger logger = Logger.getLogger(LoveCalculatorAppConfig.class.getName());
	
	//setup my viewResolver
	@Bean
	public InternalResourceViewResolver viewResolver() {
		
	
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/view/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
	
}
	@Bean
	public MessageSource messageSource() { 
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource( messageSource());
		return localValidatorFactoryBean;
		}
	
		@Bean
		public JavaMailSender configureJavaMailSender() {
			
			logger.info("Fetching " + env.getProperty("mail.host"));

			JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
			javaMailSenderImpl.setHost(env.getProperty("mail.host"));
			javaMailSenderImpl.setUsername(env.getProperty("mail.userName"));
			javaMailSenderImpl.setPassword(env.getProperty("mail.password"));
			javaMailSenderImpl.setPort(getIntProperty("mail.port"));
			javaMailSenderImpl.setJavaMailProperties(getMailProperty());

			return javaMailSenderImpl;

		}
		
		private Properties getMailProperty() {
			
			Properties mailProperties = new Properties();
			mailProperties.put("mail.smtp.starttls.enable", true);
			mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			return mailProperties;
		}
		
		int getIntProperty(String key) {

			String property = env.getProperty(key);

			return Integer.parseInt(property);
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {

			System.out.println("inside addformatters method");

			registry.addFormatter(new PhoneNumberFormatter());
		}

		@Override
		public Validator getValidator() {

			return validator();
		}
	}
