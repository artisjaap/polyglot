package be.artisjaap.polyglot.web.endpoints.request;

public class NewUserRequest {
    private String firstName;
    private String lastName;


    private String username;
    private String password;
    private String type;
    private String email;

    private NewUserRequest(){}

    private NewUserRequest(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
        password = builder.password;
        type = builder.type;
        email = builder.email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
        private String firstName;
        private String lastName;

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

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }
    }
}
