package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.User;
import io.codearte.jfairy.producer.person.Person;

public class UserMother extends AbstractMother {

    public static User.Builder initRandom() {
        Person person = fairy.person();

        return User.newBuilder()
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withUsername(person.getUsername())
                ;
    }
}
