package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@Data
public class NewLessonTO {
    private String name;
    private String userId;
    private String languagePairId;

    @Builder.Default
    private List<String> translationsIds = new ArrayList<>();
    @Builder.Default
    private Set<String> tags = new HashSet<>();


}
