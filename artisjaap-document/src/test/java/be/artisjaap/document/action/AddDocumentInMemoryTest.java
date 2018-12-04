package be.artisjaap.document.action;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.persisters.BriefPersister;
import org.junit.Test;

import javax.annotation.Resource;

public class AddDocumentInMemoryTest extends DocumentbeheerInMemoryTestParent {

    @Resource
    private BriefPersister briefPersister;



    @Test
    public void maakEenBrief() {
        briefPersister.maakEenBrief("TEST_BRIEF");


    }
}
