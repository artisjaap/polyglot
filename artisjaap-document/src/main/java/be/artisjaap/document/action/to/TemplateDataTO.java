package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Builder
@Data
public class TemplateDataTO {

    private String code;
    private byte[] data;
    private Set<ObjectId> gebruikteTemplates;

    public static TemplateDataTO empty() {
        return builder().build();
    }


}
