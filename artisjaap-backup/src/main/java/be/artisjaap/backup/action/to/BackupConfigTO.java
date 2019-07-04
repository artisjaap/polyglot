package be.artisjaap.backup.action.to;

import java.util.ArrayList;
import java.util.List;

public class BackupConfigTO {

    private final List<BackupCollectionConfigTO> config;

    private BackupConfigTO(Builder builder) {
        config = builder.config;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<BackupCollectionConfigTO> getBackupCollectionConfigs() {
        return config;
    }


    public static final class Builder {
        private List<BackupCollectionConfigTO> config = new ArrayList<>();

        private Builder() {
        }

        public Builder withConfig(List<BackupCollectionConfigTO> val) {
            config = val;
            return this;
        }

        public Builder addConfig(BackupCollectionConfigTO val) {
            config.add(val);
            return this;
        }

        public BackupConfigTO build() {
            return new BackupConfigTO(this);
        }
    }
}
