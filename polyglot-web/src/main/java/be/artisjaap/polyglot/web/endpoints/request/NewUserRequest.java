package be.artisjaap.polyglot.web.endpoints.request;

public class NewUserRequest {
    private String username;
    private String password;

    private NewUserRequest(){}

    private NewUserRequest(Builder builder) {
        username = builder.username;
        password = builder.password;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public static final class Builder {
        private String username;
        private String password;

        private Builder() {
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public NewUserRequest build() {
            return new NewUserRequest(this);
        }
    }
}
