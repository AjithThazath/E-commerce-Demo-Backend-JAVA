/**
	@Authored By Ajith Thazath 
	Created for Demo project
**/
package com.training.MyShoppingApp.email;

import java.util.Map;

public class EmailDetails {
	private String recipient;
	private String msgBody;
	private String subject;
	private Map<String, Object> model;
	// private String attachment;
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	// public String getAttachment() {
	// return attachment;
	// }
	// public void setAttachment(String attachment) {
	// this.attachment = attachment;
	// }
	public EmailDetails(String recipient, String subject,
			Map<String, Object> model) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.model = model;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
