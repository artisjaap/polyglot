package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.UserTO;

public class UserLoginResponse {
    private String userId;
    private String username;
    private String password;
    private String token;

    private UserLoginResponse(Builder builder) {
        userId = builder.userId;
        username = builder.username;
        password = builder.password;
        token = builder.token;
    }


    public static UserLoginResponse from(UserTO userTO, String token) {
        return newBuilder().withUserId(userTO.id())
                .withUsername(userTO.username())
                .withToken(token)
                .build();

    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String username;
        private String password;
        private String token;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
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

        public UserLoginResponse build() {
            return new UserLoginResponse(this);
        }
    }
}
