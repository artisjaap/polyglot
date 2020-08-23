package be.artisjaap.crossword.crosswordengine;

import java.util.ArrayList;
import java.util.List;

public class GenerationContext {
    private List<WordContextPosition> solutionContext = new ArrayList<>(); 
    
    private GenerationContext(){
    }
    
    public final static GenerationContext create(){
        return new GenerationContext();
    }
    
    public WordContextPosition addWordPosition(String word, List<StartPosition> availableStartPositions){
        StartPosition currentPosition = availableStartPositions.remove(0);
        WordContextPosition newWordContextPosition = WordContextPosition.create(word, availableStartPositions, currentPosition);
        solutionContext.add(newWordContextPosition);
        return newWordContextPosition;
    }
    
    private WordContextPosition lastAddedWord() {
        return solutionContext.get(solutionContext.size() - 1);
    }

    public void positionsFilledWithCurrentWord(List<PositionalCrossTile> positionalCrossTiles) {
        lastAddedWord().addPositionsFilled(positionalCrossTiles);
    }

    public boolean hasOtherPositionsToTry() {
        return !lastAddedWord().getPossiblePossitionsToTry().isEmpty();
    }

    public NewWordPositionInfo newPositionForWord() {
        WordContextPosition wordContextPosition = lastAddedWord();
        
        if(!wordContextPosition.getPossiblePossitionsToTry().isEmpty()){
            List<PositionalCrossTile> positionsFilled = wordContextPosition.getPositionsFilled();
            StartPosition newPosition = wordContextPosition.tryOtherPosition();
            return NewWordPositionInfo.create(wordContextPosition.getWord(), positionsFilled, newPosition, new ArrayList<>());
        }

        throw new UnsupportedOperationException("Not yet possible");
    }
}
