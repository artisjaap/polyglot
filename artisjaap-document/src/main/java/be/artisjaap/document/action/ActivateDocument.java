package be.artisjaap.document.action;

import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.mongo.BriefRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivateDocument {

    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private DeactivateDocument deactivateDocument;

    public void metId(String id) {
        Optional<Brief> briefOptional = briefRepository.findById(new ObjectId(id));
        briefOptional.ifPresent(
                brief -> {
                    deactivateDocument.metCodeAndTaal(brief.getCode(), brief.getTaal());
                    brief.activeer();
                    briefRepository.save(brief);
                }
        );
    }
}
