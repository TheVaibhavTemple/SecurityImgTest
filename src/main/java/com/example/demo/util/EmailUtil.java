package com.example.demo.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;
	
	public boolean send(
			String to,
			String cc[],
			String bcc[],
			String subject,
			String text)  
	{
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);// create empty object of helper
			
			helper.setTo(to);//--setting the data
			helper.setCc(cc);
			helper.setBcc(bcc);
			
			helper.setSubject(subject);
			
			helper.setText(text, true);//true for allow html presentation  <b>hello</b>
			
			sender.send(message);
			
		} catch (Exception e) {
			
		}
		return false;
		
	}
	
	public boolean send(
			String to,
			String subject,
			String text)  
	{
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);// create empty object of helper
			
			helper.setTo(to);//--setting the data
			
			helper.setSubject(subject);
			
			helper.setText(text, true);//true for allow html presentation  <b>hello</b>
			
			sender.send(message);
			
		} catch (Exception e) {
			
		}
		return false;
		
	}
}
