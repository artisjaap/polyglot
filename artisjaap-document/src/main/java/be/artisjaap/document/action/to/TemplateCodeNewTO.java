package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TemplateCodeNewTO {

    private String code;
    private String description;


}
