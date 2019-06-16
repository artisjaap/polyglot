package be.artisjaap.polyglot.testhelper;

import io.codearte.jfairy.Fairy;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class AbstractMother {
    protected static Fairy fairy = Fairy.create();

    public static LocalDateTime from(DateTime dateTime){
        return dateTime.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
