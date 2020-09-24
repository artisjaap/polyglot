package be.artisjaap.polyglot.core.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ComplexLesson {
    @Builder.Default
    private List<ComplexQuestion> questions = new ArrayList<>();

    @Builder.Default
    private List<QuestionRule> rules = new ArrayList<>();
}
