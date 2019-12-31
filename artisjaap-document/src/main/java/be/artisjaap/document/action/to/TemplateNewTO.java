package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateNewTO {
    private String code;
    private String originalFilename;
    private byte[] template;
    private String taal;


}
