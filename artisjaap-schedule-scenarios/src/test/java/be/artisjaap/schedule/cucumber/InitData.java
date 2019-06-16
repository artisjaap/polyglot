package be.artisjaap.schedule.cucumber;

import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import org.springframework.stereotype.Component;

@Component
public class InitData extends AbstractInitScript {


    @Override
    public String getVersion() {
        return "0.2";
    }

    @Override
    public String omschrijving() {
        return "Verhoog versienummer";
    }

    @Override
    public Integer cardinality() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void execute() {
        //System.out.println(Context.userId());

    }
}
