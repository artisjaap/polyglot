package be.artisjaap.mail.model;


import be.artisjaap.common.model.AbstractDocument;

public class Mailing extends AbstractDocument {

	private static final long serialVersionUID = 1L;

	public enum Status {
        PENDING, SUCCESS, FAILED, DISABLED
	};

	private String failReason;
	private String from;
	private String to;
	private String subject;
	private String body;
	private Status status = Status.PENDING;
	private byte[] attachement;

	public String getFrom() {
		return from;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public void sendSuccess() {
		status = Status.SUCCESS;
	}

	public void sendDisabled() {
		status = Status.DISABLED;
	}
	
	public String getFailReason() {
		return failReason;
	}
	
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public void sendFailed(String reason) {
		this.failReason = reason;
		status = Status.FAILED;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public byte[] getAttachement() {
		return attachement;
	}

	public void setAttachement(byte[] attachement) {
		this.attachement = attachement;
	}

}
