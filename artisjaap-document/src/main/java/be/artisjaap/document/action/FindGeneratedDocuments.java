package be.artisjaap.document.action;

import be.artisjaap.document.action.to.GegenereerdeBriefInfoTO;
import be.artisjaap.document.assembler.GegenereerdeBriefInfoAssembler;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindGeneratedDocuments {

    private final GegenereerdeBriefRepository gegenereerdeBriefRepository;
    private final GegenereerdeBriefInfoAssembler gegenereerdeBriefInfoAssembler;

    public FindGeneratedDocuments(GegenereerdeBriefRepository gegenereerdeBriefRepository, GegenereerdeBriefInfoAssembler gegenereerdeBriefInfoAssembler) {
        this.gegenereerdeBriefRepository = gegenereerdeBriefRepository;
        this.gegenereerdeBriefInfoAssembler = gegenereerdeBriefInfoAssembler;
    }

    public List<GegenereerdeBriefInfoTO> forDocumentInCodeAndLanguage(String code, String language) {
        return gegenereerdeBriefInfoAssembler.assembleTO(gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, language));
    }
}
