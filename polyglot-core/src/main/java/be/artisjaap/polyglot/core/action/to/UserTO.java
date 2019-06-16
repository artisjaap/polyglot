package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.model.ReferenceableTO;

import java.util.Set;

public class UserTO extends ReferenceableTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String classRoom;
    private String preferedRole;
    private Set<String> roles;
    private UserSettingsTO userSettings;

    public Set<String> roles() {
        return roles;
    }

    public String preferedRole() {
        return preferedRole;
    }

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

    public String email() {
        return email;
    }

    public String classRoom() {
        return classRoom;
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
        email = builder.email;
        classRoom = builder.classRoom;
        preferedRole = builder.preferedRole;
        roles = builder.roles;
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
        private String email;
        private String classRoom;
        private String preferedRole;
        private Set<String> roles;
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

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withClassRoom(String classRoom) {
            this.classRoom = classRoom;
            return this;
        }

        public Builder withPreferedRole(String val) {
            preferedRole = val;
            return this;
        }

        public Builder withRoles(Set<String> val) {
            roles = val;
            return this;
        }

        public UserTO build() {
            return new UserTO(this);
        }
    }



}
