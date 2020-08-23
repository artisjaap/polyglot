package be.artisjaap.crossword.action.to;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CrosswordWordTO {
    private String word;
    private String description;
}
