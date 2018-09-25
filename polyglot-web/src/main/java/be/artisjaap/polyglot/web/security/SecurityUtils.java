package be.artisjaap.polyglot.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created by stijn on 20/01/18.
 */
public class SecurityUtils {

    public static Optional<UserDetails> findSessionData() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (UserDetails.class.isAssignableFrom(details.getClass())) {
            return Optional.of((UserDetails) details);
        }
        return Optional.empty();
    }
}
