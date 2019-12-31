package be.artisjaap.document.action.to;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DocumentNewTO {
    private String code;
    private List<String> templates;
    private String taal;

}
