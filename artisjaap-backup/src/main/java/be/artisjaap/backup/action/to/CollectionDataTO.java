package be.artisjaap.backup.action.to;

import java.io.InputStream;

public class CollectionDataTO {
    public enum RestoreMode {
        DELETE_INSERT, INSERT_NEW, UPSERT
    }

    private String name;
    private InputStream data;
    private RestoreMode restoreMode = RestoreMode.DELETE_INSERT;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public RestoreMode getRestoreMode() {
        return restoreMode;
    }

    public void setRestoreMode(RestoreMode restoreMode) {
        this.restoreMode = restoreMode;
    }
}
