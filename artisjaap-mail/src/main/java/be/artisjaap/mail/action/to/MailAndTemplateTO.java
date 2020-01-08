package be.artisjaap.mail.action.to;

import be.artisjaap.mail.model.Attachment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
public class MailAndTemplateTO {
    private String templateCode;
    private String from;
    private String to;
    private String username;
    private String password;
    private List<Attachment> attachments;
    private Object model;
}
