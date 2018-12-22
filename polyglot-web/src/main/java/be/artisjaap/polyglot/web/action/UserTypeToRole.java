package be.artisjaap.polyglot.web.action;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserTypeToRole {
    public Set<String> forType(String type){
        switch(type){
            case "STUDENT":
                return Collections.singleton("ROLE_STUDENT");
            case "TEACHER":
                return Collections.singleton("ROLE_TEACHER");
            case "ADMIN":
                return Collections.singleton("ROLE_ADMIN");
        }
        return new HashSet<>();
    }
}
