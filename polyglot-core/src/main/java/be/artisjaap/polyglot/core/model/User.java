package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "PolUser")
public class User extends AbstractDocument {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private UserSettings userSettings;
    private String email;

    public String getEmail() {
        return email;
    }

    private User(){}

    public Set<String> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    private User(Builder builder) {
        username = builder.username;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
        roles = builder.roles;
        userSettings = builder.userSettings;
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
        private Set<String> roles;
        private UserSettings userSettings = UserSettings.buildDefaults();
        private String email;

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


        public Builder withUserSettings(UserSettings userSettings) {
            this.userSettings = userSettings;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
