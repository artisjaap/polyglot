package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateLoaderConfigTO {
    private String templateCode;
    private String documentPath;
    private String description;
}
