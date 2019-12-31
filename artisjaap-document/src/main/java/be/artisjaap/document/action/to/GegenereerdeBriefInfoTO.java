package be.artisjaap.document.action.to;

import be.artisjaap.document.api.brieflocatie.BriefLocatieType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GegenereerdeBriefInfoTO {
    private String id;
    private String taal;
    private String filename;
    private BriefLocatieType briefLocatieType;
    private String briefCode;


}
