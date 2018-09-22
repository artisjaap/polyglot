package be.artisjaap.polyglot.core.action.to;

public class NewUserTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    private NewUserTO(Builder builder) {
        username = builder.username;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public NewUserTO build() {
            return new NewUserTO(this);
        }
    }
}
