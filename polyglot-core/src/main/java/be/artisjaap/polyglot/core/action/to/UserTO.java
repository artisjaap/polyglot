package be.artisjaap.polyglot.core.action.to;

public class UserTO extends ReferenceableTO{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserSettingsTO userSettings;

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

    public UserSettingsTO userSettings() {
        return userSettings;
    }

    private UserTO(Builder builder) {
        buildCommon(builder);
        username = builder.username;
        password = builder.password;
        firstName = builder.firstName;
        lastName = builder.lastName;
        userSettings = builder.userSettings;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private UserSettingsTO userSettings;

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

        public Builder withUserSettings(UserSettingsTO userSettings) {
            this.userSettings = userSettings;
            return this;
        }

        public UserTO build() {
            return new UserTO(this);
        }
    }



}
