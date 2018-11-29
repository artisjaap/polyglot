package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.UserTO;

public class UserDataSet {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String classRoom;

    private UserDataSet(Builder builder) {
        username = builder.username;
        firstname = builder.firstname;
        lastname = builder.lastname;
        email = builder.email;
        classRoom = builder.classRoom;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static UserDataSet from(UserTO userTO) {
        return UserDataSet.newBuilder()
                .withClassRoom(userTO.classRoom())
                .withEmail(userTO.email())
                .withFirstname(userTO.firstName())
                .withLastname(userTO.lastName())
                .withUsername(userTO.username())
                .build();
    }

    public static UserDataSet dummy() {
        return UserDataSet.newBuilder()
                .withUsername("username")
                .withFirstname("first name")
                .withLastname("last name")
                .withClassRoom("Classroom")
                .withEmail("dummy@mail.no")
                .build();
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public static final class Builder {
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String classRoom;

        private Builder() {
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withFirstname(String val) {
            firstname = val;
            return this;
        }

        public Builder withLastname(String val) {
            lastname = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withClassRoom(String val) {
            classRoom = val;
            return this;
        }

        public UserDataSet build() {
            return new UserDataSet(this);
        }
    }
}
