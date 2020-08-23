package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.backup.action.CollectionInfo;
import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.lesson.CreateLesson;
import be.artisjaap.polyglot.core.action.pairs.CreateLanguagePair;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.UpdateStatusTranslation;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import be.artisjaap.polyglot.core.model.ProgressStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUsers extends AbstractInitScript {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private CreateLanguagePair createLanguagePair;

    @Autowired
    private CreateTranslation createTranslation;




    @Autowired
    private UpdateStatusTranslation updateStatusTranslation;

    @Autowired
    private CollectionInfo collectionInfo;

    @Override
    public String getVersion() {
        return "DEMO";
    }

    @Override
    public String omschrijving() {
        return "Generate Users";
    }

    @Override
    public Integer cardinality() {
        return 1000;
    }

    @Override
    public void execute() {
        collectionInfo.allCollections();

        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername("stijn").withPassword("abc").withRole("STUDENT").build());
        logger.info("User created with id: " + userTO.id());




    }
}
