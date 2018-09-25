package be.artisjaap.polyglot.web.security;

/**
 * Created by stijn on 20/01/18.
 */
public class UserDetails {

    public Integer userId;

    public Integer getUserId() {
        return userId;
    }

    private UserDetails(Builder builder) {
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer userId;

        private Builder() {
        }

        public Builder withUserId(Integer val) {
            userId = val;
            return this;
        }

        public UserDetails build() {
            return new UserDetails(this);
        }
    }
}
