package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.entity.VerificationToken;
import com.exception.EmailFailureException;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${email.from}")
	private String fromAddress;
	@Value("${app.frontend.url}")
	private String url;
	
	private SimpleMailMessage makeMailMessage() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromAddress);
		return simpleMailMessage;
	}
	
	public void sendVerificationEmail(VerificationToken verificationToken)throws EmailFailureException {
		SimpleMailMessage message = makeMailMessage();
		message.setTo(verificationToken.getUser().getEmail());
		message.setSubject("Verify your email to activate your account.");
		message.setText("Please follow the link below to verify your email to activate your account.\n"+url+"/auth/verify?token"+verificationToken.getToken());
	try {
		javaMailSender.send(message);
	}catch(MailException e) {
		throw new EmailFailureException();
	}
	
	
	}

}
