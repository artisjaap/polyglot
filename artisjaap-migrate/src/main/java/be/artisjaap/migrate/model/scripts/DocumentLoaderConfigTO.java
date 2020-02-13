package be.artisjaap.migrate.model.scripts;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Builder
@Data
public class DocumentLoaderConfigTO {
    @NonNull
    private String documentCode;
    @NonNull
    private String description;
    @NonNull
    private String language;
    @NonNull
    private List<TemplateLoaderConfigTO> templates;
}

