package be.artisjaap.backup.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BackupCollectionConfigTO {
    private String name;
    @Builder.Default
    private Boolean clearAfterBackup = false;
}
