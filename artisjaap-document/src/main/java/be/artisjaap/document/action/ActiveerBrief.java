package be.artisjaap.document.action;

import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.mongo.BriefRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveerBrief {

    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private DesactiveerBrief desactiveerBrief;

    public void metId(String id){
        Optional<Brief> briefOptional = briefRepository.findById(new ObjectId(id));
        briefOptional.ifPresent(
                brief -> {
                    desactiveerBrief.metCodeAndTaal(brief.getCode(), brief.getTaal());
                    brief.activeer();
                    briefRepository.save(brief);
                }
        );
    }
}
