package be.artisjaap.document.action;

import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.action.to.PaginaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateDocument {
    private final FindAvailableDocuments findAvailableDocuments;
    private final ValidateSimpleTemplate validateSimpleTemplate;
    private final ValidateCombinedTemplate validateCombinedTemplate;

    public ValidateDocument(FindAvailableDocuments findAvailableDocuments, ValidateSimpleTemplate validateSimpleTemplate, ValidateCombinedTemplate validateCombinedTemplate) {
        this.findAvailableDocuments = findAvailableDocuments;
        this.validateSimpleTemplate = validateSimpleTemplate;
        this.validateCombinedTemplate = validateCombinedTemplate;
    }

    public boolean metCodeInTaal(String briefCode, String taal) {
        return findAvailableDocuments.activeWithCodeAndLanguage(briefCode, taal)
                .filter(DocumentTO::getActief)
                .map(briefTO -> alleTemplatesActief(briefTO.getPaginas(), taal))
                .isPresent();
    }


    private boolean alleTemplatesActief(List<PaginaTO> paginas, String taal) {
        return !paginas.stream().map(p -> paginaActief(p, taal)).filter(b -> !b).findFirst().isPresent();
    }

    private boolean paginaActief(PaginaTO pagina, String taal) {
        switch (pagina.getType()) {
            case TEMPLATE:
                return validateSimpleTemplate.metCodeInTaal(pagina.getCode(), taal);
            case COMBINED:
                return validateCombinedTemplate.metCodeInTaal(pagina.getCode(), taal);
            case UNDEFINED:
                return false;
        }

        return false;
    }

}
