package be.artisjaap.crossword;

import be.artisjaap.crossword.CrosswordApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = {
        CrosswordApplication.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class CrosswordInMemoryTestParent {
    
    
}
