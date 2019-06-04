package be.artisjaap.backup.action.to;

public class BackupCollectionConfigTO {
    private String name;
    private Boolean clearAfterBackup = false;

    public BackupCollectionConfigTO(String collection, boolean clearCollection) {
        this.name = collection;
        this.clearAfterBackup = clearCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getClearAfterBackup() {
        return clearAfterBackup;
    }

    public void setClearAfterBackup(Boolean clearAfterBackup) {
        this.clearAfterBackup = clearAfterBackup;
    }
}
