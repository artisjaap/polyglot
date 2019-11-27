package be.artisjaap.polyglot.web.endpoints.old.response;

public interface ResponseForTO<TO, R> {

    R from(TO to);
}
