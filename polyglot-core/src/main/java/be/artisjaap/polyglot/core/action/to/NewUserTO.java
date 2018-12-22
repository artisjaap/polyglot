package be.artisjaap.polyglot.core.action.to;

import java.util.Set;

public class NewUserTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;

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

    public Set<String> roles() {
        return roles;
    }

    public String email() {
        return email;
    }

    private NewUserTO(Builder builder) {
        username = builder.username;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
        roles = builder.roles;
        email = builder.email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private Set<String> roles;

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
        public Builder withRoles(Set<String> roles) {
            this.roles = roles;
            return this;
        }
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public NewUserTO build() {
            return new NewUserTO(this);
        }
    }
}
