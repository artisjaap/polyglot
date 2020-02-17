package be.artisjaap.polyglot.cucumber.types;


public class LanguagePairType {
    private String from;
    private String to;

    private LanguagePairType(Builder builder) {
        setFrom(builder.from);
        setTo(builder.to);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public static final class Builder {
        private String from;
        private String to;

        private Builder() {
        }

        public Builder from(String val) {
            from = val;
            return this;
        }

        public Builder to(String val) {
            to = val;
            return this;
        }

        public LanguagePairType build() {
            return new LanguagePairType(this);
        }
    }
}
