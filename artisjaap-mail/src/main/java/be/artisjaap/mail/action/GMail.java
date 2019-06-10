package be.artisjaap.mail.action;

import be.artisjaap.mail.action.to.MailTO;
import be.artisjaap.mail.model.Attachment;
import be.artisjaap.mail.model.Mailing;
import be.artisjaap.mail.model.mongo.MailingRepository;
import be.artisjaap.properties.action.GetProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;

@Component
class GMail {
    private final static Logger LOGGER = LogManager.getLogger();

	@Autowired
	private MailingRepository mailingRepository;
	
	@Autowired
	private GetProperty getProperty;

	private static Properties props = new Properties();
	static {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

	public void sendAMail(MailTO mailTO) {
	    if(mailTO.username() == null ||
            mailTO.password() == null){
            mailTO = MailTO.newBuilder(mailTO)
                    .withUsername(getProperty.withKey("mailing.username"))
                    .withPassword(getProperty.withKey("mailing.password")).build();
        }

		doSendAMail(mailTO);
	}

	private void doSendAMail(MailTO mailTO) {

		final Session session = createSession(mailTO.username(), mailTO.password());

		Runnable run = new Runnable() {

			@Override
			public void run() {
				Mailing mailing = new Mailing();
				mailing.setFrom(mailTO.from());
				mailing.setTo(mailTO.to());
				mailing.setSubject(mailTO.subject());
				mailing.setBody(mailTO.body());
				mailingRepository.save(mailing);

				try {
					Boolean mailingEnabled = getProperty.withKey("mailing.enabled");
					
					if(mailingEnabled){
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(mailTO.from()));
						message.setRecipients(Message.RecipientType.TO,
								InternetAddress.parse(mailTO.to()));
						
	
						message.setSubject(mailTO.subject());
	
						BodyPart messageBody = new MimeBodyPart();
						messageBody.setContent(mailTO.body(), "text/html; charset=utf-8");
	
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBody);
	
						for (Attachment atm : mailTO.attachments()) {
							MimeBodyPart attachment = new MimeBodyPart();
							DataSource source = new ByteArrayDataSource(
									atm.getInputstream(),  atm.getFiletype());
							attachment.setDataHandler(new DataHandler(source));
							attachment.setFileName(atm.getFilename());
							multipart.addBodyPart(attachment);
						}
	
						message.setContent(multipart);
	
						LOGGER.info("Sending mail to " + mailTO.to() + " with subject: "
								+ mailTO.subject());
						
						Transport.send(message);
						mailing.sendSuccess();
						LOGGER.info("Sending mail done");
					}else {
						mailing.sendDisabled();
					}
					
					
					mailingRepository.save(mailing);


				} catch ( Exception e) {
					LOGGER.info("Failed to send mail to " + mailTO.to()
							+ " with subject: " + mailTO.subject() + ". ["
							+ e.getMessage() + "]", e);
					mailing.sendFailed(e.getMessage());
					mailingRepository.save(mailing);
				}

			}
		};

		Thread t = new Thread(run);
		t.start();

	}

	private Session createSession(final String username, final String password) {
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}

}
