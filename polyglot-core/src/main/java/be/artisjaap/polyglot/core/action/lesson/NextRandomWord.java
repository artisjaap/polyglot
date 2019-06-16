package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;

public interface NextRandomWord {

    PracticeWordTO nextWord(String userId, String languagePairId, OrderType orderType);
}
