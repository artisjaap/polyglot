package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateCodeTO {

    private String code;
    private String description;

}
