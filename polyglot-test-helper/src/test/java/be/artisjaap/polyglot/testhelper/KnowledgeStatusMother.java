package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.KnowledgeStatus;

public class KnowledgeStatusMother extends AbstractMother {

    public static KnowledgeStatus random(){
        KnowledgeStatus[] values = KnowledgeStatus.values();
        return values[fairy.baseProducer().randomBetween(0, values.length-1)];
    }
}
