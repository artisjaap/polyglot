package be.artisjaap.polyglot.cucumber.types;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LanguagePairType {
    private String from;
    private String to;
}
