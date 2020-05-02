package be.artisjaap.crossword.crosswordengine;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CrosswordEngine {

    private CrosswordField crosswordField;
    private WordProvider wordProvider;

    private CrosswordEngine(List<String> words) {
        crosswordField = CrosswordField.init();
        wordProvider = WordProvider.initWithWords(words);
    }
    
    public static CrosswordEngine initForWords(List<String> words) {
        return new CrosswordEngine(words);
    }
    
    public void findSolutionImproved(){
        
        GenerationContext context = GenerationContext.create();
        
        while(!wordProvider.allWordsUsed()){
            crosswordField.printGrid();

            if(wordProvider.allWordsAvailable()){
                String word = wordProvider.firstUnuseWord().get();
                log.info("The first word to be placed is: " + word);
                ArrayList<StartPosition> start = new ArrayList<>();
                start.add(StartPosition.center());
                WordContextPosition wordContextPosition = context.addWordPosition(word, start);
                List<PositionalCrossTile> positionalCrossTiles = crosswordField.putWord(word, wordContextPosition.getCurrentPosition());
                context.positionsFilledWithCurrentWord(positionalCrossTiles);
                continue;
            }else {
                List<String> chars = crosswordField.connectableStrings();
                log.info("There is already one or more word on the board and the connectable characters are " + chars.stream().collect(Collectors.joining(",")));
                
                if(!chars.isEmpty()){
                    Optional<String> word = wordProvider.firstUnuseWordContaining(chars);    
                    
                    if(word.isPresent()){
                        log.info("The next word that can be placed is be placed is: " + word.get());
                        List<StartPosition> allStartPositions = crosswordField.findAllStartPositions(word.get());
                        log.info("This word can be placed on {} positions: {}",  allStartPositions.size(), allStartPositions.stream().map(Objects::toString).collect(Collectors.joining(";")));
                        WordContextPosition wordContextPosition = context.addWordPosition(word.get(), allStartPositions);
                        log.info("This word will be placed on position {}", wordContextPosition.getCurrentPosition());
                        List<PositionalCrossTile> positionalCrossTiles = crosswordField.putWord(word.get(), wordContextPosition.getCurrentPosition());
                        context.positionsFilledWithCurrentWord(positionalCrossTiles);
//                        if(!isValid()) {
//                            log.info("after placing word {} the bord is no longer valid, remove it again...", word.get());
//                            crosswordField.blankOutPositions(positionalCrossTiles);
//                        }
                        continue;
                    } else {
                        log.info("no word found that fits on open position... backtrack...");  
                        if(context.hasOtherPositionsToTry()){
                            NewWordPositionInfo newWordPositionInfo = context.newPositionForWord();
                            crosswordField.blankOutPositions(newWordPositionInfo.getPositionsToBlank());
                            //FIXME words to pu back in wordProvider
                            List<PositionalCrossTile> positionalCrossTiles = crosswordField.putWord(newWordPositionInfo.getWord(), newWordPositionInfo.getNewStartPosition());
                            context.positionsFilledWithCurrentWord(positionalCrossTiles);
//                            if(!isValid()) {
//                                crosswordField.blankOutPositions(positionalCrossTiles);
//                            }
                            continue;
                            
                        }
                        
                    }
                    
                } else {
                    log.info("there are no positions left and there are still {} words left to put on the bord", wordProvider.countWordsUnused());
                }
                
            }
            
            break;
            
        }

        crosswordField.printGrid();

    }

    private boolean isValid() {
        return !crosswordField.connectableStrings().stream()
                .filter(str -> !wordProvider.wordAvailableFor(str))
                .findFirst()
                .isPresent();
    }

    public String findSolution() {
        Optional<String> string = wordProvider.firstUnuseWord();
        if(string.isPresent()){

            String st = string.get();
            crosswordField.putWord(st, StartPosition.create(0, 0, Direction.HORIZONTAL));
            List<String> chars = crosswordField.connectableStrings();
            Optional<String> word = wordProvider.firstUnuseWordContaining(chars);
            List<StartPosition> allPossiblePossitions = crosswordField.findAllStartPositions(word.get());

            StartPosition startPosition = allPossiblePossitions.remove(0);
            crosswordField.putWord(word.get(), startPosition);

            chars = crosswordField.connectableStrings();
            word = wordProvider.firstUnuseWordContaining(chars);
            allPossiblePossitions = crosswordField.findAllStartPositions(word.get());
            startPosition = allPossiblePossitions.remove(0);
            crosswordField.putWord(word.get(), startPosition);

            Set<String> strings = crosswordField.connectableCharacters();
            List<String> strings2 = crosswordField.connectableStrings();
            System.out.println(string);
            
            

        }

        crosswordField.printGrid();
        return "";
    }
    
    
}
