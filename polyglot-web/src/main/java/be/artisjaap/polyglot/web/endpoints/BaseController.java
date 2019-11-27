package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.web.security.SecurityUtils;

public abstract class BaseController {

    protected String userId() {
        return SecurityUtils.userId();
    }
}
