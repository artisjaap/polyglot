package be.artisjaap.document.cucumber;

import be.artisjaap.common.cucumber.AbstractWorld;
import be.artisjaap.common.cucumber.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class DocumentWorld extends AbstractWorld {


}
