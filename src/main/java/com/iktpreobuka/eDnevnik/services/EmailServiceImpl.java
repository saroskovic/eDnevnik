package com.iktpreobuka.eDnevnik.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.eDnevnik.models.EmailObject;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendSimpleMailMessage(EmailObject object) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(object.getTo());
		message.setSubject(object.getSubject());
		message.setText(object.getText());
		emailSender.send(message);

	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "<html><body><table" + "style='border:2px solid black'>"
						+ "<tr><td>" + object.getText()	+ "</td></tr>" + "<?table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);
	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception {
		// TODO Auto-generated method stub

	}
}
