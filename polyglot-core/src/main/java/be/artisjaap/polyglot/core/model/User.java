package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;

public class User extends AbstractDocument {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserSettings userSettings;

    private User(){}

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
        userSettings = builder.userSettings;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private UserSettings userSettings = UserSettings.buildDefaults();

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

        public Builder withUserSettings(UserSettings userSettings) {
            this.userSettings = userSettings;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
