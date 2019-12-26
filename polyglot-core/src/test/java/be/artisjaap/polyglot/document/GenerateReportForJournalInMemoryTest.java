package be.artisjaap.polyglot.document;

import be.artisjaap.polyglot.core.action.documents.GenerateReportForJournal;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.Translation;
import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.testhelper.LanguagePairPersister;
import be.artisjaap.polyglot.testhelper.TranslationPersister;
import be.artisjaap.polyglot.testhelper.TranslationPracticePersister;
import be.artisjaap.polyglot.testhelper.UserPersister;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class GenerateReportForJournalInMemoryTest  extends InMemoryTest {

    @Autowired
    private GenerateReportForJournal generateReportForJournal;

    @Autowired
    private LanguagePairPersister languagePairPersister;

    @Autowired
    private TranslationPracticePersister translationPracticePersister;

    @Autowired
    private TranslationPersister translationPersister;

    @Autowired
    private UserPersister userPersister;

    @Test
    public void test() throws IOException {
        LanguagePair languagePair = languagePairPersister.randomLanguagePair();
        Translation translation = translationPersister.randomTranslationForLanguagePair(languagePair);
        User user = userPersister.randomUser();

        Optional<byte[]> bytes = generateReportForJournal.withData(LanguagePracticeJournalTO.newBuilder()
                .withLanguagePairId(languagePair.getId().toString())
                .withUserId(user.getId().toString())
                .withTranslationJournalList(Arrays.asList(TranslationJournalTO.newBuilder()
                        .withTranslationId(translation.getId().toString())
                        .withQuestion(translation.getLanguageA())
                        .withGivenAnswer(translation.getLanguageB())
                        .withExpectedAnswer(translation.getLanguageB())
                        .withCorrect(false)
                        .build(),TranslationJournalTO.newBuilder()
                                .withTranslationId(translation.getId().toString())
                                .withQuestion(translation.getLanguageA())
                                .withGivenAnswer(translation.getLanguageB())
                                .withExpectedAnswer(translation.getLanguageB())
                                .withCorrect(true)
                                .build())
                        )
                .build());
        FileUtils.writeByteArrayToFile(new File("c:/temp/test.pdf"), bytes.get());

    }
}
