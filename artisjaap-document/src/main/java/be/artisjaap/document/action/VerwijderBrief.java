package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.BriefRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerwijderBrief {

    @Autowired
    private BriefRepository briefRepository;

    public void metId(String id) {
        briefRepository.deleteById(new ObjectId(id));
    }
}
