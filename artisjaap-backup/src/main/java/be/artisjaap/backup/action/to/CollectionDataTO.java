package be.artisjaap.backup.action.to;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Builder
@Data
public class CollectionDataTO {
    public enum RestoreMode {
        DELETE_INSERT, INSERT_NEW, UPSERT
    }

    private String name;
    private InputStream data;
    @Builder.Default
    private RestoreMode restoreMode = RestoreMode.DELETE_INSERT;


}
