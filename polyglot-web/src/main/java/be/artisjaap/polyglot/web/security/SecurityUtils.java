package be.artisjaap.polyglot.web.security;

import be.artisjaap.common.validation.ValidationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created by stijn on 20/01/18.
 */
public class SecurityUtils {

    public static Optional<UserDetails> findSessionData() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.empty();
        }
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (UserDetails.class.isAssignableFrom(details.getClass())) {
            return Optional.of((UserDetails) details);
        }
        return Optional.empty();
    }

    public static String userId(){
        return findSessionData().map(UserDetails::getUserId).orElseThrow(() -> new ValidationException("Unauthorized"));
    }
}
