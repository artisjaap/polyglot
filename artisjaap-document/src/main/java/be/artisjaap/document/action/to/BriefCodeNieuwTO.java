package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BriefCodeNieuwTO {
    private final String code;
    private final String description;


}
