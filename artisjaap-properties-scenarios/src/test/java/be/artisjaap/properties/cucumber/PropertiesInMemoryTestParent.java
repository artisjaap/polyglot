package be.artisjaap.properties.cucumber;

import be.artisjaap.properties.PropertiesApplication;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
        PropertiesApplication.class})
public class PropertiesInMemoryTestParent {
}
