package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.NewTranslationAssembler;
import be.artisjaap.polyglot.core.action.to.NewTranslationForUserTO;
import be.artisjaap.polyglot.core.model.Translation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewTranslationForUserAssembler  {

    @Autowired
    private NewTranslationAssembler newTranslationAssembler;

    public List<Translation> assembleAllEntities(NewTranslationForUserTO to){
        return to.translations().stream().map(t -> newTranslationAssembler.assembleEntity(t, new ObjectId(to.languagePairId()))).collect(Collectors.toList());
    }
}
