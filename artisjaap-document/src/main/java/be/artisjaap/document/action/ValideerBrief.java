package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefTO;
import be.artisjaap.document.action.to.PaginaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValideerBrief {
    @Autowired
    private ZoekBeschikbareBrieven zoekBeschikbareBrieven;

    @Autowired
    private ValideerEnkelvoudigeTemplate valideerEnkelvoudigeTemplate;

    @Autowired
    private ValideerMeervoudigeTemplate valideerMeervoudigeTemplate;

    public boolean metCodeInTaal(String briefCode, String taal) {
        return zoekBeschikbareBrieven.actiefMetCodeInTaal(briefCode, taal)
                .filter(BriefTO::getActief)
                .map(briefTO -> alleTemplatesActief(briefTO.getPaginas(), taal))
                .isPresent();
    }


    private boolean alleTemplatesActief(List<PaginaTO> paginas, String taal) {
        return !paginas.stream().map(p -> paginaActief(p, taal)).filter(b -> !b).findFirst().isPresent();
    }

    private boolean paginaActief(PaginaTO pagina, String taal) {
        switch (pagina.getType()) {
            case TEMPLATE:
                return valideerEnkelvoudigeTemplate.metCodeInTaal(pagina.getCode(), taal);
            case GECOMBINEERD:
                return valideerMeervoudigeTemplate.metCodeInTaal(pagina.getCode(), taal);
            case UNDEFINED:
                return false;
        }

        return false;
    }

}
