package be.artisjaap.polyglot.core.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class LanguagePairMistakes {

    private ObjectId userId;
    private ObjectId languagePairId;
    private LocalDateTime from;
    private LocalDateTime to;
    @Builder.Default
    private List<MistakeDetail> mistakes = new ArrayList<>();
}
