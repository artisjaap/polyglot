package be.artisjaap.document;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {
        DocumentbeheerApplication.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class DocumentbeheerInMemoryTestParent  {
}
