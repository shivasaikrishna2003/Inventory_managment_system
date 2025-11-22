package com.alpha.ABClogistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABClogistics.service.MailService;

@RestController
public class MailController {

	@Autowired
	MailService mailService;
	
	@PostMapping("/sendmail")
	public void sendmail() {
		String tomail="shivasaikasani@gmail.com";
		String subject="This is normal mail";
		String content="This is dummy mail please ignore";
		
		mailService.sendmail(tomail,subject,content);
	}
}
