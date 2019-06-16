package be.artisjaap.document.action;

import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.mongo.BriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeactivateDocument {

    @Autowired
    private BriefRepository briefRepository;

    public void metCodeAndTaal(String code, String taal) {
        Optional<Brief> briefOptional = briefRepository.findByCodeAndTaalAndActief(code, taal);
        briefOptional.ifPresent(brief -> {
            brief.desactiveer();
            briefRepository.save(brief);
        });

    }
}
