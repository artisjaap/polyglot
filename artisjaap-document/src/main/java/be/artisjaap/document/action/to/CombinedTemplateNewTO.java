package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CombinedTemplateNewTO {
    private String taal;
    private List<String> templates;
    private String code;


}
