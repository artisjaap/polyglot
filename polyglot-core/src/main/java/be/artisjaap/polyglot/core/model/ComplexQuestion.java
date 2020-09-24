package be.artisjaap.polyglot.core.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ComplexQuestion {
    @Builder.Default
    private List<String> questionAnswer = new ArrayList<>();
}
