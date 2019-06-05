package be.artisjaap.backup.action.to;

public class BackupCollectionConfigTO {
    private String name;
    private Boolean clearAfterBackup = false;

    private BackupCollectionConfigTO(Builder builder) {
        name = builder.name;
        clearAfterBackup = builder.clearAfterBackup;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private Boolean clearAfterBackup;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withClearAfterBackup(Boolean val) {
            clearAfterBackup = val;
            return this;
        }

        public BackupCollectionConfigTO build() {
            return new BackupCollectionConfigTO(this);
        }
    }
}
