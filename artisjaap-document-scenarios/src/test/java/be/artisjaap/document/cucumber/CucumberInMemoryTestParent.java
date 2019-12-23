package be.artisjaap.document.cucumber;

import be.artisjaap.common.CommonApplication;
import be.artisjaap.document.DocumentbeheerApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {DocumentbeheerApplication.class, CommonApplication.class})
public abstract class CucumberInMemoryTestParent {
}
