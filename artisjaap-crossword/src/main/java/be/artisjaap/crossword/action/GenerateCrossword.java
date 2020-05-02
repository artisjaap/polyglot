package be.artisjaap.crossword.action;

import be.artisjaap.crossword.action.to.CrosswordTO;
import be.artisjaap.crossword.action.to.CrosswordWordTO;
import be.artisjaap.crossword.action.to.CrosswordWordsTO;
import be.artisjaap.crossword.crosswordengine.CrosswordEngine;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenerateCrossword {
    public CrosswordTO forListOfWords(CrosswordWordsTO words){
        CrosswordEngine crosswordEngine = CrosswordEngine.initForWords(words.getWords().stream().map(CrosswordWordTO::getWord).collect(Collectors.toList()));
        crosswordEngine.findSolutionImproved();
        return CrosswordTO.builder().build();
    }
}
