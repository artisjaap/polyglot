package be.artisjaap.polyglot.web.action;

import be.artisjaap.polyglot.core.action.FindUser;
import be.artisjaap.polyglot.core.action.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticateForUser {
    @Autowired
    private FindUser findUser;

    public Optional<UserTO> byUsernameAndPassword(String username, String password) {
        return findUser.byUsernameAndPassword(username, password);
    }
}
