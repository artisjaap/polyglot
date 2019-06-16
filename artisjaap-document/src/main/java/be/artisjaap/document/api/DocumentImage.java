package be.artisjaap.document.api;

public class DocumentImage {
    private String name;
    private byte[] image;

    private DocumentImage(Builder builder) {
        name = builder.name;
        image = builder.image;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }


    public static final class Builder {
        private String name;
        private byte[] image;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withImage(byte[] image) {
            this.image = image;
            return this;
        }

        public DocumentImage build() {
            return new DocumentImage(this);
        }
    }
}
