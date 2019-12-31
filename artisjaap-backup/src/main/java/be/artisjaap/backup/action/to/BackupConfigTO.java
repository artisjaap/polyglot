package be.artisjaap.backup.action.to;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;


import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class BackupConfigTO {

    @Singular("aConfig")
    private List<BackupCollectionConfigTO> config;



}
