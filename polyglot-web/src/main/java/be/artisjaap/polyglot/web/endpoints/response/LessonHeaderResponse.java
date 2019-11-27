package be.artisjaap.polyglot.web.endpoints.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonHeaderResponse {
    private String id;
    private String name;
}
