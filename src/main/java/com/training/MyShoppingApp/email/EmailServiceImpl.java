/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.email;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	Configuration fmConfiguration;

	@Override
	@Async
	public void sendSimpleMail(EmailDetails details) {

		try {

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setSubject(details.getSubject());
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			details.setMsgBody(geContentFromTemplate(details.getModel()));
			mimeMessageHelper.setText(details.getMsgBody(), true);
			javaMailSender.send(mimeMessageHelper.getMimeMessage());

		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public String geContentFromTemplate(Map<String, Object> usersInfo) {

		StringBuffer content = new StringBuffer();

		try {

			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
					fmConfiguration.getTemplate("email-template.flth"),
					usersInfo));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

}
