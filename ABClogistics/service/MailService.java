package com.alpha.ABClogistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
     
	@Autowired
	JavaMailSender javaMailSender;

	public void sendmail(String tomail, String subject, String content) {
		
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setFrom("saigoudkasani234@gmail.com");
		
		message.setTo(tomail);
		
		message.setSubject(subject);
		
		message.setText(content);
		
		javaMailSender.send(message);
		
	}
	
}
