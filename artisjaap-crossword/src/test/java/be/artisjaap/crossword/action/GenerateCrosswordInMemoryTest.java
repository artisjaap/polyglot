package be.artisjaap.crossword.action;

import be.artisjaap.crossword.CrosswordInMemoryTestParent;
import be.artisjaap.crossword.action.to.CrosswordTO;
import be.artisjaap.crossword.action.to.CrosswordWordTO;
import be.artisjaap.crossword.action.to.CrosswordWordsTO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


public class GenerateCrosswordInMemoryTest extends CrosswordInMemoryTestParent {
    @Resource
    private GenerateCrossword generateCrossword;

    @Test
    public void generateCrossword(){
        CrosswordTO crosswordTO = generateCrossword.forListOfWords(CrosswordWordsTO.builder()
                .words(List.of(
                        CrosswordWordTO.builder().word("AAA").build(),
                        CrosswordWordTO.builder().word("BBC").build(),
                        CrosswordWordTO.builder().word("DD").build(),
                        CrosswordWordTO.builder().word("ABD").build(),
                        CrosswordWordTO.builder().word("ACD").build()
                ))
                .build());
        
        crosswordTO.prettyPrint();


    }

}

//-- AAA
//-- BBC
//-- #DD