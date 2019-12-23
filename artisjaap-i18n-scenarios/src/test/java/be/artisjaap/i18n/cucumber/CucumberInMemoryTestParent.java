package be.artisjaap.i18n.cucumber;

import be.artisjaap.common.CommonApplication;
import be.artisjaap.i18n.I18nApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {I18nApplication.class, CommonApplication.class})
public abstract class CucumberInMemoryTestParent {
}
