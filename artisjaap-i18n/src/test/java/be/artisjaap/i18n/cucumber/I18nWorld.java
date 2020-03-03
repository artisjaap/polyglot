package be.artisjaap.i18n.cucumber;

import be.artisjaap.common.cucumber.AbstractWorld;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Component
public class I18nWorld extends AbstractWorld {

}
