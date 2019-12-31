package be.artisjaap.document.action.to;


import be.artisjaap.document.model.PaginaType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaginaTO {

    private PaginaType type;
    private String code;


}
