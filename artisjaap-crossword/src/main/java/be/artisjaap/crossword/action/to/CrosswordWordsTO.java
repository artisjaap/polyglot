package be.artisjaap.crossword.action.to;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class CrosswordWordsTO {
    @Builder.Default
    private List<CrosswordWordTO> words = new ArrayList<>();
    
}
