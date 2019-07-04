package be.artisjaap.backup.action.to;

public class CollectionInfoTO {
    private String collectionName;
    private Long size;
    private Boolean capped;

    private CollectionInfoTO(Builder builder) {
        this.collectionName = builder.collectionName;
        this.size = builder.size;
        this.capped = builder.capped;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getCapped() {
        return capped;
    }

    public void setCapped(Boolean capped) {
        this.capped = capped;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String collectionName;
        private Boolean capped;
        private Long size;

        private Builder() {
        }

        public Builder withCapped(Boolean val) {
            this.capped = val;
            return this;
        }

        public Builder withCollectionName(String val) {
            this.collectionName = val;
            return this;
        }

        public Builder withSize(Long val) {
            this.size = val;
            return this;
        }

        public CollectionInfoTO build() {
            return new CollectionInfoTO(this);
        }
    }
}
