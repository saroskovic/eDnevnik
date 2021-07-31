package com.iktpreobuka.eDnevnik.services;

import com.iktpreobuka.eDnevnik.models.EmailObject;

public interface EmailService {

	void sendSimpleMailMessage(EmailObject object);
	void sendTemplateMessage(EmailObject object) throws Exception;
	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;
}
