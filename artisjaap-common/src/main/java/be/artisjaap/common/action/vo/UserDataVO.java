package be.artisjaap.common.action.vo;

import be.artisjaap.common.utils.LocalDateUtils;

import java.time.LocalDateTime;

public class UserDataVO {
    private final String userId;
    private final LocalDateTime activeSince;

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getActiveSince() {
        return activeSince;
    }

    private UserDataVO(Builder builder) {
        userId = builder.userId;
        activeSince = builder.activeSince;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private LocalDateTime activeSince = LocalDateUtils.now();

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }


        public UserDataVO build() {
            return new UserDataVO(this);
        }
    }
}
