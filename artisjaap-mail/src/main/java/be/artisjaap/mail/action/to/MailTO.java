package be.artisjaap.mail.action.to;

import be.artisjaap.mail.model.Attachment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder(toBuilder = true)
@Data
public class MailTO {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String username;
    private String password;
    private List<Attachment> attachments;


}
