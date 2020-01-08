package be.artisjaap.mail.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MailTemplateTO {

    private String code;
    private String htmlBody;
    private String subject;


}
