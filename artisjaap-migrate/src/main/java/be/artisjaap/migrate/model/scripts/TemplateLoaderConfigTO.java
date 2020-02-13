package be.artisjaap.migrate.model.scripts;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateLoaderConfigTO {
    private String templateCode;
    private String documentPath;
    private String description;
}
