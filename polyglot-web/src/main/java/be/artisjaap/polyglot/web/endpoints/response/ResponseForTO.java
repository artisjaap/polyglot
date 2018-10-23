package be.artisjaap.polyglot.web.endpoints.response;

public interface ResponseForTO<TO, R> {

    R from(TO to);
}
