package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.ProgressStatus;

public class ProgressStatusMother extends AbstractMother{

    public static ProgressStatus random(){
        ProgressStatus[] values = ProgressStatus.values();
        return values[fairy.baseProducer().randomBetween(0, values.length-1)];
    }
}
