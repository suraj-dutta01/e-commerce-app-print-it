package com.printit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.printit.dto.EmailConfiguration;
@Service
public class PrintitApiMailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendMail(EmailConfiguration emailConfiguration) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(emailConfiguration.getToAddress());
		simpleMailMessage.setText(emailConfiguration.getText());
		simpleMailMessage.setSubject(emailConfiguration.getSubject());
		javaMailSender.send(simpleMailMessage);
		return "Registration Successfull and Verification Mail has been send";
	}
}
