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
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;

@Component
class GMail {
    private final static Logger LOGGER = LogManager.getLogger();

    private final MailingRepository mailingRepository;

    private final GetProperty getProperty;

    private static Properties props = new Properties();

    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public GMail(MailingRepository mailingRepository, GetProperty getProperty) {
        this.mailingRepository = mailingRepository;
        this.getProperty = getProperty;
    }

    public void sendAMail(MailTO mailTO) {
        if (mailTO.getUsername() == null ||
                mailTO.getPassword() == null) {
            mailTO = mailTO.toBuilder()
                    .username(getProperty.valueForKey("mailing.username"))
                    .password(getProperty.valueForKey("mailing.password")).build();
        }

        doSendAMail(mailTO);
    }

    private void doSendAMail(MailTO mailTO) {

        final Session session = createSession(mailTO.getUsername(), mailTO.getPassword());

        Runnable run = new Runnable() {

            @Override
            public void run() {
                Mailing mailing = new Mailing();
                mailing.setFrom(mailTO.getFrom());
                mailing.setTo(mailTO.getTo());
                mailing.setSubject(mailTO.getSubject());
                mailing.setBody(mailTO.getBody());
                mailingRepository.save(mailing);

                try {
                    Boolean mailingEnabled = getProperty.valueForKeyAsBoolean("mailing.enabled");

                    if (mailingEnabled) {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(mailTO.getFrom()));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(mailTO.getTo()));


                        message.setSubject(mailTO.getSubject());

                        BodyPart messageBody = new MimeBodyPart();
                        messageBody.setContent(mailTO.getBody(), "text/html; charset=utf-8");

                        Multipart multipart = new MimeMultipart();
                        multipart.addBodyPart(messageBody);

                        for (Attachment atm : mailTO.getAttachments()) {
                            MimeBodyPart attachment = new MimeBodyPart();
                            DataSource source = new ByteArrayDataSource(
                                    atm.getInputstream(), atm.getFiletype());
                            attachment.setDataHandler(new DataHandler(source));
                            attachment.setFileName(atm.getFilename());
                            multipart.addBodyPart(attachment);
                        }

                        message.setContent(multipart);

                        LOGGER.info("Sending mail to " + mailTO.getTo() + " with subject: "
                                + mailTO.getSubject());

                        Transport.send(message);
                        mailing.sendSuccess();
                        LOGGER.info("Sending mail done");
                    } else {
                        mailing.sendDisabled();
                    }


                    mailingRepository.save(mailing);


                } catch (Exception e) {
                    LOGGER.info("Failed to send mail to " + mailTO.getTo()
                            + " with subject: " + mailTO.getSubject() + ". ["
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
