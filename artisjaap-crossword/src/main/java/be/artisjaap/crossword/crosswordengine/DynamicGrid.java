package be.artisjaap.crossword.crosswordengine;

import javax.crypto.spec.OAEPParameterSpec;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DynamicGrid {
    private int minRow = 0;
    private int maxRow = 1;
    private int minCol = 0;
    private int maxCol = 1;
    
    
    
    private List<List<CrossTile>> dynamicGrid;
    
    private DynamicGrid(){
        dynamicGrid = new ArrayList<>();
        ArrayList<CrossTile> row = new ArrayList<>();
        dynamicGrid.add(row);
        row.add(CrossTile.createEmpty());
    }
    
    public final static DynamicGrid createGrid() {
        return new DynamicGrid();
    }
    
    public void addRowTop() {
        minRow--;    
        dynamicGrid.add(0, createEmptyRow());
    }
    
    public void addRowBottom() {
        maxRow++;
        dynamicGrid.add(createEmptyRow());
    }
    
    public void addColumnLeft() {
        minCol--;
        dynamicGrid.forEach(row -> row.add(0, CrossTile.createEmpty()));
    }
    
    public void addColumRight(){
        maxCol++;
        dynamicGrid.forEach(row -> row.add(CrossTile.createEmpty()));
    }
    
    public int width(){
        return maxCol - minCol;
    }
    
    public int height(){
        return maxRow - minRow;
    }
    
    public void putOnPosition(CrossTile tile, int row, int col){
        expandGridIfNeeded(row, col);

        onLocation(row, col).update(tile);
        
    }
    
    public boolean testPosition(CrossTile newTile, int row, int col, List<PositionalCrossTile> tilesToBeSet){
        CrossTile currentTile = onLocation(row, col);
        return newTileFitsOverCurrent(currentTile, newTile, tilesToBeSet, row, col);
    }

    private boolean newTileFitsOverCurrent(CrossTile currentTile, CrossTile newTile, List<PositionalCrossTile> tilesToBeSet, int row, int col) {
        if(currentTile.crossTileType() == CrossTileType.WHITE){
            tilesToBeSet.add(PositionalCrossTile.forData(currentTile, row, col));
            return true;
        }
        if(currentTile.crossTileType() == CrossTileType.BLACK &&
          newTile.crossTileType() == CrossTileType.BLACK){
            return true;
        }
        return (currentTile.crossTileType() == CrossTileType.FILLED && newTile.crossTileType() == CrossTileType.FILLED &&
                currentTile.containgCharacter().equals(newTile.containgCharacter())); 
                
    }

    public CrossTile onLocation(int row, int col){
        expandGridIfNeeded(row, col);
        int rowIndex =  row - minRow ;
        int colIndex =  col - minCol ;

        return dynamicGrid.get(rowIndex).get(colIndex);
    }

    public List<CrossTile> row(int row){
        return dynamicGrid.get(row - minRow);
    }

    public List<CrossTile> column(int column){
        return dynamicGrid.stream().map(row -> row.get(column - minCol)).collect(Collectors.toList());
    }

    private void expandGridIfNeeded(int row, int col) {
        while(row < minRow) {
            addRowTop();
        }

        while(row > maxRow-1){
            addRowBottom();
        }

        while(col < minCol) {
            addColumnLeft();
        }

        while(col > maxCol-1) {
            addColumRight();
        }
    }

    private List<CrossTile> createEmptyRow() {
        return IntStream.rangeClosed(1, width()).mapToObj(i -> CrossTile.createEmpty()).collect(Collectors.toList());
    }
    
    public void prettyPrintRow(int row) {
        row(row).forEach(elm -> System.out.print(elm.print()));
    }

    public void prettyPrintColumn(int col) {
        column(col).forEach(elm -> System.out.println(elm.print()));
    }
    
    public void prettyPrint() {
        System.out.println("Size is: " + width() + " x " + height());
        dynamicGrid.forEach(row -> {
            row.forEach(elm -> {
                System.out.print(elm.print());
            });
            System.out.println();
        });
    }


    public boolean allFree(int row, int column, int length, Direction direction) {
        if(direction == Direction.HORIZONTAL){
            return IntStream.rangeClosed(column, column + length).mapToObj(i -> onLocation(row, i))
                    .filter(tile -> tile.crossTileType() != CrossTileType.WHITE).collect(Collectors.toList())
                    .isEmpty();
        }else {
           return  IntStream.rangeClosed(row, row + length).mapToObj(i -> onLocation(i, column))
                    .filter(tile -> tile.crossTileType() != CrossTileType.WHITE).collect(Collectors.toList())
                   .isEmpty();

        }
    }
    
    public List<PositionalCrossTileString> connectableStrings(){
        boolean startedWithWhite = true;

        PositionalCrossTileString.PositionalCrossTileStringBuilder positionalTileBuilder = PositionalCrossTileString.createEmpty();
        List<PositionalCrossTileString> words = new ArrayList<>();

        for(int i = minRow; i < maxRow; i++){
            
            for(int j = minCol; j < maxCol; j++){
                CrossTile crossTile = onLocation(i, j);

                if(crossTile.crossTileType() == CrossTileType.WHITE){
                    if(positionalTileBuilder.isComplete()){
                        words.add(positionalTileBuilder.create());
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }
                    startedWithWhite = true;
                }else if(crossTile.crossTileType() == CrossTileType.BLACK) {
                    if(startedWithWhite && positionalTileBuilder.isComplete()){
                        words.add(positionalTileBuilder.create());
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }else {
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }
                    startedWithWhite = false;
                }else {
                    positionalTileBuilder.addTile(PositionalCrossTile.forData(crossTile, i, j));
                    positionalTileBuilder.setHorizontal();
                }
            }
            if(positionalTileBuilder.isComplete()){
                words.add(positionalTileBuilder.create());
                positionalTileBuilder = PositionalCrossTileString.createEmpty();
            }
        }

        for(int j = minCol; j < maxCol; j++){
            positionalTileBuilder = PositionalCrossTileString.createEmpty();
            for(int i = minRow; i < maxRow; i++){
                CrossTile crossTile = onLocation(i, j);

                if(crossTile.crossTileType() == CrossTileType.WHITE){
                    if(positionalTileBuilder.isComplete()){
                        words.add(positionalTileBuilder.create());
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }
                    startedWithWhite = true;
                }else if(crossTile.crossTileType() == CrossTileType.BLACK) {
                    if(startedWithWhite && positionalTileBuilder.isComplete()){
                        words.add(positionalTileBuilder.create());
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }else {
                        positionalTileBuilder = PositionalCrossTileString.createEmpty();
                    }
                    startedWithWhite = false;
                }else {
                    positionalTileBuilder.addTile(PositionalCrossTile.forData(crossTile, i, j));
                    positionalTileBuilder.setVertical();
                }
            }
            if(positionalTileBuilder.isComplete()){
                words.add(positionalTileBuilder.create());
            }
        }

        return words;
        
    }
    
    public List<PositionalCrossTile> connectableCharacters() {
        return allCharacters().stream().filter(c -> connectable(c).isPresent()).collect(Collectors.toList());
    }

    public Optional<Direction> connectable(PositionalCrossTile positionalCrossTile) {
        if (onLocation(positionalCrossTile.row() - 1, positionalCrossTile.column()).crossTileType() == CrossTileType.WHITE &&
                onLocation(positionalCrossTile.row() + 1, positionalCrossTile.column()).crossTileType() == CrossTileType.WHITE) {
            return Optional.of(Direction.VERTICAL);
        }
        if(onLocation(positionalCrossTile.row(), positionalCrossTile.column() - 1).crossTileType() == CrossTileType.WHITE &&
                        onLocation(positionalCrossTile.row(), positionalCrossTile.column() + 1).crossTileType() == CrossTileType.WHITE){
            return Optional.of(Direction.HORIZONTAL);
        }
        return Optional.empty();
    }
    
    

    public List<PositionalCrossTile> allCharacters() {
        List<PositionalCrossTile> result = new ArrayList<>();
        
        for(int i = minRow; i < maxRow; i++){
            for(int j = minCol; j < maxCol; j++){
                CrossTile crossTile = onLocation(i, j);
                if(crossTile.crossTileType() == CrossTileType.FILLED){
                    result.add(PositionalCrossTile.forData(crossTile, i, j));
                }
            }
        }

        return result;
    }
    
    public List<String> allConnectableString(){
        boolean startedWithWhite = true;
        StringBuffer stringBuffer;
        List<String> words = new ArrayList<>();
        
        for(int i = minRow; i < maxRow; i++){
            stringBuffer = new StringBuffer();
            for(int j = minCol; j < maxCol; j++){
                CrossTile crossTile = onLocation(i, j);
                
                if(crossTile.crossTileType() == CrossTileType.WHITE){
                    if(stringBuffer.length() > 0){
                        words.add(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    }
                    startedWithWhite = true;
                }else if(crossTile.crossTileType() == CrossTileType.BLACK) {
                   
                    if(startedWithWhite && stringBuffer.length() > 0){
                        words.add(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    }else {
                        stringBuffer = new StringBuffer();
                    }
                    startedWithWhite = false;
                }else {
                    stringBuffer.append(crossTile.containgCharacter());
                }
            }
            if(stringBuffer.length() > 0){
                words.add(stringBuffer.toString());
                
            }
        }

        for(int j = minCol; j < maxCol; j++){
            stringBuffer = new StringBuffer();
            for(int i = minRow; i < maxRow; i++){
                CrossTile crossTile = onLocation(i, j);

                if(crossTile.crossTileType() == CrossTileType.WHITE){
                    if(stringBuffer.length() > 0){
                        words.add(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    }
                    startedWithWhite = true;
                }else if(crossTile.crossTileType() == CrossTileType.BLACK) {
                    if(startedWithWhite && stringBuffer.length() > 0){
                        words.add(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    } else {
                        stringBuffer = new StringBuffer();
                    }
                    startedWithWhite = false;
                }else  {
                    stringBuffer.append(crossTile.containgCharacter());
                }
            }
            if(stringBuffer.length() > 0){
                words.add(stringBuffer.toString());
            }
        }

        return words;

    }

    public List<StartPosition> connectableCharactersForWord(String newWord) {
          return connectableStrings().stream()
                  .flatMap(positionalCrossTileString -> calculateStartPositions(positionalCrossTileString, newWord).stream())
                .collect(Collectors.toList());

//        List<PositionalCrossTile> connectableTilesForWord = connectableCharacters().stream()
//                .filter(positionalCrossTile -> newWord.indexOf(positionalCrossTile.crossTile().containgCharacter()) >= 0)
//                .collect(Collectors.toList());
//
//        return connectableTilesForWord.stream()
//                .flatMap(tile -> calculatePosition(tile, newWord).stream())
//                .collect(Collectors.toList());
                
    }
    
    private List<StartPosition> calculateStartPositions(PositionalCrossTileString toMatch, String fullWord){
        String string = toMatch.stringToMatch();
        List<StartPosition> result = new ArrayList<>();

        int previousMatch = fullWord.indexOf(string);
        
        while(previousMatch >= 0){
            result.add(toMatch.startPositionWithOffset(previousMatch));
            previousMatch = fullWord.indexOf(string, previousMatch+1);
        }

        return result;
        
    }
    
    private Optional<StartPosition> calculcateStartPosition(PositionalCrossTileString toMatch, String fullWord){
        String string = toMatch.stringToMatch();
        int index = fullWord.indexOf(string);
        if (index < 0) {
            return Optional.empty();
        }
        return Optional.of(toMatch.startPositionWithOffset(index));
    }

    /*private List<StartPosition> calculatePosition(PositionalCrossTile positionalCrossTile, String word) {
        return connectable(positionalCrossTile).map(direction -> {
            List<StartPosition> startPositions = new ArrayList<>();
            if(direction == Direction.HORIZONTAL){
                for(int i = positionalCrossTile.column()-word.length(); i < positionalCrossTile.column(); i++){
                    if(putWordOnPosition(word, positionalCrossTile.row(), i, direction, true)){
                        startPositions.add(StartPosition.create(positionalCrossTile.row(), i+1, direction));
                    }
                }

            }
            if(direction == Direction.VERTICAL){
                for(int i = positionalCrossTile.row()-word.length(); i < positionalCrossTile.row(); i++){
                    if(putWordOnPosition(word, i,  positionalCrossTile.column(), direction, true)){
                        startPositions.add(StartPosition.create(i+1, positionalCrossTile.column(), direction));
                    }
                }

            }
            return startPositions;
       }).orElseGet(() -> new ArrayList<>());
    }

    /*private boolean putWordOnPosition(String word, int startRow, int startColumn, Direction direction, boolean testPosition){
        String[] split = word.split("(?!^)");

        if(testPosition){
            boolean fits = true;
            if(direction == Direction.HORIZONTAL){
                fits = fits && testPosition(CrossTile.createBlack(), startRow, startColumn);
                fits = fits && testPosition(CrossTile.createBlack(), startRow, startColumn+word.length()+1);

                for (int i = 0; i < split.length; i++) {
                    fits = fits && testPosition(CrossTile.createWithCharacter(split[i]), startRow, startColumn+i+1);
                }

            }else {
                fits = fits && testPosition(CrossTile.createBlack(), startRow, startColumn);
                fits = fits && testPosition(CrossTile.createBlack(), startRow + word.length() + 1, startColumn);

                for (int i = 0; i < split.length; i++) {
                    fits = fits && testPosition(CrossTile.createWithCharacter(split[i]), startRow+i+1, startColumn);
                }

            }

            return fits;
        }
        else {


            if(direction == Direction.HORIZONTAL){
                putOnPosition(CrossTile.createBlack(), startRow, startColumn);
                putOnPosition(CrossTile.createBlack(), startRow, startColumn+word.length()+1);

                for (int i = 0; i < split.length; i++) {
                    putOnPosition(CrossTile.createWithCharacter(split[i]), startRow, startColumn+i+1);
                }

            }else {
                putOnPosition(CrossTile.createBlack(), startRow, startColumn);
                putOnPosition(CrossTile.createBlack(), startRow + word.length() + 1, startColumn);

                for (int i = 0; i < split.length; i++) {
                    putOnPosition(CrossTile.createWithCharacter(split[i]), startRow+i+1, startColumn);
                }

            }
        }
        return true;
    }*/
}
