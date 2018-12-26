package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.UserTO;

import java.util.Set;

public class UserLoginResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String token;
    private Set<String> roles;
    private String preferedRole;


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getPreferedRole() {
        return preferedRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private UserLoginResponse(Builder builder) {
        userId = builder.userId;
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
        password = builder.password;
        token = builder.token;
        roles = builder.roles;
        preferedRole = builder.preferedRole;
    }


    public static UserLoginResponse from(UserTO userTO, String token) {
        return newBuilder().withUserId(userTO.id())
                .withUsername(userTO.username())
                .withFirstName(userTO.firstName())
                .withLastName(userTO.lastName())
                .withToken(token)
                .withRoles(userTO.roles())
                .withPreferedRole(userTO.preferedRole())
                .build();

    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String token;
        private Set<String> roles;
        private String preferedRole;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withToken(String val) {
            token = val;
            return this;
        }

        public Builder withRoles(Set<String> val) {
            roles = val;
            return this;
        }

        public Builder withPreferedRole(String val) {
            preferedRole = val;
            return this;
        }

        public UserLoginResponse build() {
            return new UserLoginResponse(this);
        }
    }
}
