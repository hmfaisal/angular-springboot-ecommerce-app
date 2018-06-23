package com.basketbird.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service("emailService")
public class EmailService {
	
	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
	   this.mailSender = mailSender;
	}
	  
	@Async
	public void sendEmail(SimpleMailMessage email) {
	  mailSender.send(email);
	}

}
