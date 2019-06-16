package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LessonHeaderTO;

import java.util.List;
import java.util.stream.Collectors;

public class LessonHeaderResponse {
    private String id;
    private String name;

    private LessonHeaderResponse(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public static LessonHeaderResponse from(LessonHeaderTO lessonHeader){
        return newBuilder().withId(lessonHeader.id())
                .withName(lessonHeader.name())
                .build();
    }

    public static List<LessonHeaderResponse> from(List<LessonHeaderTO> lessonHeaderTOList) {
        return lessonHeaderTOList.stream()
                .map(LessonHeaderResponse::from)
                .collect(Collectors.toList());

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public LessonHeaderResponse build() {
            return new LessonHeaderResponse(this);
        }
    }
}
