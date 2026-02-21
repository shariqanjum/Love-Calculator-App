package com.shariq.lc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class LCAppEmailServiceimpl implements LCAppEmailService {
	
	@Autowired
	private JavaMailSender javaMailSenderImpl;
	
	@Override
	public void sendEmail(String userName, String userEmail, String result) {
		
		SimpleMailMessage newEmail= new SimpleMailMessage();
		newEmail.setTo(userEmail);
		newEmail.setSubject("Love Calculator Application Result");
		newEmail.setText("Hi " +  userName + "/n The Result predicted by the LCApp is " + result);
		
		javaMailSenderImpl.send(newEmail);
	}

}
