package be.artisjaap.crossword.crosswordengine;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrosswordField {


    private DynamicGrid dynamicGrid;

    

    private CrosswordField(){
        this.dynamicGrid = DynamicGrid.createGrid();
    }
    

    public static CrosswordField init() {
        return new CrosswordField();
    }

    public List<PositionalCrossTile> putWord(String word, StartPosition position) {
        int startRow = position.direction() == Direction.HORIZONTAL ? position.row()   : position.row() - 1;
        int startColumn = position.direction() == Direction.HORIZONTAL ? position.column() - 1 : position.column();


        PutWordStats allFree = putWordOnPosition(word, startRow, startColumn, position, true);
        if(allFree.isCanBePut()){
            putWordOnPosition(word, startRow, startColumn, position, false);
        }
        
        return allFree.getTilesFiled();
    }
    
    private PutWordStats putWordOnPosition(String word, int startRow, int startColumn, StartPosition position, boolean testPosition){
        String[] split = word.split("(?!^)");
        List<PositionalCrossTile> tilesToBeSet = new ArrayList<>();
        if(testPosition){
            boolean fits = true;
            if(position.direction() == Direction.HORIZONTAL){
               fits = fits && dynamicGrid.testPosition(CrossTile.createBlack(), startRow, startColumn, tilesToBeSet);
                fits = fits && dynamicGrid.testPosition(CrossTile.createBlack(), startRow, startColumn+word.length()+1, tilesToBeSet);

                for (int i = 0; i < split.length; i++) {
                    fits = fits && dynamicGrid.testPosition(CrossTile.createWithCharacter(split[i]), startRow, startColumn+i+1, tilesToBeSet);
                }

            }else {
                fits = fits && dynamicGrid.testPosition(CrossTile.createBlack(), startRow, startColumn, tilesToBeSet);
                fits = fits && dynamicGrid.testPosition(CrossTile.createBlack(), startRow + word.length() + 1, startColumn, tilesToBeSet);

                for (int i = 0; i < split.length; i++) {
                    fits = fits && dynamicGrid.testPosition(CrossTile.createWithCharacter(split[i]), startRow+i+1, startColumn, tilesToBeSet);
                }

            }

            return PutWordStats.create(fits, tilesToBeSet);
        }
        else {
            
        
            if(position.direction() == Direction.HORIZONTAL){
                dynamicGrid.putOnPosition(CrossTile.createBlack(), startRow, startColumn);
                dynamicGrid.putOnPosition(CrossTile.createBlack(), startRow, startColumn+word.length()+1);
    
                for (int i = 0; i < split.length; i++) {
                    dynamicGrid.putOnPosition(CrossTile.createWithCharacter(split[i]), startRow, startColumn+i+1);
                }
    
            }else {
                dynamicGrid.putOnPosition(CrossTile.createBlack(), startRow, startColumn);
                dynamicGrid.putOnPosition(CrossTile.createBlack(), startRow + word.length() + 1, startColumn);
    
                for (int i = 0; i < split.length; i++) {
                    dynamicGrid.putOnPosition(CrossTile.createWithCharacter(split[i]), startRow+i+1, startColumn);
                }
    
            }
        }
        return PutWordStats.create(true, tilesToBeSet);
    }

    public List<StartPosition> findAllStartPositions(String newWord) {
        return dynamicGrid.connectableCharactersForWord(newWord);
    }

    private List<String> newWordsToTest(String word, StartPosition position) {
        return null;
    }
    
    public void printGrid() {
        dynamicGrid.prettyPrint();
        
    }


    public Set<String> connectableCharacters() {
        return dynamicGrid.connectableCharacters().stream()
                .map(PositionalCrossTile::crossTile)
                .map(CrossTile::containgCharacter)
                .collect(Collectors.toSet());
    }
    
    public List<String> connectableStrings(){
        return dynamicGrid.allConnectableString().stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
    }

    public void blankOutPositions(List<PositionalCrossTile> positionsToBlank) {
        positionsToBlank.forEach(positionalCrossTile -> dynamicGrid.putOnPosition(CrossTile.createEmpty(), positionalCrossTile.row(), positionalCrossTile.column()));
    }
}
