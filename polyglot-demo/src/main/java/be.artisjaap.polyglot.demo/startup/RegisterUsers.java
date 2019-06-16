package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.user.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUsers extends AbstractInitScript {
    @Autowired
    private RegisterUser registerUser;

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
        registerUser.newUser(NewUserTO.newBuilder().withUsername("stijn").withPassword("abc").withRole("STUDENT").build());

    }
}
