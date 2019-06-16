package be.artisjaap.document.api.brieflocatie;

public class AbsolutePathLocation implements BriefOpslagLocatie {


    private String path;

    private AbsolutePathLocation(Builder builder) {
        path = builder.path;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPath() {
        return path;
    }

    @Override
    public BriefLocatieType briefLocatieType() {
        return BriefLocatieType.ABSOLUTE_PATH;
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

        public AbsolutePathLocation build() {
            return new AbsolutePathLocation(this);
        }
    }
}
