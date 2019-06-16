package be.artisjaap.document.api.brieflocatie;

public class RelativePathLocation implements BriefOpslagLocatie {

    private String path;

    private RelativePathLocation(Builder builder) {
        path = builder.path;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public BriefLocatieType briefLocatieType() {
        return BriefLocatieType.RELATIVE_PATH;
    }

    @Override
    public String path() {
        return path;
    }

    public static final class Builder {
        private String path;

        private Builder() {
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public RelativePathLocation build() {
            return new RelativePathLocation(this);
        }
    }
}
