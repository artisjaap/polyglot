package be.artisjaap.crossword.crosswordengine;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WordContextPosition {
    private String word;
    private List<StartPosition> possiblePossitionsToTry;
    private StartPosition currentPosition;
    private List<PositionalCrossTile> positionsFilled = new ArrayList<>();

    private WordContextPosition(String word, List<StartPosition> possiblePossitionsToTry, StartPosition currentPosition){
        this.word = word;
        this.possiblePossitionsToTry = possiblePossitionsToTry;
        this.currentPosition = currentPosition;
    }

    public static WordContextPosition create(String word, List<StartPosition> availableStartPositions, StartPosition currentPosition) {
        return new WordContextPosition(word, availableStartPositions, currentPosition);
    }


    public void addPositionsFilled(List<PositionalCrossTile> positionsFilled){
        this.positionsFilled.addAll(positionsFilled);
    }

    public StartPosition tryOtherPosition() {
        currentPosition = possiblePossitionsToTry.remove(0);
        positionsFilled.clear();
        return currentPosition;
    }
}
