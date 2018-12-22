package be.artisjaap.polyglot.web.endpoints.request;

public class NewUserRequest {
    private String username;
    private String password;
    private String type;
    private String email;

    private NewUserRequest(){}

    private NewUserRequest(Builder builder) {
        username = builder.username;
        password = builder.password;
        type = builder.type;
        email = builder.email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static final class Builder {
        private String username;
        private String password;
        private String type;
        private String email;

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

        public Builder withEmail(String val) {
            email = val;
            return this;
        }


        public Builder withType(String val) {
            type = val;
            return this;
        }

        public NewUserRequest build() {
            return new NewUserRequest(this);
        }
    }
}
