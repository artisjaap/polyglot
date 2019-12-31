package be.artisjaap.backup.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectionInfoTO {
    private String collectionName;
    private Long size;
    private Boolean capped;


}
