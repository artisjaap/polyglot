package be.artisjaap.polyglot.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by stijn on 20/01/18.
 */
public class JwtUser  implements UserDetails{
    private String username;
    private String password;
    private String userId;

    private JwtUser(Builder builder) {
        username = builder.username;
        password = builder.password;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public static final class Builder {
        private String username;
        private String password;
        private String userId;

        private Builder() {
        }

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public JwtUser build() {
            return new JwtUser(this);
        }
    }
}
