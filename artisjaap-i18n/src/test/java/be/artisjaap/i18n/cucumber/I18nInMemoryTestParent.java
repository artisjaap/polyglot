package be.artisjaap.i18n.cucumber;

import be.artisjaap.i18n.I18nApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        I18nApplication.class})
public abstract class I18nInMemoryTestParent {
}
