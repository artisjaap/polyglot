package be.artisjaap.document.action.to;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class DocumentTO {

    private String code;
    private String type;
    private List<PaginaTO> paginas;
    private String taal;
    private String referentie;
    private String id;
    private Boolean actief;
    private LocalDateTime aangemaaktOp;


}
